package com.kooobao.authcenter.listener;

import static org.junit.Assert.*;

import org.junit.Test;

public class LoginAuthorizerTest {

	@Test
	public void testNeedValidate() {
		LoginAuthorizer auth = new LoginAuthorizer();
		assertFalse(null != auth.needValidate("/login.xhtml"));
		assertTrue(null != auth.needValidate("/query.xhtml"));
	}

}
