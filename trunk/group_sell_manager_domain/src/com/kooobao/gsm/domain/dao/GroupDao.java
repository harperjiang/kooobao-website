package com.kooobao.gsm.domain.dao;

import com.kooobao.gsm.domain.entity.group.Group;

public interface GroupDao {

	public Group getActiveGroup();
	
	public Group newGroup();
}
