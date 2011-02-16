package org.harper.ourwebsite.member.register;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import org.harper.frm.core.logging.LogManager;
import org.harper.ourwebsite.domain.entity.profile.User;
import org.harper.ourwebsite.service.profile.UserService;


@ManagedBean
public class RegisterBean {

	private String memberType;

	private String name;

	private String email;

	private String phone;

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String register() {

		User user = new User();
		user.setType(getMemberType());
		user.setId(getName());
		user.setName(getName());
		user.setEmail(getEmail());
		try {
			getUserService().createUser(user);
			return "success";
		} catch (Exception e) {
			LogManager.getInstance().getLogger(getClass())
					.error("Exception", e);
			return "error";
		}

		
	}

	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
