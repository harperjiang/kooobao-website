package com.kooobao.lm.profile;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.kooobao.common.web.Utilities;
import com.kooobao.common.web.bean.PageSearchBean;
import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.purchase.PurchaseSearchBean;
import com.kooobao.lm.purchase.PurchaseService;
import com.kooobao.lm.purchase.entity.Purchase;

@ManagedBean(name = "myPurchaseBean")
@SessionScoped
public class MyPurchaseBean extends PageSearchBean {

	private PurchaseSearchBean searchBean = new PurchaseSearchBean();

	private List<Purchase> result;

	@Override
	public String search() {
		MyIndexBean myIndexBean = findBean("myIndexBean");
		searchBean.setVisitor(myIndexBean.getVisitor());
		searchBean.setFrom(getRecordStart());
		searchBean.setTo(getRecordStop());
		if (null != searchBean.getFromDate())
			searchBean
					.setFromDate(Utilities.dayBegin(searchBean.getFromDate()));
		if (null != searchBean.getToDate())
			searchBean.setToDate(Utilities.dayEnd(searchBean.getToDate()));
		PageSearchResult<Purchase> pageResult = getPurchaseService()
				.searchPurchases(searchBean);
		setResult(pageResult.getResult());
		setRecordCount(pageResult.getCount());
		return "success";
	}

	public PurchaseSearchBean getSearchBean() {
		return searchBean;
	}

	public List<Purchase> getResult() {
		return result;
	}

	public void setResult(List<Purchase> result) {
		this.result = result;
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
