package com.kooobao.authcenter.listener;

import static org.junit.Assert.*;

import org.junit.Test;

public class LoginAuthorizerTest {

	@Test
	public void testNeedValidate() {
		LoginAuthorizer auth = new LoginAuthorizer();
		assertFalse(auth.needValidate("/login.xhtml"));
		assertTrue(auth.needValidate("/query.xhtml"));
	}

}
