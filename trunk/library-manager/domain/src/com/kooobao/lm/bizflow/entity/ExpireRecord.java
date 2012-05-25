package com.kooobao.lm.bizflow.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.kooobao.common.domain.entity.StatusUtils;
import com.kooobao.common.domain.entity.VersionEntity;

@Entity
@Table(name = "lm_tran_expire_rec")
public class ExpireRecord extends VersionEntity {

	@Column(name = "state")
	private String state;

	@OneToOne
	@JoinColumn(name = "transaction")
	private Transaction transaction;

	@Column(name = "due_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dueTime;

	@Column(name = "return_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date returnTime;

	@Column(name = "penalty")
	private BigDecimal penalty = BigDecimal.ZERO;

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

	public Date getDueTime() {
		return dueTime;
	}

	public void setDueTime(Date dueTime) {
		this.dueTime = dueTime;
	}

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	public ExpireRecordState getState() {
		return ExpireRecordState.valueOf(state);
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setState(ExpireRecordState state) {
		this.state = state.name();
	}

	public String getStateText() {
		return StatusUtils.text(getState());
	}

	public boolean isActive() {
		return getState() == ExpireRecordState.ACTIVE;
	}

}
