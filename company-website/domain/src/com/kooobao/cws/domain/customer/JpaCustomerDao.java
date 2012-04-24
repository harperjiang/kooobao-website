package com.kooobao.cws.domain.customer;

import javax.persistence.TypedQuery;

import com.kooobao.common.domain.dao.AbstractJpaDao;

public class JpaCustomerDao extends AbstractJpaDao<Customer> implements
		CustomerDao {

	@Override
	public Customer findByEmail(String email) {
		TypedQuery<Customer> customerQuery = getEntityManager()
				.createQuery("select c from Customer c where c.email=:email",
						Customer.class).setParameter("email", email);
		return customerQuery.getSingleResult();
	}

	@Override
	public Customer findByRegId(String uuid) {
		TypedQuery<Customer> customerQuery = getEntityManager()
				.createQuery(
						"select c from Customer c join Contact ct on ct.type = 'REG_NO' where ct.value=:uuid",
						Customer.class).setParameter("uuid", uuid);
		return customerQuery.getSingleResult();
	}

}
