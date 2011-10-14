package com.kooobao.gsm.service;

import java.math.BigDecimal;

import com.kooobao.gsm.domain.entity.order.DeliveryStatus;
import com.kooobao.gsm.domain.entity.order.Order;
import com.kooobao.gsm.domain.entity.order.OrderItem;
import com.kooobao.gsm.domain.entity.rule.DiscountRule;

public class OrderService {

	public static void setOrderTotalAmount(Order order, DiscountRule rule) {
		BigDecimal sum = BigDecimal.ZERO;
		for (OrderItem item : order.getItems())
			sum = sum.add(item.getUnitPrice().multiply(
					new BigDecimal(item.getCount())));
		order.setTotalAmount(sum);
	}

	public static void updateOrderPrepareStatus(Order order) {
		if (DeliveryStatus.DELIVERED.name().equals(order.getDeliveryStatus())
				|| DeliveryStatus.PARTIALLY_DELIVERED.name().equals(
						order.getDeliveryStatus())
				|| DeliveryStatus.SHORT_OF_STORAGE.name().equals(
						order.getDeliveryStatus()))
			return;
		order.setDeliveryStatus(DeliveryStatus.NOT_PREPARED.name());
		int sum = 0;
		int prepared = 0;
		for (OrderItem item : order.getItems()) {
			sum += item.getCount();
			prepared += item.getPreparedCount();
		}
		if (sum == prepared) {
			order.setDeliveryStatus(DeliveryStatus.PREPARED.name());
		}
		if (sum > prepared && prepared > 0) {
			order.setDeliveryStatus(DeliveryStatus.PARTIALLY_PREPARED.name());
		}
		if (prepared == 0) {
			order.setDeliveryStatus(DeliveryStatus.NOT_PREPARED.name());
		}
	}

	public static void updateOrderSendStatus(Order order) {
		if (DeliveryStatus.DELIVERED.name().equals(order.getDeliveryStatus())
				|| DeliveryStatus.SHORT_OF_STORAGE.name().equals(
						order.getDeliveryStatus()))
			return;
		int sum = 0;
		int sent = 0;
		for (OrderItem item : order.getItems()) {
			sum += item.getCount();
			sent += item.getPreparedCount();
		}
		if (sum == sent) {
			order.setDeliveryStatus(DeliveryStatus.DELIVERED.name());
		}
		if (sum > sent && sent > 0) {
			order.setDeliveryStatus(DeliveryStatus.PARTIALLY_DELIVERED.name());
		}
		if (sent == 0) {
			order.setDeliveryStatus(DeliveryStatus.NOT_PREPARED.name());
			updateOrderPrepareStatus(order);
		}
	}
}
