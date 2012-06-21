package com.kooobao.lm.purchase.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.purchase.PurchaseSearchBean;
import com.kooobao.lm.purchase.entity.Purchase;

public class JpaPurchaseDao extends AbstractJpaDao<Purchase> implements
		PurchaseDao {

	public PageSearchResult<Purchase> search(PurchaseSearchBean searchBean) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
		Root<Purchase> root = countQuery.from(Purchase.class);
		countQuery.select(cb.count(root)).where(
				searchCondition(cb, root, searchBean));
		long count = getEntityManager().createQuery(countQuery)
				.getSingleResult();
		List<Purchase> purchaseList;
		if (count != 0) {
			CriteriaQuery<Purchase> query = cb.createQuery(Purchase.class);
			query.where(searchCondition(cb, query.from(Purchase.class),
					searchBean));
			purchaseList = getEntityManager().createQuery(query)
					.setFirstResult(searchBean.getFrom())
					.setMaxResults(searchBean.getTo() - searchBean.getFrom())
					.getResultList();
		} else {
			purchaseList = new ArrayList<Purchase>();
		}
		return new PageSearchResult<Purchase>(count, purchaseList);
	}

	protected Predicate[] searchCondition(CriteriaBuilder cb, Root<?> root,
			PurchaseSearchBean searchBean) {
		List<Predicate> conditions = new ArrayList<Predicate>();

		conditions
				.add(cb.equal(root.get("state"), searchBean.getState().name()));
		if (null != searchBean.getFromDate() && null != searchBean.getToDate()) {
			conditions.add(cb.between(root.<Date> get("createTime"),
					searchBean.getFromDate(), searchBean.getToDate()));
		} else if (null != searchBean.getFromDate()) {
			conditions.add(cb.greaterThanOrEqualTo(
					root.<Date> get("createTime"), searchBean.getFromDate()));
		} else if (null != searchBean.getToDate()) {
			conditions.add(cb.lessThanOrEqualTo(
					root.<Date> get("createTime"), searchBean.getToDate()));
		}

		Predicate[] carray = new Predicate[conditions.size()];
		conditions.toArray(carray);
		return carray;
	}
}
