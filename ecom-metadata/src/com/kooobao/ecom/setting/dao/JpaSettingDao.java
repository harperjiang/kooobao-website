package com.kooobao.ecom.setting.dao;

import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.LoggerFactory;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.ecom.setting.SettingInfo;
import com.kooobao.ecom.setting.entity.SettingEntry;

public class JpaSettingDao extends AbstractJpaDao<SettingEntry> implements
		SettingDao {

	@Override
	public <T extends SettingInfo> T getSetting(Class<T> clazz) {
		T inst;
		try {
			inst = clazz.newInstance();
			load(inst);
			return inst;
		} catch (Exception e) {
			throw new RuntimeException("Cannot instantiate setting object", e);
		}
	}

	@Override
	public <T extends SettingInfo> void setSetting(T cs) {
		save(cs);
	}

	protected void load(Object bean) {
		for (PropertyDescriptor pd : PropertyUtils.getPropertyDescriptors(bean)) {
			if (null == pd.getWriteMethod())
				continue;
			SettingEntry se = getEntry(bean.getClass().getSimpleName() + "."
					+ pd.getName());
			PropertyEditor pe = PropertyEditorManager.findEditor(pd
					.getPropertyType());
			pe.setAsText(se.getValue());
			try {
				pd.getWriteMethod().invoke(bean, pe.getValue());
			} catch (Exception e) {
				LoggerFactory.getLogger(getClass()).error(
						"Cannot load setting for " + se.getKey(), e);
			}
		}
	}

	protected void save(Object bean) {
		for (PropertyDescriptor pd : PropertyUtils.getPropertyDescriptors(bean)) {
			if (null == pd.getWriteMethod())
				continue;
			SettingEntry se = new SettingEntry();
			se.setKey(bean.getClass().getSimpleName() + "." + pd.getName());
			PropertyEditor pe = PropertyEditorManager.findEditor(pd
					.getPropertyType());
			try {
				pe.setValue(pd.getReadMethod().invoke(bean, (Object[]) null));
				se.setValue(pe.getAsText());
				putEntry(se);
			} catch (Exception e) {
				LoggerFactory.getLogger(getClass()).error(
						"Cannot save setting for " + se.getKey(), e);
			}
		}
	}

	protected SettingEntry getEntry(String key) {
		return getEntityManager().find(SettingEntry.class, key);
	}

	protected void putEntry(SettingEntry entry) {
		store(entry);
	}

}
