package com.kooobao.common.domain.dao;

public interface Cursor<T> {

	public boolean hasNext();

	public T next();
}
