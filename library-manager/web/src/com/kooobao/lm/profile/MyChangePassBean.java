package com.kooobao.lm.profile;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.kooobao.authcenter.service.UserService;
import com.kooobao.common.web.bean.AbstractBean;

@ManagedBean(name = "myChangePassBean")
@SessionScoped
public class MyChangePassBean extends AbstractBean {

	public String save() {
		if (!newPass1.equals(newPass2)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("两次输入的新密码不一致"));
			return "success";
		}
		MyIndexBean bean = findBean("myIndexBean");
		String id = bean.getVisitor().getId();
		if (getUserService().modifyPass("lm", id, oldPass, newPass1)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("新密码已保存", "新密码已保存"));
		} else {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "密码验证失败",
							"密码验证失败"));
		}
		return "success";
	}

	@ManagedProperty("#{userService}")
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private String oldPass;

	private String newPass1;

	private String newPass2;

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public String getNewPass1() {
		return newPass1;
	}

	public void setNewPass1(String newPass1) {
		this.newPass1 = newPass1;
	}

	public String getNewPass2() {
		return newPass2;
	}

	public void setNewPass2(String newPass2) {
		this.newPass2 = newPass2;
	}

}
