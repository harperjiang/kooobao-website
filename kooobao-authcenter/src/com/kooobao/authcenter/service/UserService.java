package com.kooobao.authcenter.service;

import java.util.List;

public interface UserService {

	public List<String> getUsers(String system, List<String> auths);

}
