package com.kooobao.common.domain.dao;




public interface Dao<T> {

	public T store(T entity);
	
	public T remove(T entity);
	
	public void removeAll();

	public T find(long oid);
	
	public T find(T example);
	
	public Cursor<T> findAll();
}
