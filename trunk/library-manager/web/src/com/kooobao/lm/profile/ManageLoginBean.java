package com.kooobao.lm.profile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.kooobao.authcenter.web.bean.LoginBean;

@ManagedBean(name="manageLoginBean")
@SessionScoped
public class ManageLoginBean extends LoginBean {

	@Override
	protected boolean verify(String userId) {
		// TODO Auto-generated method stub
		return super.verify(userId);
	}
}
