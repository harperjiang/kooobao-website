package com.kooobao.lm.bizflow.entity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TransactionTest extends Transaction {

	@Test
	public void testIsStockReserved() {
		Transaction t = new Transaction();
		assertFalse(t.isStockReserved());
		t.setStockReserved(true);
		assertTrue(t.isStockReserved());
		t.setStockReserved(false);
		assertFalse(t.isStockReserved());
	}


}
