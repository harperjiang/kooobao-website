package org.harper.ourwebsite.service.profile;

import org.harper.ourwebsite.domain.entity.profile.User;

public interface UserService {
	
	public void createUser(User user);

	public void activeUser(User user);
}
