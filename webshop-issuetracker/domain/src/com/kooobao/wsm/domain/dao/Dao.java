package com.kooobao.wsm.domain.dao;

import com.kooobao.wsm.domain.entity.VersionEntity;

public interface Dao<T extends VersionEntity> {

	public T store(T entity);

	public T find(long oid);
}
