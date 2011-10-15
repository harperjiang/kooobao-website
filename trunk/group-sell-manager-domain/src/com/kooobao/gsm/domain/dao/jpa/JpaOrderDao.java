package com.kooobao.gsm.domain.dao.jpa;

import java.util.List;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.gsm.domain.dao.OrderDao;
import com.kooobao.gsm.domain.entity.order.Order;

public class JpaOrderDao extends AbstractJpaDao<Order> implements OrderDao {

	@Override
	public List<Order> searchOrders(String groupName, String customer,
			String contact, String[] status) {
		// TODO Auto-generated method stub
		return null;
	}

}
