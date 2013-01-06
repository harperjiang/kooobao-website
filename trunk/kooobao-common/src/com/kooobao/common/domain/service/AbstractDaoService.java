package com.kooobao.common.domain.service;

import com.kooobao.common.domain.dao.Cursor;
import com.kooobao.common.domain.dao.Dao;

public class AbstractDaoService<T> implements Dao<T> {

	private Dao<T> innerDao;

	public AbstractDaoService() {

	}

	public Dao<T> getInnerDao() {
		return innerDao;
	}

	public void setInnerDao(Dao<T> innerDao) {
		this.innerDao = innerDao;
	}

	public T store(T entity) {
		return innerDao.store(entity);
	}

	public T remove(T entity) {
		return innerDao.remove(entity);
	}

	public void removeAll() {
		innerDao.removeAll();
	}

	public T find(long oid) {
		return innerDao.find(oid);
	}

	public T find(T example) {
		return innerDao.find(example);
	}

	public Cursor<T> findAll() {
		return innerDao.findAll();
	}

}
