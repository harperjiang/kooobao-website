package com.kooobao.lm.bizflow;

import java.util.Date;

import com.kooobao.common.web.Utilities;

public class SearchBean {

	private Date fromDate = Utilities.offset(Utilities.dayBegin(), -7);

	private Date toDate = Utilities.dayEnd();

	private int from;

	private int to;

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