package com.kooobao.common.domain.dao;

import java.lang.reflect.ParameterizedType;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.eclipse.persistence.jpa.JpaHelper;
import org.eclipse.persistence.queries.AttributeGroup;
import org.eclipse.persistence.queries.QueryByExamplePolicy;
import org.eclipse.persistence.queries.ReadAllQuery;
import org.eclipse.persistence.queries.ReadObjectQuery;
import org.eclipse.persistence.sessions.server.Server;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;

import com.kooobao.common.domain.dao.cursor.JpaCursor;
import com.kooobao.common.domain.entity.SimpleEntity;
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
		if (entity instanceof SimpleEntity
				&& (null == ((SimpleEntity) entity).getCreateTime())) {
			((SimpleEntity) entity).setCreateTime(new Date());
		}
		if (entity instanceof SimpleEntity
				&& 0 == ((SimpleEntity) entity).getOid()) {
			getEntityManager().persist(entity);
		} else {
			entity = getEntityManager().merge(entity);
		}
		// Copy Object
		EntityManagerFactory nativeEmf = extract(getEntityManager()
				.getEntityManagerFactory());
		if (entity instanceof VersionEntity
				&& JpaHelper.isEclipseLink(nativeEmf)) {
			Server server = JpaHelper.getServerSession(nativeEmf);
			VersionEntity ve = (VersionEntity) server.copy(entity);
			ve.setVersion(ve.getVersion() + 1);
			return (T) ve;
		}
		return entity;

	}

	private EntityManagerFactory extract(
			EntityManagerFactory entityManagerFactory) {
		if (entityManagerFactory instanceof EntityManagerFactoryInfo) {
			return ((EntityManagerFactoryInfo) entityManagerFactory)
					.getNativeEntityManagerFactory();
		}
		return entityManagerFactory;
	}

	public T remove(T entity) {
		getEntityManager().remove(entity);
		return entity;
	}

	public void removeAll() {
		getEntityManager().createQuery(
				"delete from " + getParamClass().getSimpleName())
				.executeUpdate();
		EntityManagerFactory nativeEmf = extract(getEntityManager()
				.getEntityManagerFactory());
		if (JpaHelper.isEclipseLink(nativeEmf)) {
			Server server = JpaHelper.getServerSession(nativeEmf);
			server.getIdentityMapAccessor().invalidateClass(getParamClass());
		}
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

	public Cursor<T> findAll() {
		ReadAllQuery raq = new ReadAllQuery(getParamClass());
		Query query = JpaHelper.createQuery(raq, getEntityManager());
		JpaCursor<T> cursor = new JpaCursor<T>(query);
		return cursor;
	}

	@SuppressWarnings("unchecked")
	public Class<T> getParamClass() {
		return (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	protected String genParamStr(int size) {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (int i = 0; i < size; i++) {
			sb.append("?");
			if (i != size - 1)
				sb.append(",");
		}
		sb.append(")");
		return sb.toString();
	}
}
