package com.kooobao.ecom.user;

import javax.persistence.NoResultException;

import com.kooobao.ecom.user.dao.AuthorityDao;
import com.kooobao.ecom.user.dao.UserDao;
import com.kooobao.ecom.user.entity.Authority;
import com.kooobao.ecom.user.entity.User;

public class DefaultUserService implements UserService {

	@Override
	public User store(User user) {
		return getUserDao().store(user);
	}

	@Override
	public User find(String id) {
		return getUserDao().find(id);
	}

	@Override
	public boolean canAccess(String userId, String authorityId) {
		try {
			User user = getUserDao().find(userId);
			Authority auth = getAuthorityDao().findAuthority(authorityId);
			return user.getAllAuthorities().contains(auth);
		} catch (NoResultException e) {
			return false;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	private AuthorityDao authorityDao;

	public AuthorityDao getAuthorityDao() {
		return authorityDao;
	}

	public void setAuthorityDao(AuthorityDao authorityDao) {
		this.authorityDao = authorityDao;
	}

}
