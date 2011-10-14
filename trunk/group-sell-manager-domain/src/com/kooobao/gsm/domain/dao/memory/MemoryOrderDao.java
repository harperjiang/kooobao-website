package com.kooobao.gsm.domain.dao.memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import com.kooobao.common.domain.dao.AbstractMemoryDao;
import com.kooobao.gsm.domain.dao.OrderDao;
import com.kooobao.gsm.domain.entity.order.Order;

public class MemoryOrderDao extends AbstractMemoryDao<Order> implements
		OrderDao {

	@Override
	public List<Order> searchOrders(String groupName, String customer,
			String contact, String[] deliveryStatus) {
		Map<String, String> statusMap = new HashMap<String, String>();
		if (null != deliveryStatus)
			for (String s : deliveryStatus)
				statusMap.put(s, s);
		Validate.isTrue(deliveryStatus != null
				|| (!StringUtils.isEmpty(customer) || !StringUtils
						.isEmpty(contact)));
		List<Order> result = new ArrayList<Order>();
		for (Order order : getStorage().values()) {
			if ((StringUtils.isEmpty(customer) || customer.equals(order
					.getCustomer()))
					&& (StringUtils.isEmpty(contact) || customer.equals(order
							.getContact().getName()))
					&& (deliveryStatus == null || statusMap.containsKey(order
							.getDeliveryStatus()))) {
				result.add(order);
			}
		}
		return result;
	}
}
