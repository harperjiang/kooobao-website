package com.kooobao.authcenter.domain.entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

	@Test
	public void testEncryptPass() {
		assertEquals("", User.encryptPass("cndebbie"));
	}

}
