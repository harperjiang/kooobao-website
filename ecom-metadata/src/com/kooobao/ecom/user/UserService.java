package com.kooobao.ecom.user;

import com.kooobao.ecom.user.entity.User;

public interface UserService {

	User store(User user);
	
	User find(String id);
	
	boolean canAccess(String userId, long authorityId);
}
