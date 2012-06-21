package com.kooobao.lm.purchase;

import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.purchase.entity.Purchase;

public interface PurchaseService {

	Purchase savePurchase(Purchase purchase);

	Purchase create(Purchase purchase);

	PageSearchResult<Purchase> searchPurchases(PurchaseSearchBean searchBean);

	Purchase getPurchase(long purchaseId);

}
