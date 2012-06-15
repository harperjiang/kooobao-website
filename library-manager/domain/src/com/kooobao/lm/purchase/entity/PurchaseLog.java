package com.kooobao.lm.purchase.entity;

import com.kooobao.common.domain.entity.SimpleEntity;

public class PurchaseLog extends SimpleEntity {

	private String operatorId;

	private String fromState;

	private String toState;

	private String description;

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
