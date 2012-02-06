package com.kooobao.wsm.web;

import com.kooobao.authcenter.web.bean.LoginBean;

public abstract class AbstractBean extends com.kooobao.common.web.bean.AbstractBean {

	public String getCurrentUserId() {
		LoginBean loginBean = findBean("login");
		if (null != loginBean)
			return loginBean.getUserId();
		return null;
	}

	public void onPageLoad() {
	}

	public AbstractBean() {
		super();
	}
}
