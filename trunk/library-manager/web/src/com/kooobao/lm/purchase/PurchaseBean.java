package com.kooobao.lm.purchase;

import javax.faces.bean.ManagedProperty;

import org.apache.commons.lang.StringUtils;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.lm.purchase.entity.Purchase;

public class PurchaseBean extends AbstractBean {

	@Override
	public void onPageLoad() {
		String pid = getParameter("purchase_id");
		if (StringUtils.isEmpty(pid)) {
			navigate("not_found");
		}
		try {
			long purchaseId = Long.parseLong(pid);
			purchase = getPurchaseService().getPurchase(purchaseId);
		} catch (NumberFormatException e) {
			navigate("not_found");
		}
	}

	private Purchase purchase;

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	@ManagedProperty("#{purchaseService}")
	private PurchaseService purchaseService;

	public PurchaseService getPurchaseService() {
		return purchaseService;
	}

	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

}
