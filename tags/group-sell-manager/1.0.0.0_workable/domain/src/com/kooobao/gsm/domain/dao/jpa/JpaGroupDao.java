package com.kooobao.gsm.domain.dao.jpa;

import java.util.List;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.gsm.domain.dao.GroupDao;
import com.kooobao.gsm.domain.entity.group.Group;
import com.kooobao.gsm.domain.entity.group.GroupStatus;

public class JpaGroupDao extends AbstractJpaDao<Group> implements GroupDao {

	public List<Group> getActiveGroup() {
		return getEntityManager()
				.createQuery("select g from Group g where g.status = ?1",
						Group.class)
				.setParameter(1, GroupStatus.IN_PROGRESS.name())
				.getResultList();
	}

	public Group newGroup() {
		throw new UnsupportedOperationException("Not implemented");
	}

}
