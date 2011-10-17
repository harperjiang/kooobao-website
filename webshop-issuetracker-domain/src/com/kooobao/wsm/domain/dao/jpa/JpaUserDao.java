package com.kooobao.wsm.domain.dao.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.springframework.orm.jpa.JpaCallback;

import com.kooobao.wsm.domain.dao.UserDao;
import com.kooobao.wsm.domain.entity.user.User;

public class JpaUserDao extends AbstractJpaDao<User> implements UserDao {

	public List<User> getIssueFollowers() {
		return getTemplate().find("select u from User u");
	}

	public User findUser(final String userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", userId);
		return getTemplate().execute(new JpaCallback<User>() {
			public User doInJpa(EntityManager em) throws PersistenceException {
				TypedQuery<User> query = em.createQuery(
						"select u from User u where u.id = :id", User.class);
				query.setParameter("id", userId);
				return query.getSingleResult();
			}
		});
	}
}
