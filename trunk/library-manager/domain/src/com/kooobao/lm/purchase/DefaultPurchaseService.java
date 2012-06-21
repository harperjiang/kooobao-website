package com.kooobao.lm.purchase;

import com.kooobao.common.web.bean.PageSearchResult;
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

}
