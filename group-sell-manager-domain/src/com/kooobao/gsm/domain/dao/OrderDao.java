package com.kooobao.gsm.domain.dao;

import java.util.List;

import com.kooobao.common.domain.dao.Dao;
import com.kooobao.gsm.domain.entity.order.Order;

public interface OrderDao extends Dao<Order> {

	public List<Order> searchOrders(String groupName, String customer,
			String contact, String[] status);
}
