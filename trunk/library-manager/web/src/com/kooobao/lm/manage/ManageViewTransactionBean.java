package com.kooobao.lm.manage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.lm.bizflow.TransactionService;
import com.kooobao.lm.bizflow.entity.ExpireRecord;
import com.kooobao.lm.bizflow.entity.Transaction;
import com.kooobao.lm.bizflow.entity.TransactionState;

@ManagedBean(name = "manageViewTransactionBean")
@SessionScoped
public class ManageViewTransactionBean extends AbstractBean {

	@Override
	public void onPageLoad() {
		String tranIdParam = getParameter("tran_id");
		try {
			long tranId = Long.parseLong(tranIdParam);
			Transaction t = getTransactionService().getTransaction(tranId);
			setTran(t);
			setExpireRecord(getTransactionService().findExpireRecord(getTran()));
		} catch (Exception e) {
			navigate("not_found");
		}
	}

	public String cancel() {
		getTransactionService().cancel(tran, cancelReason);
		return "success";
	}

	public boolean isCanCancel() {
		return getTran().getState() == TransactionState.BORROW_REQUESTED
				|| getTran().getState() == TransactionState.BORROW_APPROVED;
	}

	public boolean isCanComment() {
		return getTran().getState() == TransactionState.RETURN_RECEIVED
				&& StringUtils.isEmpty(getTran().getComment());
	}

	private Transaction tran;

	public Transaction getTran() {
		return tran;
	}

	public void setTran(Transaction tran) {
		this.tran = tran;
	}

	private ExpireRecord expireRecord;

	public ExpireRecord getExpireRecord() {
		return expireRecord;
	}

	public void setExpireRecord(ExpireRecord expireRecord) {
		this.expireRecord = expireRecord;
	}

	private String cancelReason;

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	@ManagedProperty("#{transactionService}")
	private TransactionService transactionService;

	public TransactionService getTransactionService() {
		return transactionService;
	}

	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}
}
