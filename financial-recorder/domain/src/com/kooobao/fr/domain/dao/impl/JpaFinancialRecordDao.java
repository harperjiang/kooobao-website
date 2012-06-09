package com.kooobao.fr.domain.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.fr.domain.dao.FinancialRecordDao;
import com.kooobao.fr.domain.entity.FinancialRecord;

public class JpaFinancialRecordDao extends AbstractJpaDao<FinancialRecord>
		implements FinancialRecordDao {

	public int getCountByCreateBy(String actor) {
		Long count = getEntityManager()
				.createQuery(
						"select count(f) from FinancialRecord f where f.createBy = :id",
						Long.class).setParameter("id", actor).getSingleResult();
		return count.intValue();
	}

	public int getCountByStatus(String[] status) {
		if (status == null || status.length == 0)
			return 0;
		TypedQuery<Long> query = getEntityManager().createQuery(
				"select count(f) from FinancialRecord f where f.status in "
						+ inQuery(status.length), Long.class);
		for (int i = 0; i < status.length; i++) {
			query.setParameter("val" + i, status[i]);
		}
		return query.getSingleResult().intValue();
	}

	public List<FinancialRecord> getRecordsByCreatedBy(String id, int limit) {
		return getEntityManager()
				.createQuery(
						"select f from FinancialRecord f where f.createBy = :id order by f.createTime desc",
						FinancialRecord.class).setParameter("id", id)
				.setMaxResults(limit).getResultList();
	}

	public List<FinancialRecord> getRecordsByStatus(String[] status, int limit) {
		String queryStr = "select f from FinancialRecord f ";

		if (!(status == null || status.length == 0)) {
			queryStr += "where f.status in " + inQuery(status.length);
		}
		queryStr += " order by f.createTime desc";
		TypedQuery<FinancialRecord> query = getEntityManager().createQuery(
				queryStr, FinancialRecord.class);
		for (int i = 0; i < status.length; i++) {
			query.setParameter("val" + i, status[i]);
		}
		return query.getResultList();
	}

	public int searchCount(Date fromDate, Date toDate, String[] status) {
		TypedQuery<Long> query = getEntityManager()
				.createQuery(
						"select count(f) from FinancialRecord f where (f.createTime between :fromDate and :toDate) and f.status in "
								+ inQuery(status.length), Long.class);
		for (int i = 0; i < status.length; i++) {
			query.setParameter("val" + i, status[i]);
		}
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		return query.getSingleResult().intValue();
	}

	public List<FinancialRecord> search(Date fromDate, Date toDate,
			String[] status, int recordStart, int recordStop) {
		TypedQuery<FinancialRecord> query = getEntityManager()
				.createQuery(
						"select f from FinancialRecord f where (f.createTime between :fromDate and :toDate) and f.status in "
								+ inQuery(status.length)
								+ " order by f.createTime desc",
						FinancialRecord.class);
		for (int i = 0; i < status.length; i++) {
			query.setParameter("val" + i, status[i]);
		}
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		if (recordStart != -1)
			query.setFirstResult(recordStart);
		if (recordStop != -1)
			query.setMaxResults(recordStop - recordStart);
		return query.getResultList();
	}

	static final String inQuery(int count) {
		StringBuilder builder = new StringBuilder();
		builder.append("(");
		for (int i = 0; i < count; i++) {
			builder.append(":val" + i).append(",");
		}
		builder.deleteCharAt(builder.length() - 1);
		builder.append(")");
		return builder.toString();
	}

	static String SUMAMOUNT_QUERY = "select sum(r.amount + r.adjustAmount) from FinancialRecord r where r.status = :status and r.createTime between :begin and :end";

	public BigDecimal getSumAmount(Date from, Date to, String status) {
		TypedQuery<BigDecimal> query = getEntityManager().createQuery(
				SUMAMOUNT_QUERY, BigDecimal.class);
		query.setParameter("status", status);
		query.setParameter("begin", from);
		query.setParameter("end", to);
		return query.getSingleResult();
	}
}
