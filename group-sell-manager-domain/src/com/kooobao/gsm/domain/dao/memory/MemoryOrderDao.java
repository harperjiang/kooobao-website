package com.kooobao.gsm.domain.dao.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.kooobao.common.domain.dao.AbstractMemoryDao;
import com.kooobao.gsm.domain.dao.OrderDao;
import com.kooobao.gsm.domain.entity.order.Order;

public class MemoryOrderDao extends AbstractMemoryDao<Order> implements
		OrderDao {

	public List<Order> searchOrders(SearchBean search) {
		search.validate();

		Map<String, String> statusMap = new HashMap<String, String>();
		if (null != search.getDeliveryStatus())
			for (String s : search.getDeliveryStatus())
				statusMap.put(s, s);

		List<Order> result = new ArrayList<Order>();
		for (Order order : getStorage().values()) {
			if ((StringUtils.isEmpty(search.getCustomer()) || search
					.getCustomer().equals(order.getCustomer()))
					&& (StringUtils.isEmpty(search.getContact()) || search
							.getCustomer().equals(order.getContact().getName()))
					&& (search.getDeliveryStatus() == null || statusMap
							.containsKey(order.getDeliveryStatus()))) {
				result.add(order);
			}
		}
		return result;
	}

	public Order rollback(Order order) {
		// TODO Auto-generated method stub
		return null;
	}
}
