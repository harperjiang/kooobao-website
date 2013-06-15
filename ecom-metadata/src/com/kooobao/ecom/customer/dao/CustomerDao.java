package com.kooobao.ecom.customer.dao;

import java.util.List;

import com.kooobao.common.domain.dao.Dao;
import com.kooobao.ecom.customer.entity.Customer;

public interface CustomerDao extends Dao<Customer> {

	public Customer find(String id);

	public List<Customer> search(String name);
}
