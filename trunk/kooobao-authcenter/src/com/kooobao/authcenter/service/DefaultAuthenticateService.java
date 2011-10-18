package com.kooobao.authcenter.service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.NoResultException;

import com.kooobao.authcenter.domain.dao.UserDao;
import com.kooobao.authcenter.domain.entity.User;

public class DefaultAuthenticateService implements AuthenticateService {

	private Map<String, TimeToken> userCache = new ConcurrentHashMap<String, TimeToken>();

	private static class TimeToken extends Token {

		/**
		 * 
		 */
		private static final long serialVersionUID = 5112046560650714917L;

		TimeToken(Token token) {
			super(token.getModule(), token.getUserId(), token.getUuid());
			this.lastActionTime = new Date().getTime();
		}

		private long lastActionTime;

		public long getLastActionTime() {
			return lastActionTime;
		}

		public void setLastActionTime(long lastActionTime) {
			this.lastActionTime = lastActionTime;
		}

	}

	public Token login(String system, String userId, String plainPass) {
		User user = null;
		try {
			user = getUserDao().findUser(system, userId);
		} catch (NoResultException e) {
			return null;
		}
		if (null == user)
			return null;
		if (!user.getEncryptedPass().equals(User.encryptPass(plainPass)))
			return null;
		user.setLastLoginTime(new Date());
		getUserDao().store(user);
		Token token = new Token(system, userId, UUID.randomUUID().toString());
		userCache.put(token.getUuid(), new TimeToken(token));
		return token;
	}

	public boolean validate(Token token) {
		if (userCache.containsKey(token.getUuid())
				&& System.currentTimeMillis()
						- userCache.get(token.getUuid()).getLastActionTime() < 600000) {
			userCache.get(token.getUuid()).setLastActionTime(
					System.currentTimeMillis());
			return true;
		}
		userCache.remove(token.getUuid());
		return false;
	}

	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
