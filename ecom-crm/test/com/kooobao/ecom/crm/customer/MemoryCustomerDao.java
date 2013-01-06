package com.kooobao.ecom.crm.customer;

import java.util.ArrayList;
import java.util.List;

import com.kooobao.common.domain.dao.AbstractMemoryDao;
import com.kooobao.common.domain.dao.Cursor;
import com.kooobao.ecom.crm.customer.dao.CustomerDao;
import com.kooobao.ecom.crm.customer.entity.Customer;
import com.kooobao.ecom.crm.customer.entity.CustomerStatus;

public class MemoryCustomerDao extends AbstractMemoryDao<Customer> implements
		CustomerDao {

	@Override
	public List<Customer> getCustomersByOwner(String operatorId) {
		List<Customer> cs = new ArrayList<Customer>();

		for (Customer c : getStorage().values()) {
			if (c.getOwnBy().equals(operatorId)) {
				cs.add(c);
			}
		}
		return cs;
	}

	@Override
	public List<Customer> getFreeCustomers(int count) {
		List<Customer> cs = new ArrayList<Customer>();

		for (Customer c : getStorage().values()) {
			if (c.getStatus().equals(CustomerStatus.FREE)) {
				cs.add(c);
			}
		}
		return cs;
	}

	@Override
	public Cursor<Customer> getOvertimeCustomers(int customerRetainTime) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer find(String source, String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
