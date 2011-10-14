package com.kooobao.gsm.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.gsm.domain.entity.delivery.ExpressCompany;

@ManagedBean
@ApplicationScoped
public class SupportDataBean extends AbstractBean {

	private List<String> expressCompanies;

	public List<String> getExpressCompanies() {
		if (null == expressCompanies) {
			expressCompanies = new ArrayList<String>();
			for (ExpressCompany comp : ExpressCompany.values())
				expressCompanies.add(comp.name());
		}
		return expressCompanies;
	}

}
