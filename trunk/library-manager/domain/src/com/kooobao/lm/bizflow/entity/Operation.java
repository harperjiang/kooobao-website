package com.kooobao.lm.bizflow.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.SimpleEntity;

@Entity
@Table(name = "lm_tran_opt")
public class Operation extends SimpleEntity {

	@Column(name = "from_state")
	private String fromState;

	@Column(name = "to_state")
	private String toState;

	@Column(name = "comment")
	private String comment;

	@Column(name = "desc_text")
	private String description;
	
	@OneToOne
	@JoinColumn(name="tran_id")
	private Transaction transaction;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFromState() {
		return fromState;
	}

	public void setFromState(String fromState) {
		this.fromState = fromState;
	}

	public String getToState() {
		return toState;
	}

	public void setToState(String toState) {
		this.toState = toState;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setFromState(TransactionState state) {
		if (null != state)
			setFromState(state.name());
	}

	public void setToState(TransactionState state) {
		if (null != state)
			setToState(state.name());
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
}
