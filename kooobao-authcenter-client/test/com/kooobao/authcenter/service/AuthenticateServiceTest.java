package com.kooobao.authcenter.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/auth.xml")
public class AuthenticateServiceTest extends AbstractJUnit4SpringContextTests {

	@Resource
	AuthenticateService authService;

	@Test
	public void testLogin() {
		assertNotNull(authService.login("gsm", "debbie", ""));
	}

	@Test
	public void testValidate() {
		fail("Not yet implemented");
	}

}
