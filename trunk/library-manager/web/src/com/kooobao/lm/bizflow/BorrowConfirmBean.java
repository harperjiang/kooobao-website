package com.kooobao.lm.bizflow;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.kooobao.authcenter.web.bean.LoginBean;
import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.lm.bizflow.entity.DeliveryMethod;
import com.kooobao.lm.bizflow.entity.Transaction;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.profile.ProfileService;
import com.kooobao.lm.profile.entity.Address;
import com.kooobao.lm.profile.entity.Visitor;
import com.kooobao.lm.rule.RuleService;

@ManagedBean(name = "borrowConfirmBean")
@SessionScoped
public class BorrowConfirmBean extends AbstractBean {

	public void onPageLoad() {
		Visitor visitor = getCurrentVisitor();
		if (null != visitor && null == address)
			setAddress(getCurrentVisitor().getAddress());
		if (null != visitor) {
			setExpireDate(getRuleService().getExpirePeriod(visitor));
			setPenalty(getRuleService().getPenalty(visitor));
		}
	}

	public String changeAddress() {
		return "success";
	}

	public String confirm() {
		if (null == getCurrentVisitor()
				|| CollectionUtils.isEmpty(getBorrowed())) {
			addMessage(FacesMessage.SEVERITY_WARN, "用户未登录", "您尚未登录,请先登录");
			return "failed";
		}
		for (Book book : borrowed) {
			Transaction transaction = new Transaction();
			// Fill transaction object
			transaction.setVisitor(getCurrentVisitor());
			transaction.setBook(book);
			transaction.setDelivery(getDelivery());
			transaction.setAddress(address);
			transaction.setComment(getComment());
			getTransactionService().requestBorrow(transaction);
		}
		reset();
		return "success";
	}

	protected void reset() {
		borrowed = new ArrayList<Book>();
		currentVisitor = null;
	}

	private Date expireDate;

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	private BigDecimal penalty;

	public BigDecimal getPenalty() {
		return penalty;
	}

	public void setPenalty(BigDecimal penalty) {
		this.penalty = penalty;
	}

	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	private List<Book> borrowed;

	public List<Book> getBorrowed() {
		return borrowed;
	}

	public void setBorrowed(List<Book> borrowed) {
		this.borrowed = borrowed;
	}

	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	private DeliveryMethod delivery;

	public DeliveryMethod getDelivery() {
		return delivery;
	}

	public void setDelivery(DeliveryMethod delivery) {
		this.delivery = delivery;
	}

	private Visitor currentVisitor;

	public Visitor getCurrentVisitor() {
		String userId = LoginBean.getCurrentUser();
		if (!StringUtils.isEmpty(userId)
				&& (null == currentVisitor || !currentVisitor.getId().equals(
						userId))) {
			currentVisitor = getProfileService().getVisitor(userId);
		}
		return currentVisitor;
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

	@ManagedProperty("#{ruleService}")
	private RuleService ruleService;

	public RuleService getRuleService() {
		return ruleService;
	}

	public void setRuleService(RuleService ruleService) {
		this.ruleService = ruleService;
	}

}
