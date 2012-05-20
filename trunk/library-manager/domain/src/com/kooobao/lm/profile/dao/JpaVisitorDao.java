package com.kooobao.lm.profile.dao;

import javax.persistence.NoResultException;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.lm.profile.entity.ActivationRecord;
import com.kooobao.lm.profile.entity.Visitor;

public class JpaVisitorDao extends AbstractJpaDao<Visitor> implements
		VisitorDao {

	public Visitor find(String id) {
		try {
			return getEntityManager()
					.createQuery("select v from Visitor v where v.id = :id",
							Visitor.class).setParameter("id", id)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void store(ActivationRecord actRecord) {
		getEntityManager().persist(actRecord);
	}

	public ActivationRecord getActivationRecord(String uuid) {
		try {
			return getEntityManager()
					.createQuery(
							"select ar from ActivationRecord ar where ar.activationId = :uuid",
							ActivationRecord.class).setParameter("uuid", uuid)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void removeActivationRecord(ActivationRecord acr) {
		getEntityManager().remove(acr);
	}
}
