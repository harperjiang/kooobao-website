package com.kooobao.ecom.crm.setting.dao;

import com.kooobao.ecom.crm.setting.entity.CustomerSetting;

public interface SettingDao {

	CustomerSetting getCustomerSetting();

	void setCustomerSetting(CustomerSetting cs);
}
