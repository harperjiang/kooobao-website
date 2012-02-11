package com.kooobao.fr.service;

import java.util.List;

import com.kooobao.fr.domain.entity.Customer;

public interface CustomerService {

	List<Customer> getRecentCustomer(int count);
	
	Customer addCustomer(Customer newCust);
	
	Customer findCustomer(Customer similar);
}
