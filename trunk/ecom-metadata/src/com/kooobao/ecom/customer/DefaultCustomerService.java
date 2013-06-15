package com.kooobao.ecom.customer;

import java.util.List;

import com.kooobao.ecom.customer.dao.CustomerDao;
import com.kooobao.ecom.customer.entity.Customer;

public class DefaultCustomerService implements CustomerService {

	private CustomerDao customerDao;

	@Override
	public void save(Customer customer) {
		customerDao.store(customer);
	}

	@Override
	public Customer find(String id) {
		return customerDao.find(id);
	}

	@Override
	public List<Customer> search(String name) {
		return customerDao.search(name);
	}

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

}
