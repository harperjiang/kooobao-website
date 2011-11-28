package com.kooobao.gsm.service;

import java.math.BigDecimal;

import com.kooobao.gsm.domain.entity.order.DeliveryStatus;
import com.kooobao.gsm.domain.entity.order.Order;
import com.kooobao.gsm.domain.entity.order.OrderItem;
import com.kooobao.gsm.domain.entity.rule.DeliveryAmountRule;
import com.kooobao.gsm.domain.entity.rule.GrossWeightRule;

public class OrderService {

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
		if (DeliveryStatus.SHORT_OF_STORAGE.name().equals(
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

	public static void updateOrderTotal(Order order) {
		BigDecimal sum = BigDecimal.ZERO;
		BigDecimal weight = BigDecimal.ZERO;

		boolean usetotal = (order.getDeliveryStatus()
				.equals(DeliveryStatus.NOT_PREPARED.name()));

		for (OrderItem item : order.getItems()) {

			sum = sum.add(item.getUnitPrice().multiply(
					new BigDecimal(usetotal ? item.getCount() : item
							.getPreparedCount())));
			weight = weight.add(item
					.getProduct()
					.getNetWeight()
					.multiply(
							new BigDecimal(usetotal ? item.getCount() : item
									.getPreparedCount())));
		}
		order.setTotalAmount(sum);
		order.setGrossWeight(weight);
	}

	public static void updateOrderTotalAmount(Order order,
			DeliveryAmountRule daRule, GrossWeightRule gwRule) {
		BigDecimal value = null;
		if (null != gwRule && null != (value = gwRule.getPackageWeight(order)))
			order.setGrossWeight(order.getGrossWeight().add(value));
		if (null != daRule && null != (value = daRule.calculate(order))) {
			order.setTotalAmount(order.getTotalAmount().add(value));
			order.setAdjust(value);
		}

	}
}
