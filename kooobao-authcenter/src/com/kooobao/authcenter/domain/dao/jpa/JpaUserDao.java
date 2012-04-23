package com.kooobao.authcenter.domain.dao.jpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.kooobao.authcenter.domain.dao.UserDao;
import com.kooobao.authcenter.domain.entity.Authority;
import com.kooobao.authcenter.domain.entity.User;

public class JpaUserDao implements UserDao {

	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public List<User> getUserByAuthority() {
		return new ArrayList<User>();
	}

	public User findUser(String userid) {
		TypedQuery<User> query = getEntityManager().createQuery(
				"select u from User u where u.id = :id", User.class);
		query.setParameter("id", userid);
		return query.getSingleResult();
	}

	public User findUser(String system, String userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", userId);
		TypedQuery<User> query = getEntityManager()
				.createQuery(
						"select u from User u join u.systems s where u.id = :id and value(s) = :sysname",
						User.class);
		query.setParameter("id", userId);
		query.setParameter("sysname", system);
		return query.getSingleResult();

	}

	public User store(User user) {
		return getEntityManager().merge(user);
	}

	public User findUser(String userId, Authority auth) {
		return null;
	}

	public List<User> findUsers(String system, List<Authority> auths) {
		TypedQuery<User> query = getEntityManager()
				.createQuery(
						"select u from User u join u.systems s where value(s)=:sysname",
						User.class);
		query.setParameter("sysname", system);
		return query.getResultList();
	}
}
