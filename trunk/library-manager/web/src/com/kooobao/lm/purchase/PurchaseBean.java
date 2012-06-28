package com.kooobao.lm.purchase;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.lm.profile.LoginBean;
import com.kooobao.lm.purchase.entity.Purchase;
import com.kooobao.lm.purchase.entity.PurchaseState;

@ManagedBean(name = "purchaseBean")
@SessionScoped
public class PurchaseBean extends AbstractBean {

	@Override
	public void onPageLoad() {
		String purchaseIdParam = getParameter("purchase_id");
		try {
			if ((!StringUtils.isEmpty(purchaseIdParam))) {
				long pId = Long.parseLong(purchaseIdParam);
				Purchase p = getPurchaseService().getPurchase(pId);
				setPurchase(p);
			}
			if (null == getPurchase()) {
				navigate("not_found");
				return;
			}
			LoginBean lb = findBean("loginBean");
			if (!lb.isLoggedIn()
					|| !lb.getUserId().equals(
							getPurchase().getVisitor().getId())) {
				// Unauthorized
				addMessage(FacesMessage.SEVERITY_ERROR, "无权限", "您无权查看该订单");
				setPurchase(null);
			}
		} catch (NumberFormatException e) {
			navigate("not_found");
		} catch (Exception e) {
			LoggerFactory.getLogger(getClass())
					.error("Cannot find purchase", e);
			navigate("not_found");
		}
	}

	public boolean isCanCancel() {
		return getPurchase() != null
				&& (getPurchase().getState() == PurchaseState.SUBMIT || getPurchase()
						.getState() == PurchaseState.APPROVE);
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
