package com.kooobao.lm.bizflow;

import java.util.ArrayList;
import java.util.List;

import com.kooobao.common.domain.entity.SimpleEntity;

public class Operation extends SimpleEntity {

	private String fromState;

	private String toState;

	private String comment;

	private List<Adjust> adjusts = new ArrayList<Adjust>();
	
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

	public List<Adjust> getAdjusts() {
		return adjusts;
	}

	public void addAdjust(Adjust adjust) {
		this.adjusts.add(adjust);
	}

	
}
