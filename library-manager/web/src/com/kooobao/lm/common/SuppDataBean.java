package com.kooobao.lm.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.util.CollectionUtils;

import com.kooobao.common.domain.entity.StatusUtils;
import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.common.web.bean.JSFStartupAware;
import com.kooobao.lm.bizflow.entity.DeliveryMethod;
import com.kooobao.lm.bizflow.entity.TransactionState;
import com.kooobao.lm.book.BookService;
import com.kooobao.lm.book.entity.Category;
import com.kooobao.lm.profile.entity.VisitorStatus;
import com.kooobao.lm.profile.entity.VisitorType;

public class SuppDataBean extends AbstractBean implements JSFStartupAware {

	private List<SelectItem> transactionStates;

	private List<SelectItem> deliveryMethods;

	private List<Category> categories;

	private List<Category> allCategories;

	private List<String> cancelReasons;

	private List<String> expressCompanies;

	private List<SelectItem> userTypes;

	private List<SelectItem> visitorStatus;

	public void init() {
		transactionStates = new ArrayList<SelectItem>();
		for (TransactionState state : TransactionState.values()) {
			transactionStates
					.add(new SelectItem(state, StatusUtils.text(state)));
		}

		deliveryMethods = new ArrayList<SelectItem>();
		for (DeliveryMethod dm : DeliveryMethod.values()) {
			deliveryMethods.add(new SelectItem(dm, StatusUtils.text(dm)));
		}

		categories = getBookService().getRootCategories();
		allCategories = new ArrayList<Category>();
		allCategories.addAll(categories);
		for (int i = 0; i < allCategories.size(); i++) {
			Category c = allCategories.get(i);
			if (!CollectionUtils.isEmpty(c.getChildren())) {
				allCategories.remove(i);
				i--;
				allCategories.addAll(c.getChildren());
			}
		}

		cancelReasons = new ArrayList<String>();
		cancelReasons.add("我不想要了");
		cancelReasons.add("订单错了，需要重新修改");

		expressCompanies = new ArrayList<String>();
		expressCompanies.add("顺丰");
		expressCompanies.add("圆通");
		expressCompanies.add("中通");
		expressCompanies.add("汇通");
		expressCompanies.add("韵达");
		expressCompanies.add("其他");

		userTypes = new ArrayList<SelectItem>();
		for (VisitorType vt : VisitorType.values()) {
			userTypes.add(new SelectItem(vt, StatusUtils.text(vt)));
		}

		visitorStatus = new ArrayList<SelectItem>();
		for (VisitorStatus s : VisitorStatus.values()) {
			visitorStatus.add(new SelectItem(s, StatusUtils.text(s)));
		}
	}

	public void dispose() {

	}

	public List<Category> getCategories() {
		return categories;
	}

	public List<SelectItem> getTransactionStates() {
		return transactionStates;
	}

	public void setTransactionStates(List<SelectItem> transactionStates) {
		this.transactionStates = transactionStates;
	}

	public Date getCurrentTime() {
		return new Date();
	}

	public List<Category> getAllCategories() {
		return allCategories;
	}

	public List<SelectItem> getDeliveryMethods() {
		return deliveryMethods;
	}

	private BookService bookService;

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public List<String> getCancelReasons() {
		return cancelReasons;
	}

	public void setCancelReasons(List<String> cancelReasons) {
		this.cancelReasons = cancelReasons;
	}

	public List<String> getExpressCompanies() {
		return expressCompanies;
	}

	public List<SelectItem> getUserTypes() {
		return userTypes;
	}

	public List<SelectItem> getVisitorStatus() {
		return visitorStatus;
	}

}
