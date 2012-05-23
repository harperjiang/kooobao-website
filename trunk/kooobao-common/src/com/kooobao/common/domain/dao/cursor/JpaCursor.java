package com.kooobao.common.domain.dao.cursor;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.Query;

import com.kooobao.common.domain.dao.Cursor;

public class JpaCursor<T> implements Cursor<T> {

	int currentCount;

	public boolean hasNext() {
		if (currentCount < getBuffer().size())
			return true;
		if (buffer.size() < getPageSize())
			return false;
		getNextPage();
		return hasNext();
	}

	protected List<T> getBuffer() {
		if (null == buffer)
			getNextPage();
		return buffer;
	}

	private List<T> buffer;

	protected void getNextPage() {
		query.setFirstResult(currentCount);
		query.setMaxResults(getPageSize());
		buffer = query.getResultList();
	}

	public T next() {
		return getBuffer().get(currentCount++);
	}

	@SuppressWarnings("unchecked")
	public Class<T> getParamClass() {
		return (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public JpaCursor(Query query) {
		this.query = query;
	}

	private Query query;

	private int pageSize = 100;

	public int getPageSize() {
		return pageSize;
	}

}
