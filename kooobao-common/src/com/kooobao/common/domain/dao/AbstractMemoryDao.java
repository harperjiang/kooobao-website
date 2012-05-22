package com.kooobao.common.domain.dao;

import java.util.Date;
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

	public T store(T entity) {
		if (entity.getOid() == 0) {
			entity.setOid(count++);
		}
		storage.put(entity.getOid(), entity);
		entity.setCreateTime(new Date());
		return entity;
	}
	
	public T remove(T entity) {
		return storage.remove(entity.getOid());
	}

	public T find(long oid) {
		return storage.get(Long.valueOf(oid));
	}

	public T find(T example) {
		for (T value : getStorage().values()) {
			if (value.equals(example))
				return value;
		}
		return null;
	}

	protected Map<Long, T> getStorage() {
		return storage;
	}
	
	public Cursor<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
