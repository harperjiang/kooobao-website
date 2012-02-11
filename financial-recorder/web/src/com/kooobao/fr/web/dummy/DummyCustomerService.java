package com.kooobao.fr.web.dummy;

import java.util.ArrayList;
import java.util.List;

import com.kooobao.fr.domain.entity.Customer;
import com.kooobao.fr.service.CustomerService;

public class DummyCustomerService implements CustomerService {

	public List<Customer> getRecentCustomer(int count) {
		List<Customer> customers = new ArrayList<Customer>();
		for(int i = 0 ; i < count; i ++) {
			Customer cust = new Customer();
			cust.setName("Cust " + i);
			cust.setCompany("Company "+i);
			cust.setAccount("Account "+i);
			customers.add(cust);
		}
		return customers;
	}

	public Customer addCustomer(Customer newCust) {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer findCustomer(Customer similar) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
