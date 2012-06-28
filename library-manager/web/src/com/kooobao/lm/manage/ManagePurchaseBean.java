package com.kooobao.lm.manage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import com.kooobao.common.web.Utilities;
import com.kooobao.common.web.bean.PageSearchBean;
import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.bizflow.BusinessService;
import com.kooobao.lm.bizflow.InsufficientFundException;
import com.kooobao.lm.profile.ManageLoginBean;
import com.kooobao.lm.profile.ProfileService;
import com.kooobao.lm.profile.entity.Operator;
import com.kooobao.lm.profile.entity.Visitor;
import com.kooobao.lm.purchase.PurchaseSearchBean;
import com.kooobao.lm.purchase.PurchaseService;
import com.kooobao.lm.purchase.entity.Purchase;
import com.kooobao.lm.purchase.entity.PurchaseState;

@ManagedBean(name = "managePurchaseBean")
@SessionScoped
public class ManagePurchaseBean extends PageSearchBean {

	@Override
	public String search() {
		if (0 != getPurchaseId()) {
			List<Purchase> trans = new ArrayList<Purchase>();
			long count = 0;
			Purchase t = getPurchaseService().getPurchase(getPurchaseId());
			if (null != t) {
				trans.add(t);
				count = 1;
			}
			setResult(trans);
			setRecordCount(count);
		} else {
			Visitor v = null;
			if (!StringUtils.isEmpty(getVisitorId()))
				v = getProfileService().getVisitor(getVisitorId());
			PurchaseSearchBean tsb = new PurchaseSearchBean();
			tsb.setFromDate(getFromDate());
			tsb.setVisitor(v);
			tsb.setToDate(Utilities.dayEnd(getToDate()));
			tsb.setState(getState());
			tsb.setFrom(getRecordStart());
			tsb.setTo(getRecordStop());
			PageSearchResult<Purchase> ts = getPurchaseService()
					.searchPurchases(tsb);
			setResult(ts.getResult());
			setRecordCount(ts.getCount());
		}
		return "success";
	}

	public String approve() {
		Purchase t = getPurchase();
		ManageLoginBean mlb = findBean("manageLoginBean");
		if (mlb.isLoggedIn()) {
			Operator o = getProfileService().getOperator(mlb.getUserId());
			try {
				getPurchaseService().approvePurchase(t, o);
				reset();
				return search();
			} catch (InsufficientFundException e) {
				addMessage(FacesMessage.SEVERITY_WARN, "余额不足", "用户余额不足，无法通过");
				return "failed";
			}
		} else {
			addMessage(FacesMessage.SEVERITY_FATAL, "尚未登录", "请登录后操作");
			return "failed";
		}
	}

	public String send() {
		if (StringUtils.isEmpty(getExpressNo())) {
			addMessage(FacesMessage.SEVERITY_WARN, "缺少信息", "快递信息尚未填写");
			return "failed";
		}
		Purchase t = getPurchase();
		ManageLoginBean mlb = findBean("manageLoginBean");
		if (mlb.isLoggedIn()) {
			Operator o = getProfileService().getOperator(mlb.getUserId());
			getPurchaseService().sendPurchase(t, o, getExpressNo());
			reset();
			return search();
		} else {
			addMessage(FacesMessage.SEVERITY_FATAL, "尚未登录", "请登录后操作");
			return "failed";
		}
	}

	public String interrupt() {
		if (StringUtils.isEmpty(getComment())) {
			addMessage(FacesMessage.SEVERITY_WARN, "缺少信息", "注释尚未填写");
			return "failed";
		}
		return execute(new ExecuteWithOperator() {
			public void execute(Operator o) {
				Purchase t = getPurchase();
				getPurchaseService().interruptPurchase(t, o, getComment());
			}
		});
	}
	public String reject() {
		if (StringUtils.isEmpty(getComment())) {
			addMessage(FacesMessage.SEVERITY_WARN, "缺少信息", "注释尚未填写");
			return "failed";
		}
		return execute(new ExecuteWithOperator() {
			public void execute(Operator o) {
				Purchase t = getPurchase();
				getPurchaseService().rejectPurchase(t, o, getComment());
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

	protected void reset() {
		setComment(null);
		setExpressNo(null);
	}

	protected Purchase getPurchase() {
		return getPurchaseService().getPurchase(
				Long.parseLong(getParameter("purchase_id")));
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

	@ManagedProperty("#{purchaseService}")
	private PurchaseService purchaseService;

	public PurchaseService getPurchaseService() {
		return purchaseService;
	}

	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

	private long purchaseId;

	private String visitorId;

	private Date fromDate = Utilities.offset(Utilities.dayBegin(), -7);

	private Date toDate = Utilities.dayEnd();

	private PurchaseState state;

	private List<Purchase> result;

	private String expressNo;

	private String comment;

	public long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(long purchaseId) {
		this.purchaseId = purchaseId;
	}

	public List<Purchase> getResult() {
		return result;
	}

	public void setResult(List<Purchase> result) {
		this.result = result;
	}

	public void setState(PurchaseState state) {
		this.state = state;
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

	public String getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(String visitorId) {
		this.visitorId = visitorId;
	}

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

	public PurchaseState getState() {
		return state;
	}

}
