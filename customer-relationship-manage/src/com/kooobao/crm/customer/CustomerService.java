package com.kooobao.crm.customer;

import java.util.List;

import com.kooobao.crm.customer.entity.Customer;
import com.kooobao.crm.customer.entity.CustomerFollowup;

public interface CustomerService {

	/**
	 * Current operator mark a follow up action of the indicated customer
	 * 
	 * @param customer
	 */
	void recordFollowup(CustomerFollowup followup);

	/**
	 * Get customers that belongs to current operator
	 * 
	 * @return a list of {@link Customer}
	 */
	List<Customer> getCustomers();

	/**
	 * Request new customer to be allocated
	 * 
	 * @return customer count that has been allocated
	 */
	int request();

	/**
	 * Discard old customer, request new one
	 * 
	 * @param old
	 * @return count allocated
	 */
	int exchange(List<Customer> old);
}
