package com.kooobao.lm.bizflow.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.kooobao.common.domain.entity.VersionEntity;

public class ExpireRecord extends VersionEntity {

	private boolean active;

	private Transaction transaction;

	private Date dueDate;

	private Date returnDate;

	private BigDecimal penalty;

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public BigDecimal getPenalty() {
		return penalty;
	}

	public void setPenalty(BigDecimal penalty) {
		this.penalty = penalty;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

}
