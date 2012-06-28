package com.kooobao.lm.bizflow;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.lm.bizflow.entity.ExpireRecord;
import com.kooobao.lm.bizflow.entity.Transaction;
import com.kooobao.lm.bizflow.entity.TransactionState;
import com.kooobao.lm.book.entity.Comment;
import com.kooobao.lm.profile.LoginBean;

@ManagedBean(name = "transactionBean")
@SessionScoped
public class TransactionBean extends AbstractBean {

	@Override
	public void onPageLoad() {
		String tranIdParam = getParameter("tran_id");
		try {
			if ((!StringUtils.isEmpty(tranIdParam))) {
				long tranId = Long.parseLong(tranIdParam);
				Transaction t = getTransactionService().getTransaction(tranId);
				setTran(t);
			}
			if (null == getTran()) {
				navigate("not_found");
				return;
			}
			LoginBean lb = findBean("loginBean");
			if (!lb.isLoggedIn()
					|| !lb.getUserId().equals(getTran().getVisitor().getId())) {
				// Unauthorized
				addMessage(FacesMessage.SEVERITY_ERROR, "无权限", "您无权查看该订单");
				setTran(null);
				return;
			} else {
				setExpireRecord(getTransactionService().findExpireRecord(
						getTran()));
			}
		} catch (NumberFormatException e) {
			navigate("not_found");
		} catch (Exception e) {
			LoggerFactory.getLogger(getClass()).error("Cannot find transaction", e);
			navigate("not_found");
		}
	}

	public String cancel() {
		getTransactionService().cancel(tran, cancelSelect + ":" + cancelReason);
		addMessage(FacesMessage.SEVERITY_INFO, "操作成功", "您的订单已经取消");
		return "success";
	}

	public boolean isCanCancel() {
		return getTran().getState() == TransactionState.BORROW_REQUESTED
				|| getTran().getState() == TransactionState.BORROW_APPROVED;
	}

	public boolean isCanComment() {
		return getTran().getState() == TransactionState.RETURN_RECEIVED
				&& 0 == (getTran().getRating());
	}

	public boolean isCanSendback() {
		return getTran().getState() == TransactionState.RETURN_WAIT
				|| getTran().getState() == TransactionState.RETURN_EXPIRED;
	}

	public String saveComment() {
		if (StringUtils.isEmpty(getComment()) || getRating() == 0) {
			addMessage(FacesMessage.SEVERITY_WARN, "未评级或无内容", "请您做出评分并填写评论");
			return "failed";
		}
		if (getComment().length() > 500) {
			addMessage(FacesMessage.SEVERITY_WARN, "评论过长", "您的评论不能超过500字");
			return "failed";
		}
		if (getRating() > 5 || getRating() < 0) {
			addMessage(FacesMessage.SEVERITY_WARN, "数字出错", "您是黑客吗");
			return "failed";
		}
		Comment cmt = new Comment();
		cmt.setRating(getRating());
		cmt.setContent(getComment());
		setTran(getTransactionService().addComment(getTran(), cmt));
		addMessage(FacesMessage.SEVERITY_INFO, "评论成功", "感谢您的评论，我们将赠送您5豆丁的积分");
		return "success";
	}

	public String sendback() {
		String expressCompany = "其他".equals(getExpressCompany()) ? getExpressNo()
				: getExpressCompany();
		if (StringUtils.isEmpty(expressCompany)
				|| StringUtils.isEmpty(expressNo)) {
			addMessage(FacesMessage.SEVERITY_WARN, "信息不全", "请填写快递公司及快递单号");
			return "failed";
		}
		setTran(getTransactionService().sendReturn(getTran(), null,
				expressCompany + expressNo));
		return "success";
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

	private String cancelSelect;

	public String getCancelSelect() {
		return cancelSelect;
	}

	public void setCancelSelect(String cancelSelect) {
		this.cancelSelect = cancelSelect;
	}

	private String cancelReason;

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	private int rating;

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	private String expressCompany;

	public String getExpressCompany() {
		return expressCompany;
	}

	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}

	private String expressCompanyName;

	public String getExpressCompanyName() {
		return expressCompanyName;
	}

	public void setExpressCompanyName(String expressCompanyName) {
		this.expressCompanyName = expressCompanyName;
	}

	private String expressNo;

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
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
