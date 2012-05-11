package com.kooobao.authcenter.service;

import java.util.List;

public interface UserService {

	public List<String> getUsers(String system, List<String> auths);

	public void register(String system, String id, String pass);

	public boolean modifyPass(String system, String id, String oldPass,
			String newPass);
}
