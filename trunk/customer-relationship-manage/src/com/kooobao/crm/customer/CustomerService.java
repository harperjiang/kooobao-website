package com.kooobao.crm.customer;

import java.util.List;

import com.kooobao.crm.common.Context;
import com.kooobao.crm.customer.entity.Customer;
import com.kooobao.crm.customer.entity.CustomerFollowup;

public interface CustomerService {

	/**
	 * Add a customer info
	 * 
	 * @param customer
	 */
	void addCustomer(Context context, Customer customer);

	/**
	 * Current operator mark a follow up action of the indicated customer
	 * 
	 * @param customer
	 */
	void recordFollowup(Context context, CustomerFollowup followup);

	/**
	 * Get customers that belongs to current operator
	 * 
	 * @return a list of {@link Customer}
	 */
	List<Customer> getCustomers(Context context);

	/**
	 * Request new customer to be allocated
	 * 
	 * @return customer count that has been allocated
	 */
	int request(Context context);

	/**
	 * Discard old customer, request new one
	 * 
	 * @param old
	 * @return count allocated
	 */
	int exchange(Context context, List<Customer> old);

	/**
	 * 
	 */
	void freeCustomers();
}
