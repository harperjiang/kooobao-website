package com.kooobao.util.print;

import com.kooobao.util.print.setting.HTSettingBean;
import com.kooobao.util.print.setting.PostSettingBean;
import com.kooobao.util.print.setting.QFSettingBean;
import com.kooobao.util.print.setting.SFSettingBean;
import com.kooobao.util.print.setting.YTOSettingBean;
import com.kooobao.util.print.setting.YundaSettingBean;
import com.kooobao.util.print.setting.ZJSSettingBean;
import com.kooobao.util.print.setting.ZTOSettingBean;

public enum ExpressCompany {
	中通(ZTOSettingBean.class), 全丰(QFSettingBean.class), 邮政(PostSettingBean.class), 韵达(
			YundaSettingBean.class), 汇通(HTSettingBean.class), 顺丰(
			SFSettingBean.class), 圆通(YTOSettingBean.class), 宅急送(
			ZJSSettingBean.class);

	Class<? extends ExpressOrderSettingBean> defBeanClass;

	ExpressCompany() {
		this(null);
	}

	ExpressCompany(Class<? extends ExpressOrderSettingBean> defBeanClass) {
		this.defBeanClass = defBeanClass;
	}

	public String getDisplayName() {
		return name();
	}

	public Class<? extends ExpressOrderSettingBean> getDefBeanClass() {
		return defBeanClass;
	}

	public ExpressOrderSettingBean getPrintSetting() {
		// TODO Allow Saving
		try {
			return (ExpressOrderSettingBean) getDefBeanClass().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
