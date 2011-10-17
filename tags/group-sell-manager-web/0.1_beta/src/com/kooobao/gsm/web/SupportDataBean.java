package com.kooobao.gsm.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import com.kooobao.common.domain.entity.StatusUtils;
import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.gsm.domain.entity.delivery.DOStatus;
import com.kooobao.gsm.domain.entity.delivery.ExpressCompany;
import com.kooobao.gsm.domain.entity.order.DeliveryStatus;
import com.kooobao.gsm.domain.entity.order.OrderStatus;
import com.kooobao.gsm.domain.entity.rule.DeliveryMethod;

@ManagedBean
@ApplicationScoped
public class SupportDataBean extends AbstractBean {

	public SupportDataBean() {
		super();
		getExpressCompanies();
		getDeliveryMethod();
		getDeliveryStatus();
		getOrderStatus();
	}

	private List<String> expressCompanies;

	public List<String> getExpressCompanies() {
		if (null == expressCompanies) {
			expressCompanies = new ArrayList<String>();
			for (ExpressCompany comp : ExpressCompany.values())
				expressCompanies.add(comp.name());
		}
		return expressCompanies;
	}

	private List<SelectItem> deliveryStatus;

	public List<SelectItem> getDeliveryStatus() {
		if (null == deliveryStatus) {
			deliveryStatus = createStatusList(DOStatus.class, true);
		}
		return deliveryStatus;
	}

	private List<SelectItem> deliveryMethod;

	public List<SelectItem> getDeliveryMethod() {
		if (null == deliveryMethod)
			deliveryMethod = createStatusList(DeliveryMethod.class, false);
		return deliveryMethod;
	}

	private List<SelectItem> orderStatus;

	private static String[][] STATUS_LIST = new String[][] {
			null,
			new String[] { DeliveryStatus.NOT_PREPARED.name(),
					DeliveryStatus.PARTIALLY_PREPARED.name(),
					DeliveryStatus.PARTIALLY_DELIVERED.name() },
			new String[] { DeliveryStatus.PARTIALLY_PREPARED.name(),
					DeliveryStatus.NOT_PREPARED.name(), },
			new String[] { DeliveryStatus.NOT_PREPARED.name() },
			new String[] { DeliveryStatus.PARTIALLY_PREPARED.name() },
			new String[] { DeliveryStatus.PREPARED.name() },
			new String[] { DeliveryStatus.DELIVERED.name(),
					DeliveryStatus.PARTIALLY_DELIVERED.name() },
			new String[] { DeliveryStatus.DELIVERED.name() },
			new String[] { DeliveryStatus.PARTIALLY_DELIVERED.name() },
			new String[] { OrderStatus.CANCELLED.name() } };

	public String[] translateOrderStatus(int index) {
		return STATUS_LIST[index];
	};

	public List<SelectItem> getOrderStatus() {
		if (null == orderStatus) {
			orderStatus = new ArrayList<SelectItem>();
			orderStatus.add(new SelectItem(0, "(所有状态)"));
			orderStatus.add(new SelectItem(1, "未完成"));
			orderStatus.add(new SelectItem(2, "未完全备货"));
			orderStatus.add(new SelectItem(3, "|+ 未备货"));
			orderStatus.add(new SelectItem(4, "|+ 部分备货"));
			orderStatus.add(new SelectItem(5, "已备货"));
			orderStatus.add(new SelectItem(6, "已发货"));
			orderStatus.add(new SelectItem(7, "|+ 全部发货"));
			orderStatus.add(new SelectItem(8, "|+ 部分发货"));
			orderStatus.add(new SelectItem(9, "已取消"));
		}
		return orderStatus;
	}

	protected List<SelectItem> createStatusList(
			Class<? extends Enum<?>> enumClass, boolean withAll) {
		return createStatusList(enumClass, withAll, false);
	}

	protected List<SelectItem> createStatusList(
			Class<? extends Enum<?>> enumClass, boolean withAll,
			boolean useObject) {
		List<SelectItem> items = new ArrayList<SelectItem>();
		if (withAll) {
			items.add(new SelectItem(null, "(所有状态)"));
		}
		for (Enum<?> en : enumClass.getEnumConstants()) {
			SelectItem sel = new SelectItem();
			sel.setLabel(StatusUtils.text(en));
			sel.setValue(useObject ? en : en.name());
			items.add(sel);
		}
		return items;
	}

}
