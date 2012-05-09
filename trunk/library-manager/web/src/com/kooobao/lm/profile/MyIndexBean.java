package com.kooobao.lm.profile;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import com.kooobao.authcenter.web.bean.LoginBean;
import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.lm.bizflow.Transaction;
import com.kooobao.lm.bizflow.TransactionService;
import com.kooobao.lm.book.Book;

@ManagedBean(name = "myIndexBean")
@SessionScoped
public class MyIndexBean extends AbstractBean {

	public Visitor getVisitor() {
		String userId = LoginBean.getCurrentUser();
		if (!StringUtils.isEmpty(userId)
				&& (null == visitor || !userId.equals(visitor.getId()))) {
			visitor = getProfileService().getVisitor(userId);
		}
		return visitor;
	}

	public void onPageLoad() {
		// Visitor
		getVisitor();
		//
		expiredBookCount = getTransactionService().getExpiredBookCount(visitor);
		borrowedBookCount = getTransactionService().getBorrowedBookCount(
				visitor);
		activeTransactions = getTransactionService().getActiveTransactions(
				visitor);
		recommendBooks = getTransactionService().getRecommendBooks(visitor);
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

	private Visitor visitor;

	private int expiredBookCount;

	private int borrowedBookCount;

	private List<Transaction> activeTransactions;

	private List<Book> recommendBooks;

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	public int getExpiredBookCount() {
		return expiredBookCount;
	}

	public void setExpiredBookCount(int expiredBookCount) {
		this.expiredBookCount = expiredBookCount;
	}

	public int getBorrowedBookCount() {
		return borrowedBookCount;
	}

	public void setBorrowedBookCount(int borrowedBookCount) {
		this.borrowedBookCount = borrowedBookCount;
	}

	public List<Transaction> getActiveTransactions() {
		return activeTransactions;
	}

	public void setActiveTransactions(List<Transaction> activeTransactions) {
		this.activeTransactions = activeTransactions;
	}

	public List<Book> getRecommendBooks() {
		return recommendBooks;
	}

	public void setRecommendBooks(List<Book> recommendBooks) {
		this.recommendBooks = recommendBooks;
	}

}
