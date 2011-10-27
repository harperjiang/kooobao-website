package com.kooobao.gsm.web;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.gsm.web.order.MaintainOrderBean;

@ManagedBean
@ApplicationScoped
public class InnerIndexBean extends AbstractBean {

	public String createOrder() {
		MaintainOrderBean mob = findBean("maintainOrderBean");
		mob.setOrderId(0);
		return "success";
	}
}
