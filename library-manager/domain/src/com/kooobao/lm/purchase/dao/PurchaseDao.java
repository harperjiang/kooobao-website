package com.kooobao.lm.purchase.dao;

import com.kooobao.common.domain.dao.Dao;
import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.purchase.PurchaseSearchBean;
import com.kooobao.lm.purchase.entity.Purchase;

public interface PurchaseDao extends Dao<Purchase> {

	PageSearchResult<Purchase> search(PurchaseSearchBean searchBean);

}
