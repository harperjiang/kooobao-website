package com.kooobao.gsm.web.group;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.kooobao.common.web.bean.AbstractBean;

@ManagedBean
@ApplicationScoped
public class MaintainGroupBean extends AbstractBean {

	private List<String> groupNames;

	public List<String> getGroupNames() {
		if (null == groupNames) {
			load();
		}
		return groupNames;
	}

	public List<String> getActiveGroupNames() {
		return getGroupNames();
	}

	public void update() {
		groupNames = null;
	}

	private void load() {
		groupNames = new ArrayList<String>();
		groupNames.add("团购活动A");
		groupNames.add("团购活动B");
		groupNames.add("团购活动C");
	}

	public void setGroupNames(List<String> groupNames) {
		this.groupNames = groupNames;
	}

}
