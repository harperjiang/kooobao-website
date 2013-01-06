package com.kooobao.ecom.setting.dao;

import com.kooobao.ecom.setting.SettingInfo;


public interface SettingDao {

	public <T extends SettingInfo> T getSetting(Class<T> clazz);

	public <T extends SettingInfo> void setSetting(T cs);
}
