package com.kooobao.common.domain.dao;

import com.kooobao.common.domain.entity.VersionEntity;

public interface Dao<T extends VersionEntity> {

	public T store(T entity);

	public T find(long oid);
}
