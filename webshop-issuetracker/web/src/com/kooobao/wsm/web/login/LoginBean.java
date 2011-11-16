package com.kooobao.wsm.web.login;

import java.util.Date;

import org.springframework.dao.EmptyResultDataAccessException;

import com.kooobao.wsm.domain.dao.UserDao;
import com.kooobao.wsm.domain.entity.user.User;

public class LoginBean {

	private String userId;

	private String plainPass;

	private boolean authorized = false;

	private int errorCount;

	private Date loginDate = new Date();

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPlainPass() {
		return plainPass;
	}

	public void setPlainPass(String plainPass) {
		this.plainPass = plainPass;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

	public boolean isAuthorized() {
		return authorized;
	}

	public void setAuthorized(boolean authorized) {
		this.authorized = authorized;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String login() {
		try {
			User user = getUserDao().findUser(getUserId());
			if (user.getEncryptedPass()
					.equals(User.encryptPass(getPlainPass()))) {
				setAuthorized(true);
				return "success";
			}
			return "failed";
		} catch (EmptyResultDataAccessException e) {
			return "failed";
		}
	}

	public String logout() {
		setAuthorized(false);
		return "success";
	}

	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
