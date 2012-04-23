package com.kooobao.authcenter.service;

import java.util.ArrayList;
import java.util.List;

import com.kooobao.authcenter.domain.dao.UserDao;
import com.kooobao.authcenter.domain.entity.User;

public class DefaultUserService implements UserService {

	public List<String> getUsers(String system, List<String> auths) {
		List<User> users = getUserDao().findUsers(system, null);
		List<String> userids = new ArrayList<String>();
		for (User user : users)
			userids.add(user.getId());
		return userids;
	}

	private UserDao userDao;

	public UserDao getUserDao() {
		return this.userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void register(String system, String id, String pass) {
		User user = new User();
		user.setId(id);
		user.getSystems().put(system, system);
		user.setEncryptedPass(User.encryptPass(pass));
		getUserDao().store(user);
	}

	public void modifyPass(String system, String id, String oldPass,
			String newPass) {
		User user = getUserDao().findUser(system, id);
		if (user != null
				&& user.getEncryptedPass().equals(User.encryptPass(oldPass))) {
			user.setEncryptedPass(User.encryptPass(newPass));
		}
	}

}
