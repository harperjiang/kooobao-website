package com.kooobao.gsm.domain.dao.jpa;

import java.util.List;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.gsm.domain.dao.GroupDao;
import com.kooobao.gsm.domain.entity.group.Group;
import com.kooobao.gsm.domain.entity.group.GroupStatus;

public class JpaGroupDao extends AbstractJpaDao<Group> implements GroupDao {

	@SuppressWarnings("unchecked")
	public List<Group> getActiveGroup() {
		return (List<Group>) getTemplate().find(
				"select g from Group g where g.status = ?1",
				GroupStatus.IN_PROGRESS.name());
	}

	public Group newGroup() {
		throw new UnsupportedOperationException("Not implemented");
	}

}
