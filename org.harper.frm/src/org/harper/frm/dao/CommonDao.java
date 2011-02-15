package org.harper.frm.dao;

import java.util.Collection;

public interface CommonDao extends Dao{

	public String NUMBER_TYPE_USER = "USER";
	
	public String getNumber(String type);
	
	public void store(Collection<?> cols);
	
	public <T> T store(T object);
}
