package com.kooobao.ecom.user.dao;

import org.apache.commons.lang.Validate;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.ecom.user.entity.User;

public class JpaUserDao extends AbstractJpaDao<User> implements UserDao {

	@Override
	public User find(String id) {
		Validate.notEmpty(id, "User id should not be empty");
		return getEntityManager()
				.createQuery("select u from User u where u.id = :id",
						User.class).setParameter("id", id).getSingleResult();
	}

}
