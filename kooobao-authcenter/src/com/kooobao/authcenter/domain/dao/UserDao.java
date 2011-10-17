package com.kooobao.authcenter.domain.dao;

import java.util.List;

import com.kooobao.authcenter.domain.entity.Authority;
import com.kooobao.authcenter.domain.entity.User;
import com.kooobao.common.domain.dao.Dao;

public interface UserDao extends Dao<User> {

	public List<User> getUserByAuthority();

	public User findUser(String userId);
	
	public User findUser(String userId, Authority auth);
}
