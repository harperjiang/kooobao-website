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
import com.kooobao.gsm.domain.entity.rule.DeliveryMethod;

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

	protected List<SelectItem> createStatusList(
			Class<? extends Enum<?>> enumClass, boolean withAll) {
		List<SelectItem> items = new ArrayList<SelectItem>();
		if (withAll) {
			items.add(new SelectItem(null, "(所有状态)"));
		}
		for (Enum<?> en : enumClass.getEnumConstants()) {
			SelectItem sel = new SelectItem();
			sel.setLabel(StatusUtils.text(en));
			sel.setValue(en.name());
			items.add(sel);
		}
		return items;
	}

}
