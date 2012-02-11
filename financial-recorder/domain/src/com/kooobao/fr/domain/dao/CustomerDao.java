package com.kooobao.fr.domain.dao;

import java.util.List;

import com.kooobao.common.domain.dao.Dao;
import com.kooobao.fr.domain.entity.Customer;

public interface CustomerDao extends Dao<Customer> {

	public List<Customer> getRecentCustomer(int limit);
}
