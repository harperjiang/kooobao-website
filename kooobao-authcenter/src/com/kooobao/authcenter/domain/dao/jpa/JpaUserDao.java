package com.kooobao.authcenter.domain.dao.jpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;

import com.kooobao.authcenter.domain.dao.UserDao;
import com.kooobao.authcenter.domain.entity.Authority;
import com.kooobao.authcenter.domain.entity.User;
import com.kooobao.common.domain.dao.AbstractJpaDao;

public class JpaUserDao extends AbstractJpaDao<User> implements UserDao {

	public List<User> getUserByAuthority() {
		return new ArrayList<User>();
	}

	public User findUser(final String userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", userId);
		TypedQuery<User> query = getEntityManager().createQuery(
				"select u from User u where u.id = :id", User.class);
		query.setParameter("id", userId);
		return query.getSingleResult();

	}

	public User findUser(String userId, Authority auth) {
		return null;
	}
}