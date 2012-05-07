package com.kooobao.lm.bizflow;

import org.junit.Test;

import com.kooobao.lm.book.DummyBookService;

public class CartManagerTest {

	CartManager cm = new CartManager();
	{
		cm.setBookService(new DummyBookService());
	}
	@Test
	public void testClearExpired() {
		
		cm.clearExpired();
		cm.clearExpired();
		cm.addToCart("1231234", 123);
		cm.clearExpired();

	}

}
