package org.harper.frm.dao;

public interface Dao {
	public <T> T readObject(Class<T> clazz, int oid);
}
