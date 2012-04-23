package com.kooobao.authcenter.service;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;

import com.kooobao.authcenter.domain.dao.UserDao;
import com.kooobao.authcenter.domain.entity.Authority;
import com.kooobao.authcenter.domain.entity.User;

public class DefaultAuthenticateServiceTest {

	DefaultAuthenticateService service;

	public DefaultAuthenticateServiceTest() {
		service = new DefaultAuthenticateService();
		service.setUserDao(new UserDao() {
			public User findUser(String system, String userId) {
				if ("a".equals(userId)) {
					User usr = new User();
					usr.setEncryptedPass(User.encryptPass("a"));
					usr.setId("a");
					return usr;
				}
				return null;
			}

			public User findUser(String userId, Authority auth) {
				return null;
			}

			public List<User> getUserByAuthority() {
				return null;
			}

			public User store(User user) {
				return null;
			}

			public List<User> findUsers(String system, List<Authority> auths) {
				// TODO Auto-generated method stub
				return null;
			}

			public User findUser(String id) {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}

	@Test
	public void testLogin() {
		Token token = service.login("kk", "a", "a");
		assertNotNull(token);

		token = service.login("kk", "b", "a");
		assertNull(token);
	}

	@Test
	public void testValidate() {
		Token token = service.login("kk", "a", "a");
		assertNotNull(token);
		
		assertTrue(service.validate(token));
		
		token.setUuid("add");
		

		assertFalse(service.validate(token));
	}

}
