package com.kooobao.lm.bizflow;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import com.kooobao.authcenter.web.bean.LoginBean;
import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.lm.bizflow.entity.Transaction;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.profile.ProfileService;
import com.kooobao.lm.profile.entity.Address;
import com.kooobao.lm.profile.entity.Visitor;

@ManagedBean
@SessionScoped
public class BorrowConfirmBean extends AbstractBean {

	public void onPageLoad() {
		Visitor visitor = getCurrentVisitor();
		if (null != visitor && null == address)
			setAddress(getCurrentVisitor().getAddress());
	}

	public String changeAddress() {
		return "success";
	}

	public String confirm() {
		if (null == getCurrentVisitor()
				|| CollectionUtils.isEmpty(getBorrowed()))
			return "failed";
		for (Book book : borrowed) {
			Transaction transaction = new Transaction();
			// Fill transaction object
			transaction.setVisitor(getCurrentVisitor());
			transaction.setBook(book);
			transaction.setCount(1);
			transaction.setDelivery(getDelivery());
			transaction.setAddress(address);
			getTransactionService().createTransaction(transaction);
		}
		reset();
		return "success";
	}

	protected void reset() {
		borrowed = new ArrayList<Book>();
		currentVisitor = null;
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

	private String delivery;

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
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

}
