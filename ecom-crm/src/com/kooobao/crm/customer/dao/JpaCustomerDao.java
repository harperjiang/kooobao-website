package com.kooobao.crm.customer.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.common.domain.dao.Cursor;
import com.kooobao.common.domain.dao.cursor.JpaCursor;
import com.kooobao.crm.customer.entity.Customer;
import com.kooobao.crm.customer.entity.CustomerStatus;

public class JpaCustomerDao extends AbstractJpaDao<Customer> implements
		CustomerDao {

	@Override
	public List<Customer> getCustomersByOwner(String operatorId) {
		return getEntityManager()
				.createQuery(
						"select c from Customer c where c.ownBy = :opr_id order by c.createTime desc",
						Customer.class).setParameter("opr_id", operatorId)
				.getResultList();
	}

	@Override
	public List<Customer> getFreeCustomers(int count) {
		return getEntityManager()
				.createQuery(
						"select c from Customer c where c.status = :status order by c.createTime desc",
						Customer.class).setMaxResults(count)
				.setParameter("status", CustomerStatus.FREE.name())
				.getResultList();
	}

	@Override
	public Cursor<Customer> getOvertimeCustomers(int customerRetainTime) {
		String queryStr = "select c.* from crm_customer c where adddate(c.update_time,?) < ? and c.status = 'OCCUPIED' order by c.create_time desc";
		Query query = getEntityManager().createNativeQuery(queryStr)
				.setParameter(1, customerRetainTime)
				.setParameter(2, new Date());
		return new JpaCursor<Customer>(query);
	}

	@Override
	public Customer find(String source, String id) {
		return getEntityManager()
				.createNamedQuery("findCustomerBySourceAndId", Customer.class)
				.setParameter("source", source).setParameter("id", id)
				.getSingleResult();
	}

}
