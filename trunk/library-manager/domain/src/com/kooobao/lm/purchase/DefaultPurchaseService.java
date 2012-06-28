package com.kooobao.lm.purchase;

import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.profile.entity.Operator;
import com.kooobao.lm.purchase.dao.PurchaseDao;
import com.kooobao.lm.purchase.entity.Purchase;

public class DefaultPurchaseService implements PurchaseService {

	public Purchase savePurchase(Purchase purchase) {
		return getPurchaseDao().store(purchase);
	}

	public Purchase create(Purchase purchase) {
		purchase.request(null);
		return getPurchaseDao().store(purchase);
	}

	public PageSearchResult<Purchase> searchPurchases(
			PurchaseSearchBean searchBean) {
		return getPurchaseDao().search(searchBean);
	}

	private PurchaseDao purchaseDao;

	public PurchaseDao getPurchaseDao() {
		return purchaseDao;
	}

	public void setPurchaseDao(PurchaseDao purchaseDao) {
		this.purchaseDao = purchaseDao;
	}

	public Purchase getPurchase(long purchaseId) {
		return getPurchaseDao().find(purchaseId);
	}

	public Purchase cancelPurchase(Purchase purchase, String reason) {
		purchase.cancel(reason);
		return getPurchaseDao().store(purchase);
	}

	public Purchase approvePurchase(Purchase p, Operator o) {
		p.approve(o.getId(), null);
		return getPurchaseDao().store(p);
	}

	public Purchase sendPurchase(Purchase p, Operator o, String expressNo) {
		p.sent(o.getId(), null, expressNo);
		return getPurchaseDao().store(p);
	}

	public Purchase interruptPurchase(Purchase p, Operator o, String comment) {
		p.interrupt(o.getId(), comment);
		return getPurchaseDao().store(p);
	}

	public Purchase rejectPurchase(Purchase p, Operator o, String reason) {
		p.reject(o.getId(), reason);
		return getPurchaseDao().store(p);
	}

}
