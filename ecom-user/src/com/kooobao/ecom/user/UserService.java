package com.kooobao.ecom.user;

public interface UserService {

	boolean createUser(String id);
	
	boolean canAccess(String userId, String authorityId);
}
