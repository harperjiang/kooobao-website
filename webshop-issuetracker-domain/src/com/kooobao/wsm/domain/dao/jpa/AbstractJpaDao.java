package com.kooobao.wsm.domain.dao.jpa;

import java.lang.reflect.ParameterizedType;

import org.springframework.orm.jpa.JpaTemplate;

import com.kooobao.wsm.domain.dao.Dao;
import com.kooobao.wsm.domain.entity.VersionEntity;

public abstract class AbstractJpaDao<T extends VersionEntity> implements Dao<T> {

	private JpaTemplate template;

	public JpaTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JpaTemplate template) {
		this.template = template;
	}

	public T store(T entity) {
		if (0 == entity.getOid()) {
			getTemplate().persist(entity);
			return entity;
		}
		return getTemplate().merge(entity);
	}

	public T find(long oid) {
		return getTemplate().find(getParamClass(), oid);
	}

	protected Class<T> getParamClass() {
		return (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
