package com.kooobao.fr.service;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import com.kooobao.common.concurrent.TaskQueue;
import com.kooobao.common.spring.ApplicationContextHolder;
import com.kooobao.fr.domain.dao.CustomerDao;
import com.kooobao.fr.domain.entity.Customer;

public class DefaultCustomerService implements CustomerService {

	private CustomerDao customerDao;

	public List<Customer> getRecentCustomer(int count) {
		return getCustomerDao().getRecentCustomer(count);
	}

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public Customer addCustomer(Customer newCust) {
		return getCustomerDao().store(newCust);
	}

	public Customer findCustomer(Customer similar) {
		return getCustomerDao().find(similar);
	}

	static TaskQueue<Customer> addCustomerQueue = new TaskQueue<Customer>(
			10000, new TaskQueue.Executor<Customer>() {
				public void execute(Customer customer) {
					CustomerService service = (CustomerService) ApplicationContextHolder
							.getInstance().getApplicationContext()
							.getBean("customerService");
					try {
						customer.setCreateDate(null);
						service.findCustomer(customer);
					} catch (NoResultException e) {
						customer.setCreateDate(new Date());
						service.addCustomer(customer);
					}
				}
			});

}
