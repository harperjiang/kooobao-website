package com.kooobao.cws.web.member;

import com.kooobao.authcenter.Constants;
import com.kooobao.authcenter.web.bean.LoginBean;

public class MemberBean extends LoginBean {

	@Override
	public String login() {
		// Override jump url
		getSession().setAttribute(Constants.JUMP_URL,"/member.xhtml");

		return super.login();
	}
	
	@Override
	protected String getSystem() {
		return "website";
	}
}
