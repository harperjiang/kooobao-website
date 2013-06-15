package com.kooobao.ecom.customer.dao;

import java.util.List;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.ecom.customer.entity.Customer;

public class JpaCustomerDao extends AbstractJpaDao<Customer> implements
		CustomerDao {

	@Override
	public Customer find(String id) {
		return getEntityManager()
				.createQuery("select c from Customer c where c.id = :id",
						Customer.class).setParameter("id", id)
				.getSingleResult();
	}

	@Override
	public List<Customer> search(String name) {
		return getEntityManager()
				.createQuery(
						"select c from Customer c where c.name like :name order by c.id",
						Customer.class).setParameter("name", "%" + name + "%")
				.getResultList();
	}

}
