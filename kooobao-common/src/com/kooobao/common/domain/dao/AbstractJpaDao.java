package com.kooobao.common.domain.dao;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;

import com.kooobao.common.domain.entity.VersionEntity;

public abstract class AbstractJpaDao<T extends VersionEntity> implements Dao<T> {

	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public T store(T entity) {
//		if (0 == entity.getOid()) {
//			getEntityManager().persist(entity);
//			return entity;
//		}
		return getEntityManager().merge(entity);
	}

	public T find(long oid) {
		return getEntityManager().find(getParamClass(), oid);
	}

	@SuppressWarnings("unchecked")
	public Class<T> getParamClass() {
		return (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
