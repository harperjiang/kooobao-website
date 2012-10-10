package com.kooobao.crm.customer.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.common.domain.dao.Cursor;
import com.kooobao.common.domain.dao.cursor.JpaCursor;
import com.kooobao.crm.customer.entity.Hint;
import com.kooobao.crm.customer.entity.HintStatus;

public class JpaHintDao extends AbstractJpaDao<Hint> implements HintDao {

	@Override
	public List<Hint> getHints(String operatorId) {
		return getEntityManager()
				.createQuery(
						"select h from Hint h where h.status = 'FOLLOWUP' and h.ownBy = :operator",
						Hint.class).setParameter("operator", operatorId)
				.getResultList();
	}

	@Override
	public List<Hint> getFreeHints(int count) {
		return getEntityManager()
				.createQuery(
						"select h from Hint h where h.status = :status order by h.createTime desc",
						Hint.class).setMaxResults(count)
				.setParameter("status", HintStatus.FREE.name()).getResultList();
	}

	@Override
	public Cursor<Hint> getOvertimeHints(int hintRetainTime) {
		String queryStr = "select h.* from crm_hint h "
				+ "where adddate(h.update_time,?) < ? "
				+ "and h.status = 'FOLLOWUP' " + "order by h.create_time desc";
		Query query = getEntityManager()
				.createNativeQuery(queryStr)
				.setParameter("1", hintRetainTime)
				.setParameter("2", new Date());
		return new JpaCursor<Hint>(query);
	}

}
