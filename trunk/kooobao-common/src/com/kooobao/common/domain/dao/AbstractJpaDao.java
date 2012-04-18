package com.kooobao.common.domain.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.eclipse.persistence.jpa.JpaHelper;
import org.eclipse.persistence.queries.QueryByExamplePolicy;
import org.eclipse.persistence.queries.ReadObjectQuery;

import com.kooobao.common.domain.entity.VersionEntity;

public abstract class AbstractJpaDao<T> implements Dao<T> {

	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public T store(T entity) {
		// if (0 == entity.getOid()) {
		// getEntityManager().persist(entity);
		// return entity;
		// }

		if (entity instanceof VersionEntity
				&& (null == ((VersionEntity) entity).getCreateTime())) {
			((VersionEntity) entity).setCreateTime(new Date());
		}
		return getEntityManager().merge(entity);
	}

	public T find(long oid) {
		return getEntityManager().find(getParamClass(), oid);
	}

	public T find(T example) {
		QueryByExamplePolicy policy = new QueryByExamplePolicy();
		policy.excludeDefaultPrimitiveValues();
		ReadObjectQuery q = new ReadObjectQuery(example, policy);

		// Wrap the native query in a standard JPA Query and execute it
		Query query = JpaHelper.createQuery(q, getEntityManager());
		return (T) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public Class<T> getParamClass() {
		return (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
