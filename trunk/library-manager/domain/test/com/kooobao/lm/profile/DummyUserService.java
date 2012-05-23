package com.kooobao.lm.profile;

import java.util.List;

import com.kooobao.authcenter.service.UserService;

public class DummyUserService implements UserService {

	public List<String> getUsers(String system, List<String> auths) {
		return null;
	}

	public void register(String system, String id, String pass) {

	}

	public boolean modifyPass(String system, String id, String oldPass,
			String newPass) {
		return false;
	}

}
