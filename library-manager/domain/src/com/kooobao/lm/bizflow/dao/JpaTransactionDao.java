package com.kooobao.lm.bizflow.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.common.domain.dao.Cursor;
import com.kooobao.common.domain.dao.cursor.JpaCursor;
import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.bizflow.TransactionService.TransactionSearchBean;
import com.kooobao.lm.bizflow.entity.Transaction;
import com.kooobao.lm.bizflow.entity.TransactionState;
import com.kooobao.lm.profile.entity.Visitor;

public class JpaTransactionDao extends AbstractJpaDao<Transaction> implements
		TransactionDao {

	public List<Transaction> findToExpire(Date date) {
		return getEntityManager()
				.createQuery(
						"select t from Transaction t where t.dueTime <= :currentTime",
						Transaction.class).setParameter("currentTime", date)
				.getResultList();
	}

	public long getTransactionCount(Visitor visitor, TransactionState... states) {
		String queryStr = "select count(t) from Transaction t where t.visitor = :visitor and t.state in "
				+ genInExpression(states.length);
		TypedQuery<Long> query = getEntityManager().createQuery(queryStr,
				Long.class);
		query.setParameter("visitor", visitor);
		for (int i = 0; i < states.length; i++)
			query.setParameter("t" + i, states[i].name());
		return query.getSingleResult();
	}

	private String genInExpression(int length) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (int i = 0; i < length; i++) {
			sb.append(":t").append(i);
			if (i < length - 1)
				sb.append(",");
		}
		sb.append(")");
		return sb.toString();
	}

	public List<Transaction> getTransactions(Visitor visitor,
			TransactionState... states) {
		String queryStr = "select t from Transaction t where t.visitor = :visitor and t.state in "
				+ genInExpression(states.length);
		TypedQuery<Transaction> query = getEntityManager().createQuery(
				queryStr, Transaction.class);
		query.setParameter("visitor", visitor);
		for (int i = 0; i < states.length; i++)
			query.setParameter("t" + i, states[i].name());
		return query.getResultList();
	}

	public PageSearchResult<Transaction> search(Visitor visitor,
			TransactionSearchBean searchBean) {
		TypedQuery<Long> countQuery = getEntityManager()
				.createQuery(
						"select count(t) from Transaction t where t.visitor = :visitor and t.createTime between :fromDate and :toDate and t.state = :state order by t.createTime",
						Long.class);
		TypedQuery<Transaction> query = getEntityManager()
				.createQuery(
						"select t from Transaction t where t.visitor = :visitor and t.createTime between :fromDate and :toDate and t.state = :state order by t.createTime",
						Transaction.class);
		countQuery.setParameter("fromDate", searchBean.getFromDate());
		countQuery.setParameter("toDate", searchBean.getToDate());
		countQuery.setParameter("state", searchBean.getState().name());
		countQuery.setParameter("visitor", visitor);
		query.setParameter("fromDate", searchBean.getFromDate());
		query.setParameter("toDate", searchBean.getToDate());
		query.setParameter("state", searchBean.getState().name());
		query.setParameter("visitor", visitor);
		query.setFirstResult(searchBean.getFrom());
		query.setMaxResults(searchBean.getTo() - searchBean.getFrom());
		long count = countQuery.getSingleResult();
		List<Transaction> result = query.getResultList();
		return new PageSearchResult<Transaction>(count, result);
	}

	public Cursor<Transaction> getTransactions(TransactionState state) {
		return new JpaCursor<Transaction>(getEntityManager().createQuery(
				"select t from Transaction t where t.state = :state",
				Transaction.class).setParameter("state", state.name()));
	}

}
