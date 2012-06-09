package com.kooobao.lm.manage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import com.kooobao.common.web.Utilities;
import com.kooobao.common.web.bean.PageSearchBean;
import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.bizflow.BusinessService;
import com.kooobao.lm.bizflow.InsufficientFundException;
import com.kooobao.lm.bizflow.InsufficientStockException;
import com.kooobao.lm.bizflow.TransactionService;
import com.kooobao.lm.bizflow.TransactionService.TransactionSearchBean;
import com.kooobao.lm.bizflow.entity.Transaction;
import com.kooobao.lm.bizflow.entity.TransactionState;
import com.kooobao.lm.profile.ManageLoginBean;
import com.kooobao.lm.profile.ProfileService;
import com.kooobao.lm.profile.entity.Operator;
import com.kooobao.lm.profile.entity.Visitor;

@ManagedBean(name = "manageTransactionBean")
@SessionScoped
public class ManageTransactionBean extends PageSearchBean {

	@Override
	public String search() {
		if (0 != getTranId()) {
			List<Transaction> trans = new ArrayList<Transaction>();
			long count = 0;
			Transaction t = getTransactionService().getTransaction(getTranId());
			if (null != t) {
				trans.add(t);
				count = 1;
			}
			setTrans(trans);
			setRecordCount(count);
		} else {
			Visitor v = null;
			if (!StringUtils.isEmpty(getVisitorId()))
				v = getProfileService().getVisitor(getVisitorId());
			TransactionSearchBean tsb = new TransactionSearchBean();
			tsb.setFromDate(getFromDate());
			tsb.setToDate(Utilities.dayEnd(getToDate()));
			tsb.setState(getState());
			tsb.setFrom(getRecordStart());
			tsb.setTo(getRecordStop());
			PageSearchResult<Transaction> ts = getTransactionService()
					.findTransaction(v, tsb);
			setTrans(ts.getResult());
			setRecordCount(ts.getCount());
		}
		return "success";
	}

	public String approve() {
		Transaction t = getTransaction();
		ManageLoginBean mlb = findBean("manageLoginBean");
		if (mlb.isLoggedIn()) {
			Operator o = getProfileService().getOperator(mlb.getUserId());
			try {
				getTransactionService().approveBorrow(t, o);
				reset();
				return search();
			} catch (IllegalStateException e) {
				addMessage(FacesMessage.SEVERITY_WARN, "库存未更新", e.getMessage());
				return "failed";
			} catch (InsufficientFundException e) {
				addMessage(FacesMessage.SEVERITY_WARN, "余额不足", "用户余额不足，无法通过");
				return "failed";
			}
		} else {
			addMessage(FacesMessage.SEVERITY_FATAL, "尚未登录", "请登录后操作");
			return "failed";
		}
	}

	public String reserveStock() {
		Transaction t = getTransaction();
		if (null != t)
			try {
				getBusinesService().reserveStock(t);
			} catch (InsufficientStockException e) {
				addMessage(FacesMessage.SEVERITY_WARN, "库存不足");
				return "failed";
			}
		else
			addMessage(FacesMessage.SEVERITY_WARN, "Transaction Id missing");
		return search();
	}

	public String send() {
		if (StringUtils.isEmpty(getExpressNo())) {
			addMessage(FacesMessage.SEVERITY_WARN, "缺少信息", "快递信息尚未填写");
			return "failed";
		}
		Transaction t = getTransaction();
		ManageLoginBean mlb = findBean("manageLoginBean");
		if (mlb.isLoggedIn()) {
			Operator o = getProfileService().getOperator(mlb.getUserId());
			getTransactionService().sendBorrow(t, o, getExpressNo());
			reset();
			return search();
		} else {
			addMessage(FacesMessage.SEVERITY_FATAL, "尚未登录", "请登录后操作");
			return "failed";
		}
	}

	public String receiveBorrow() {
		return execute(new ExecuteWithOperator() {
			public void execute(Operator o) {
				Transaction t = getTransaction();
				getBusinesService().assumeReceived(t);
			}
		});
	}

	public String recordReturn() {
		if (StringUtils.isEmpty(getExpressNo())) {
			addMessage(FacesMessage.SEVERITY_WARN, "缺少信息", "快递信息尚未填写");
			return "failed";
		}
		return execute(new ExecuteWithOperator() {
			public void execute(Operator o) {
				Transaction t = getTransaction();
				getTransactionService().sendReturn(t, o, getExpressNo());
			}
		});
	}

	public String receiveReturn() {
		return execute(new ExecuteWithOperator() {
			public void execute(Operator o) {
				Transaction t = getTransaction();
				getTransactionService().confirmReturn(t, o, getComment());
			}
		});
	}

	public String recordExpire() {
		return execute(new ExecuteWithOperator() {
			public void execute(Operator o) {
				Transaction t = getTransaction();
				getBusinesService().expireTransaction(t);
			}
		});
	}

	public String interrupt() {
		if (StringUtils.isEmpty(getComment())) {
			addMessage(FacesMessage.SEVERITY_WARN, "缺少信息", "注释尚未填写");
			return "failed";
		}
		return execute(new ExecuteWithOperator() {
			public void execute(Operator o) {
				Transaction t = getTransaction();
				getTransactionService().interrupt(t, o, getComment());
			}
		});
	}

	protected String execute(ExecuteWithOperator ewo) {
		Operator o = getOperator();
		if (null != o) {
			ewo.execute(o);
			reset();
			return search();
		} else {
			addMessage(FacesMessage.SEVERITY_FATAL, "尚未登录", "请登录后操作");
			return "failed";
		}
	}

	protected Operator getOperator() {
		ManageLoginBean mlb = findBean("manageLoginBean");
		if (mlb.isLoggedIn()) {
			return getProfileService().getOperator(mlb.getUserId());
		}
		return null;
	}

	protected interface ExecuteWithOperator {
		public void execute(Operator o);
	}

	public String addComment() {
		Operator o = getOperator();
		Validate.notNull(o);
		Transaction t = getTransaction();
		t.addComment(getTranComment(), o);
		getTransactionService().saveTransaction(t);
		return search();
	}

	protected void reset() {
		setComment(null);
		setExpressNo(null);
		setTranComment(null);
	}

	protected Transaction getTransaction() {
		return getTransactionService().getTransaction(
				Long.parseLong(getParameter("tran_id")));
	}

	@ManagedProperty("#{businessService}")
	private BusinessService businesService;

	public BusinessService getBusinesService() {
		return businesService;
	}

	public void setBusinesService(BusinessService businesService) {
		this.businesService = businesService;
	}

	@ManagedProperty("#{profileService}")
	private ProfileService profileService;

	public ProfileService getProfileService() {
		return profileService;
	}

	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}

	@ManagedProperty("#{transactionService}")
	private TransactionService transactionService;

	public TransactionService getTransactionService() {
		return transactionService;
	}

	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	private long tranId;

	private String visitorId;

	private Date fromDate = Utilities.offset(Utilities.dayBegin(), -7);

	private Date toDate = Utilities.dayEnd();

	private TransactionState state;

	private List<Transaction> trans;

	private Transaction selected;

	private String expressNo;

	private String tranComment;

	public String getTranComment() {
		return tranComment;
	}

	public void setTranComment(String tranComment) {
		this.tranComment = tranComment;
	}

	public long getTranId() {
		return tranId;
	}

	public void setTranId(long tranId) {
		this.tranId = tranId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public TransactionState getState() {
		return state;
	}

	public void setState(TransactionState state) {
		this.state = state;
	}

	public List<Transaction> getTrans() {
		return trans;
	}

	public void setTrans(List<Transaction> trans) {
		this.trans = trans;
	}

	public Transaction getSelected() {
		return selected;
	}

	public void setSelected(Transaction selected) {
		this.selected = selected;
	}

	public String getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(String visitorId) {
		this.visitorId = visitorId;
	}

	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

}
