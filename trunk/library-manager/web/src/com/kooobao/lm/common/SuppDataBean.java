package com.kooobao.lm.common;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.kooobao.common.domain.entity.StatusUtils;
import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.common.web.bean.JSFStartupAware;
import com.kooobao.lm.bizflow.TransactionState;

public class SuppDataBean extends AbstractBean implements JSFStartupAware {

	private List<SelectItem> transactionStates;

	@Override
	public void init() {
		transactionStates = new ArrayList<SelectItem>();
		for (TransactionState state : TransactionState.values()) {
			transactionStates
					.add(new SelectItem(state, StatusUtils.text(state)));
		}
	}

	@Override
	public void dispose() {

	}

	public List<SelectItem> getTransactionStates() {
		return transactionStates;
	}

	public void setTransactionStates(List<SelectItem> transactionStates) {
		this.transactionStates = transactionStates;
	}
}
