package com.kooobao.lm.purchase;

import java.util.Date;

import com.kooobao.lm.profile.entity.Visitor;
import com.kooobao.lm.purchase.entity.PurchaseState;

public class PurchaseSearchBean {

	private Visitor visitor;

	private PurchaseState state = PurchaseState.SUBMIT;

	private Date fromDate;

	private Date toDate;

	private int from;

	private int to;

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	public PurchaseState getState() {
		return state;
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

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getTo() {
		return to;
	}

	public void setTo(int to) {
		this.to = to;
	}

}
