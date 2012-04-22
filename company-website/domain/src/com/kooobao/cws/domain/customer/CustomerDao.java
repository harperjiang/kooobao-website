package com.kooobao.cws.domain.customer;

import com.kooobao.common.domain.dao.Dao;

public interface CustomerDao extends Dao<Customer>{

	public Customer findByEmail(String email);
}
