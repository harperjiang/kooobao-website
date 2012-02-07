package com.kooobao.fr.web.record;

import java.util.List;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.common.web.bean.RowIndexCounter;
import com.kooobao.fr.domain.entity.Customer;
import com.kooobao.fr.service.CustomerService;

public class SelectCustomerBean extends AbstractBean {

	private CustomerService customerService;

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	public List<Customer> getCustomers() {
		return getCustomerService().getRecentCustomer(5);
	}
	
	private RowIndexCounter counter = new RowIndexCounter();
	
	public RowIndexCounter getCounter() {
		return counter;
	}

	@Override
	public void onPageLoad() {
		counter.reset();
	}
	
	
	public String select() {
		System.out.println("Selected");
		return "true";
	}

}
