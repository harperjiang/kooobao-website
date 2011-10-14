package com.kooobao.gsm.web.delivery;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.gsm.domain.entity.delivery.Delivery;

@ManagedBean
@SessionScoped
public class SendDeliveryBean extends AbstractBean {

	private Delivery delivery;

	public String confirm() {
		return "success";
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

}
