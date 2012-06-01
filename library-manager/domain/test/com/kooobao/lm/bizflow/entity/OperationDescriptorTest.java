package com.kooobao.lm.bizflow.entity;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OperationDescriptorTest {

	@Test
	public void testDescribe() {
		Operation opr = new Operation();
		opr.setToState(TransactionState.BORROW_REQUESTED);
		assertEquals("您的请求已经提交，正在处理中", OperationDescriptor.describe(opr));

		opr.setFromState(TransactionState.BORROW_REQUESTED);
		opr.setToState(TransactionState.BORROW_APPROVED);
		assertEquals("您的订单已经通过审核，正在出库中", OperationDescriptor.describe(opr));

		opr.setFromState(TransactionState.BORROW_APPROVED);
		opr.setToState(TransactionState.BORROW_SENT);
		assertEquals("您的订单已经出库", OperationDescriptor.describe(opr));

	}

}
