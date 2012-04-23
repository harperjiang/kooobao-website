package com.kooobao.authcenter.domain.dao;

import java.util.List;

import com.kooobao.authcenter.domain.entity.Authority;
import com.kooobao.authcenter.domain.entity.User;

public interface UserDao {
	
	public User store(User user);

	public List<User> getUserByAuthority();

	public User findUser(String system, String userId);

	public User findUser(String userId, Authority auth);
	
	public List<User> findUsers(String system, List<Authority> auths);

	public User findUser(String id);
}
