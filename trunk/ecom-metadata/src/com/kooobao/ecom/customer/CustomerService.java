package com.kooobao.ecom.customer;

import java.util.List;

import com.kooobao.ecom.customer.entity.Customer;

public interface CustomerService {

	public void save(Customer customer);

	public Customer find(String id);

	public List<Customer> search(String name);
}
