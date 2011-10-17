package com.kooobao.gsm.domain.dao;

import java.util.List;

import com.kooobao.gsm.domain.entity.group.Group;

public interface GroupDao {

	public List<Group> getActiveGroup();

	public Group newGroup();
}
