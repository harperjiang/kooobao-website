package com.kooobao.lm.profile;

import java.util.List;

import com.kooobao.common.web.bean.PageSearchBean;
import com.kooobao.lm.bizflow.TransactionService;
import com.kooobao.lm.bizflow.entity.FavoriteRecord;

public class MyFavBean extends PageSearchBean {

	@Override
	public void onPageLoad() {
		search();
	}

	@Override
	public String search() {
		MyIndexBean myIndexBean = findBean("myIndexBean");
		result = getTransactionService().searchFavoriteRecords(
				myIndexBean.getVisitor());
		return "success";
	}

	public String delete() {
		MyIndexBean myIndexBean = findBean("myIndexBean");
		long bookOid = 0;
		try {
			bookOid = Long.parseLong(getParameter("book_id"));
			getTransactionService().deleteFavorite(myIndexBean.getVisitor(),
					bookOid);
		} catch (Exception e) {
			return "failed";
		}
		// Remove from list,avoiding a research
		for (int i = 0; i < result.size(); i++) {
			if (bookOid == result.get(i).getFavorite().getOid()) {
				result.remove(i);
				break;
			}
		}
		return "success";
	}

	private TransactionService transactionService;

	public TransactionService getTransactionService() {
		return transactionService;
	}

	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	private List<FavoriteRecord> result;

	public List<FavoriteRecord> getResult() {
		return result;
	}

	public void setResult(List<FavoriteRecord> result) {
		this.result = result;
	}

}
