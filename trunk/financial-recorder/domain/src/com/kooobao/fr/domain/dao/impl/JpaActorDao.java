package com.kooobao.fr.domain.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.fr.domain.dao.ActorDao;
import com.kooobao.fr.domain.entity.Actor;
import com.kooobao.fr.domain.entity.Role;

public class JpaActorDao extends AbstractJpaDao<Actor> implements ActorDao {

	public List<Actor> getActors(Role role) {
		CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
		
		CriteriaQuery<Actor> query = builder.createQuery(Actor.class);
		Root<Actor> root = query.from(Actor.class);
//		Subquery<String> roleSub = query.subquery(String.class);
//		roleSub.from()
		query.where(builder.equal(root.get("roles"),role.name()));
		return getEntityManager().createQuery(query).getResultList();
	}

}
