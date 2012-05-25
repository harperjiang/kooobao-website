package com.kooobao.lm.bizflow.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.common.domain.dao.Cursor;
import com.kooobao.common.domain.dao.cursor.JpaCursor;
import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.bizflow.SearchBean;
import com.kooobao.lm.bizflow.entity.ExpireRecord;
import com.kooobao.lm.bizflow.entity.Transaction;
import com.kooobao.lm.profile.entity.Visitor;

public class JpaExpireRecordDao extends AbstractJpaDao<ExpireRecord> implements
		ExpireRecordDao {

	public PageSearchResult<ExpireRecord> search(Visitor visitor,
			SearchBean searchBean) {
		TypedQuery<Long> countQuery = getEntityManager()
				.createQuery(
						"select count(er) from ExpireRecord er where er.transaction.visitor = :visitor and er.createTime between :fromDate and :toDate order by er.createTime",
						Long.class);
		TypedQuery<ExpireRecord> query = getEntityManager()
				.createQuery(
						"select er from ExpireRecord er where er.transaction.visitor = :visitor and er.createTime between :fromDate and :toDate order by er.createTime",
						ExpireRecord.class);
		countQuery.setParameter("fromDate", searchBean.getFromDate());
		countQuery.setParameter("toDate", searchBean.getToDate());
		countQuery.setParameter("visitor", visitor);
		query.setParameter("fromDate", searchBean.getFromDate());
		query.setParameter("toDate", searchBean.getToDate());
		query.setParameter("visitor", visitor);
		query.setFirstResult(searchBean.getFrom());
		query.setMaxResults(searchBean.getTo() - searchBean.getFrom());
		int count = countQuery.getSingleResult().intValue();
		List<ExpireRecord> result = query.getResultList();
		return new PageSearchResult<ExpireRecord>(count, result);
	}

	public ExpireRecord findByTransaction(Transaction transaction) {
		try {
			return getEntityManager()
					.createQuery(
							"select er from ExpireRecord er where er.transaction = :tran",
							ExpireRecord.class)
					.setParameter("tran", transaction).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Cursor<ExpireRecord> findActivateRecords() {
		TypedQuery<ExpireRecord> query = getEntityManager().createQuery(
				"select er from ExpireRecord er where er.state = 'ACTIVE'",
				ExpireRecord.class);
		return new JpaCursor<ExpireRecord>(query);
	}

}
