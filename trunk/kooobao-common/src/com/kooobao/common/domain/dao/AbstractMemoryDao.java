package com.kooobao.common.domain.dao;

import java.util.HashMap;
import java.util.Map;

import com.kooobao.common.domain.entity.VersionEntity;

public abstract class AbstractMemoryDao<T extends VersionEntity> implements
		Dao<T> {

	private Map<Long, T> storage;

	private int count = 1;

	public AbstractMemoryDao() {
		super();
		count = 1;
		storage = new HashMap<Long, T>();
	}

	@Override
	public T store(T entity) {
		if (entity.getOid() == 0) {
			entity.setOid(count++);
		}
		storage.put(entity.getOid(), entity);
		return entity;
	}

	@Override
	public T find(long oid) {
		return storage.get(Long.valueOf(oid));
	}

	protected Map<Long, T> getStorage() {
		return storage;
	}
}
