package com.kooobao.lm.bizflow.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
						"select t from Transaction t where t.state in ('RETURN_WAIT','RETURN_SENT') and t.dueTime <= :currentTime",
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

	protected Predicate[] genSearchQuery(CriteriaBuilder cb,
			Root<Transaction> root, Visitor v, TransactionSearchBean searchBean) {
		List<Predicate> ps = new ArrayList<Predicate>();
		ps.add(cb.equal(root.get("state"), searchBean.getState().name()));
		ps.add(cb.between(root.<Date> get("createTime"),
				searchBean.getFromDate(), searchBean.getToDate()));
		if (null != v) {
			ps.add(cb.equal(root.<Visitor> get("visitor"), v));
		}

		Predicate[] pa = new Predicate[ps.size()];
		ps.toArray(pa);
		return pa;
	}

	public PageSearchResult<Transaction> search(Visitor visitor,
			TransactionSearchBean searchBean) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

		CriteriaQuery<Transaction> tranQ = cb.createQuery(Transaction.class);
		Root<Transaction> root = tranQ.from(Transaction.class);
		tranQ.where(genSearchQuery(cb, root, visitor, searchBean));

		CriteriaQuery<Long> countQ = cb.createQuery(Long.class);
		Root<Transaction> countRoot = countQ.from(Transaction.class);
		countQ.select(cb.count(countRoot)).where(
				genSearchQuery(cb, countRoot, visitor, searchBean));

		TypedQuery<Long> countQuery = getEntityManager().createQuery(countQ);
		TypedQuery<Transaction> query = getEntityManager().createQuery(tranQ);
		query.setFirstResult(searchBean.getFrom());
		query.setMaxResults(searchBean.getTo() - searchBean.getFrom());
		long count = countQuery.getSingleResult();
		List<Transaction> result = new ArrayList<Transaction>();
		if (0 != count) {
			result = query.getResultList();
		}
		return new PageSearchResult<Transaction>(count, result);
	}

	public Cursor<Transaction> getTransactions(TransactionState state) {
		return new JpaCursor<Transaction>(getEntityManager().createQuery(
				"select t from Transaction t where t.state = :state",
				Transaction.class).setParameter("state", state.name()));
	}

	public List<Transaction> getTransactionsForComment(Visitor visitor, int i) {
		return getEntityManager()
				.createQuery(
						"select t from Transaction t where t.state = 'RETURN_RECEIVED' and t.visitor = :visitor and t.returnTime >= :currentTime and t.rating = 0",
						Transaction.class)
				.setParameter("currentTime",
						new Date(System.currentTimeMillis() - i * 86400000l))
				.setParameter("visitor", visitor).getResultList();
	}
}
