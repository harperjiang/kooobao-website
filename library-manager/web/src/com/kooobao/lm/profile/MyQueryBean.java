package com.kooobao.lm.profile;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.kooobao.common.web.bean.PageSearchBean;
import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.bizflow.SearchBean;
import com.kooobao.lm.bizflow.TransactionService;
import com.kooobao.lm.bizflow.TransactionService.TransactionSearchBean;
import com.kooobao.lm.bizflow.entity.Transaction;

@ManagedBean(name = "myQueryBean")
@SessionScoped
public class MyQueryBean extends PageSearchBean {

	private TransactionSearchBean searchBean = new TransactionSearchBean();

	private List<Transaction> result;

	@ManagedProperty("#{transactionService}")
	private TransactionService transactionService;

	@Override
	public String search() {
		MyIndexBean myIndexBean = findBean("myIndexBean");
		searchBean.setFrom(getRecordStart());
		searchBean.setTo(getRecordStop());
		PageSearchResult<Transaction> pageResult = getTransactionService()
				.findTransaction(myIndexBean.getVisitor(), searchBean);
		setResult(pageResult.getResult());
		setRecordCount(pageResult.getCount());
		return "success";
	}

	public SearchBean getSearchBean() {
		return searchBean;
	}

	public void setSearchBean(TransactionSearchBean searchBean) {
		this.searchBean = searchBean;
	}

	public List<Transaction> getResult() {
		return result;
	}

	public void setResult(List<Transaction> result) {
		this.result = result;
	}

	public TransactionService getTransactionService() {
		return transactionService;
	}

	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

}
