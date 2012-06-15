package com.kooobao.lm.profile.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.commons.lang.StringUtils;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.profile.entity.ActivationRecord;
import com.kooobao.lm.profile.entity.Visitor;
import com.kooobao.lm.profile.entity.VisitorStatus;

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

	public PageSearchResult<Visitor> search(String visitorId,
			VisitorStatus visitorStatus) {
		List<Visitor> result = new ArrayList<Visitor>();
		if (!StringUtils.isEmpty(visitorId)) {
			result.add(find(visitorId));
			return new PageSearchResult<Visitor>(result.size(), result);
		}
		long count = getEntityManager()
				.createQuery(
						"select count(v) from Visitor v where v.status = :s",
						Long.class).setParameter("s", visitorStatus.name())
				.getSingleResult();
		if (count > 0)
			result = getEntityManager()
					.createQuery("select v from Visitor v where v.status = :s",
							Visitor.class)
					.setParameter("s", visitorStatus.name()).setFirstResult(0)
					.setMaxResults(10).getResultList();

		return new PageSearchResult<Visitor>(count, result);
	}
}
