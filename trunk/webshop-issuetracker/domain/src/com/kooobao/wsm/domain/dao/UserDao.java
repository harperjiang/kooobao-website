package com.kooobao.wsm.domain.dao;

import java.util.List;

import com.kooobao.wsm.domain.entity.user.User;

public interface UserDao extends Dao<User> {

	public List<User> getIssueFollowers();

	public User findUser(String userId);
}
