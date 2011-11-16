package com.kooobao.gsm.web.group;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.gsm.domain.dao.GroupDao;
import com.kooobao.gsm.domain.entity.group.Group;

@ManagedBean
@ApplicationScoped
public class MaintainGroupBean extends AbstractBean {

	private List<String> groupNames;

	@ManagedProperty("#{groupDao}")
	private GroupDao groupDao;

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
		for (Group group : getGroupDao().getActiveGroup()) {
			groupNames.add(group.getName());
		}
	}

	public GroupDao getGroupDao() {
		return groupDao;
	}

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

}
