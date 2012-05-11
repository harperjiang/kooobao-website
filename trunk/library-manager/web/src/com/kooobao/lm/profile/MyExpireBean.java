package com.kooobao.lm.profile;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.kooobao.common.web.bean.PageSearchBean;
import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.bizflow.ExpireRecord;
import com.kooobao.lm.bizflow.TransactionService;
import com.kooobao.lm.bizflow.TransactionService.ExpireRecordSearchBean;

@ManagedBean(name = "myExpireBean")
@SessionScoped
public class MyExpireBean extends PageSearchBean {

	private List<ExpireRecord> result;

	private ExpireRecordSearchBean searchBean;

	@ManagedProperty("#{transactionService}")
	private TransactionService transactionService;

	@Override
	public String search() {
		MyIndexBean myIndexBean = findBean("myIndexBean");
		searchBean.setFrom(getRecordStart());
		searchBean.setTo(getRecordStop());
		PageSearchResult<ExpireRecord> pageResult = getTransactionService()
				.searchExpiredRecords(myIndexBean.getVisitor(), searchBean);
		setResult(pageResult.getResult());
		setPageCount(pageResult.getPageCount());
		return null;
	}

	public List<ExpireRecord> getResult() {
		return result;
	}

	public void setResult(List<ExpireRecord> result) {
		this.result = result;
	}

	public TransactionService getTransactionService() {
		return transactionService;
	}

	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	public ExpireRecordSearchBean getSearchBean() {
		return searchBean;
	}

}