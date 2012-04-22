package com.kooobao.cws.domain.customer;

import javax.persistence.TypedQuery;

import com.kooobao.common.domain.dao.AbstractJpaDao;

public class JpaCustomerDao extends AbstractJpaDao<Customer> implements
		CustomerDao {

	@Override
	public Customer findByEmail(String email) {
		TypedQuery<Customer> customerQuery = getEntityManager().createQuery(
				"select c from Customer c where c.email=:email", Customer.class)
				.setParameter("email", email);
		return customerQuery.getSingleResult();
	}

}
