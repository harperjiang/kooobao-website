package com.kooobao.gsm.web.order;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.kooobao.common.web.bean.AbstractBean;

@ManagedBean
@SessionScoped
public class MaintainOrderSuccessBean extends AbstractBean {

	public String another() {
		MaintainOrderBean orderBean = findBean("maintainOrderBean");
		orderBean.setOrder(null);
		return "success";
	}

	public String view() {
		// Get Order Id
		MaintainOrderBean orderBean = findBean("maintainOrderBean");
		long orderId = orderBean.getOrder().getOid();
		ViewOrderBean viewBean = findBean("viewOrderBean");
		viewBean.setOrderId(orderId);
		return "success";
	}
	
	public String backsearch() {
		return "success";
	}

}
