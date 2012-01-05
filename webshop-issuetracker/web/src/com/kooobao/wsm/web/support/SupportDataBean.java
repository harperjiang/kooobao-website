package com.kooobao.wsm.web.support;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.kooobao.authcenter.service.UserService;
import com.kooobao.common.util.ConfigLoader;
import com.kooobao.wsm.domain.entity.issue.TroubleCase;
import com.kooobao.wsm.web.AbstractBean;

public class SupportDataBean extends AbstractBean {

	public SupportDataBean() {
		super();
	}

	public void initFollowers() {
		String system = ConfigLoader.getInstance().load("auth_list", "system");
		List<String> users = getUserService().getUsers(system, null);
		followers = new ArrayList<String>();
		for (String user : users)
			followers.add(user);
	}

	private List<String> followers;

	public List<String> getFollowers() {
		if (null == followers) {
			initFollowers();
		}
		return followers;
	}

	private List<SelectItem> troubleOpStatus;

	private List<SelectItem> troubleSearchStatus;

	public List<SelectItem> getTroubleStatus() {
		if (null == troubleOpStatus) {
			troubleOpStatus = new ArrayList<SelectItem>();
			for (TroubleCase.Status status : TroubleCase.Status.values()) {
				if (status == TroubleCase.Status.NEW)
					continue;
				troubleOpStatus
						.add(new SelectItem(status.name(), status.text()));
			}
		}
		return troubleOpStatus;
	}

	public List<SelectItem> getTroubleSearchStatus() {
		if (null == troubleSearchStatus) {
			troubleSearchStatus = new ArrayList<SelectItem>();
			for (TroubleCase.Status status : TroubleCase.Status.values()) {
				troubleSearchStatus.add(new SelectItem(status.name(), status
						.text()));
			}
		}
		return troubleSearchStatus;
	}

	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
