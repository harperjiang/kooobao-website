package org.harper.ourwebsite.member.login;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.harper.frm.ui.jsf.AbstractBean;

@ManagedBean
@SessionScoped
public class LoginBean extends AbstractBean {

	private String name;

	private String pass;

	private boolean login;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public boolean isLogin() {
		return login;
	}

	public String login() {
		if (0 == System.currentTimeMillis() % 2) {
			login = true;
			return "success";
		}

		addMessage("Failed to login", null);
		return "failed";
	}
}
