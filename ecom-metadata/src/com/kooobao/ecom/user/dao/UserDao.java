package com.kooobao.ecom.user.dao;

import com.kooobao.common.domain.dao.Dao;
import com.kooobao.ecom.user.entity.User;

public interface UserDao extends Dao<User> {

	User find(String id);
}
