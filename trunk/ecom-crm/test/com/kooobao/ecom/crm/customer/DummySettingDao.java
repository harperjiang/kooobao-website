package com.kooobao.ecom.crm.customer;

import com.kooobao.ecom.crm.setting.CustomerSetting;
import com.kooobao.ecom.setting.SettingInfo;
import com.kooobao.ecom.setting.dao.SettingDao;

public class DummySettingDao implements SettingDao {

	@Override
	public <T extends SettingInfo> T getSetting(Class<T> clazz) {
		if(CustomerSetting.class == clazz) {
			CustomerSetting cs = new CustomerSetting();
			cs.setCustomerLimit(3);
			return (T)cs;
		}
		return null;
	}

	@Override
	public <T extends SettingInfo> void setSetting(T cs) {

	}

}
