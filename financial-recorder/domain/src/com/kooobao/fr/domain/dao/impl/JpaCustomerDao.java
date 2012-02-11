package com.kooobao.fr.domain.dao.impl;

import java.util.List;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.fr.domain.dao.CustomerDao;
import com.kooobao.fr.domain.entity.Customer;

public class JpaCustomerDao extends AbstractJpaDao<Customer> implements
		CustomerDao {

	public List<Customer> getRecentCustomer(int limit) {
		return getEntityManager()
				.createQuery(
						"select c from Customer c order by c.createDate desc",
						Customer.class).setMaxResults(limit).getResultList();
	}

}
