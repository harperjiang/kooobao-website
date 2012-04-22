package com.kooobao.cws.service.customer;

import com.kooobao.cws.domain.customer.Customer;

public interface CustomerService {

	void register(Customer customer);
	
	void confirm(String uuid);
}
