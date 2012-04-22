package com.kooobao.common.domain.converter;

import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;

public class EnumConverter implements Converter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -221048563644331815L;
	
	protected Class<? extends Enum> enumClass;

	public Object convertDataValueToObjectValue(Object data, Session arg1) {
		if (null == data)
			return null;
		return Enum.valueOf(enumClass, String.valueOf(data));
	}

	public Object convertObjectValueToDataValue(Object value, Session arg1) {
		if(null == value)
			return null;
		return ((Enum)value).name();
	}

	public void initialize(DatabaseMapping arg0, Session arg1) {
	}

	public boolean isMutable() {
		return false;
	}
}
