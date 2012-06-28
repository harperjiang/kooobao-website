package com.kooobao.lm.manage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.lm.purchase.PurchaseService;
import com.kooobao.lm.purchase.entity.Purchase;

@ManagedBean(name = "manageViewPurchaseBean")
@SessionScoped
public class ManageViewPurchaseBean extends AbstractBean {

	@Override
	public void onPageLoad() {
		String purchaseIdParam = getParameter("purchase_id");
		try {
			long purId = Long.parseLong(purchaseIdParam);
			Purchase t = getPurchaseService().getPurchase(purId);
			setPurchase(t);
		} catch (Exception e) {
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
