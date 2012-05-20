package com.kooobao.lm.profile;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.common.web.verifycode.VerifyCodeHelper;

@ManagedBean(name = "registerBean")
@RequestScoped
public class RegisterBean extends AbstractBean {

	private String email;

	private String password;

	private String confirmPass;

	private String verifyCode;

	public String register() {
		FacesContext context = FacesContext.getCurrentInstance();
		// Validate VerifyCode
		if (!new VerifyCodeHelper().verify((HttpSession) context
				.getExternalContext().getSession(true), verifyCode)) {
			addMessage(FacesMessage.SEVERITY_ERROR, "验证码错误");
			return "failed";
		}
		if (!password.equals(confirmPass)) {
			addMessage(FacesMessage.SEVERITY_ERROR, "请确保两次输入的密码一样");
			return "failed";
		}
		getProfileService().register(email, password);
		return "success";
	}

	@ManagedProperty("#{profileService}")
	private ProfileService profileService;

	public ProfileService getProfileService() {
		return profileService;
	}

	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPass() {
		return confirmPass;
	}

	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

}
