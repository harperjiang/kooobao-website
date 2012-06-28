package com.kooobao.lm.purchase;

import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.profile.entity.Operator;
import com.kooobao.lm.purchase.entity.Purchase;

public interface PurchaseService {

	Purchase savePurchase(Purchase purchase);

	Purchase create(Purchase purchase);

	PageSearchResult<Purchase> searchPurchases(PurchaseSearchBean searchBean);

	Purchase getPurchase(long purchaseId);

	Purchase cancelPurchase(Purchase purchase, String string);

	Purchase approvePurchase(Purchase t, Operator o);

	Purchase sendPurchase(Purchase t, Operator o, String expressNo);

	Purchase interruptPurchase(Purchase t, Operator o, String comment);

	Purchase rejectPurchase(Purchase t, Operator o, String comment);

}
