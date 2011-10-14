package com.kooobao.gsm.web.delivery;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIData;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.gsm.domain.entity.delivery.Delivery;

@ManagedBean
@SessionScoped
public class SendDeliveryBean extends AbstractBean {

	private List<Delivery> deliveries;

	public List<Delivery> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(List<Delivery> deliveries) {
		this.deliveries = deliveries;
	}
	
	public String send() {
		UIData dataTable = (UIData) getComponent("dataTable");
		Delivery select = (Delivery) dataTable.getRowData();
		return "success";
	}
}
