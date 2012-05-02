package com.kooobao.lm.bizflow;

import java.util.List;

import com.kooobao.common.domain.entity.VersionEntity;
import com.kooobao.lm.profile.Library;
import com.kooobao.lm.profile.Visitor;

public class Transaction extends VersionEntity {

	private Library library;

	private Visitor visitor;

	private List<TransactionItem> items;

	private String state;

	public State getState() {
		return State.valueOf(this.state);
	}

	public void setState(State state) {
		this.state = state.name();
	}

	public static enum State {
		BORROW_REQUEST, BORROW_SENT, BORROW_RECEIVED, RETURN_SENT, RETURN_EXPIRED, RETURN_RECEIVED, RETURN_MISSED, FINISHED
	}
}
