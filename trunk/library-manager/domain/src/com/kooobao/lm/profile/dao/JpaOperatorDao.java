package com.kooobao.lm.profile.dao;

import javax.persistence.NoResultException;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.lm.profile.entity.Operator;

public class JpaOperatorDao extends AbstractJpaDao<Operator> implements
		OperatorDao {

	public Operator find(String id) {
		try {
			return getEntityManager()
					.createQuery("select o from Operator o where o.id = :id",
							Operator.class).setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
