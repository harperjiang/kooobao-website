package com.kooobao.lm.bizflow.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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

}
