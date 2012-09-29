package com.kooobao.crm.customer.dao;

import java.util.List;

import com.kooobao.common.domain.dao.Cursor;
import com.kooobao.common.domain.dao.Dao;
import com.kooobao.crm.customer.entity.Customer;

public interface CustomerDao extends Dao<Customer> {

	List<Customer> getCustomersByOwner(String operatorId);

	List<Customer> getFreeCustomers(int count);

	Cursor<Customer> getOvertimeCustomers(int customerRetainTime);

}
