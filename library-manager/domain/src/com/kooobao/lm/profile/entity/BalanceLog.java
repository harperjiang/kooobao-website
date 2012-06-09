package com.kooobao.lm.profile.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.SimpleEntity;

@Entity
@Table(name = "lm_visitor_balancelog")
public class BalanceLog extends SimpleEntity {

	@Column(name = "bal_change")
	private BigDecimal change;

	@Column(name = "reason")
	private String reason;

	@Column(name = "operator_id")
	private String operatorId;
	
	@OneToOne
	@JoinColumn(name="visitor_id")
	private Visitor visitor;

	public BigDecimal getChange() {
		return change;
	}

	public void setChange(BigDecimal change) {
		this.change = change;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

}
