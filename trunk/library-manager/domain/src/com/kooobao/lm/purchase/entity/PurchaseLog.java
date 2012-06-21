package com.kooobao.lm.purchase.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.SimpleEntity;

@Entity
@Table(name = "lm_purchase_log")
public class PurchaseLog extends SimpleEntity {

	@Column(name = "operator_id")
	private String operatorId;

	@Column(name = "from_state")
	private String fromState;

	@Column(name = "to_state")
	private String toState;

	@Column(name = "desc_text")
	private String description;

	@ManyToOne
	@JoinColumn(name = "header")
	private Purchase header;

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public PurchaseState getFromState() {
		return PurchaseState.valueOf(fromState);
	}

	public void setFromState(PurchaseState state) {
		this.fromState = state.name();
	}

	public PurchaseState getToState() {
		return PurchaseState.valueOf(toState);
	}

	public void setToState(PurchaseState state) {
		this.toState = state.name();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Purchase getHeader() {
		return header;
	}

	public void setHeader(Purchase header) {
		this.header = header;
	}
}
