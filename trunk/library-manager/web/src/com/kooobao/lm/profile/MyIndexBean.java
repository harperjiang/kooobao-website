package com.kooobao.lm.profile;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import com.kooobao.authcenter.web.bean.LoginBean;
import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.lm.bizflow.TransactionService;
import com.kooobao.lm.bizflow.entity.Transaction;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.profile.entity.Visitor;

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
		expiredBookCount = getTransactionService().getExpiredTransactionCount(
				visitor);

		activeTransactions = getTransactionService().getActiveTransactions(
				visitor);
		borrowedBookCount = activeTransactions.size();
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

	private long expiredBookCount;

	private int borrowedBookCount;

	private List<Transaction> activeTransactions;

	private List<Book> recommendBooks;

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	public long getExpiredBookCount() {
		return expiredBookCount;
	}

	public void setExpiredBookCount(long expiredBookCount) {
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
