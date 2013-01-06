package com.kooobao.ecom.crm.customer;

import java.util.List;

import com.kooobao.common.domain.service.Context;
import com.kooobao.ecom.crm.customer.entity.Customer;
import com.kooobao.ecom.crm.customer.entity.CustomerFollowup;
import com.kooobao.ecom.crm.customer.entity.CustomerType;
import com.kooobao.ecom.crm.customer.entity.Hint;

public interface CustomerService {

	/**
	 * 
	 * @param id
	 * @return
	 */
	Customer getCustomer(Context context, String source, String id);

	/**
	 * Update Customer Info
	 * 
	 * @param context
	 * @param customer
	 */
	Customer update(Context context, Customer customer);

	/**
	 * Current operator mark a follow up action of the indicated customer
	 * 
	 * @param customer
	 */
	Customer recordFollowup(Context context, CustomerFollowup followup);

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
	 * Free occupied customers that is over time
	 */
	void freeCustomers();

	/**
	 * Upgrade the hint to a customer record
	 * 
	 * @param hint
	 * @return newly created customer
	 */
	Customer upgrade(Context context, Hint hint, CustomerType nature);

}
