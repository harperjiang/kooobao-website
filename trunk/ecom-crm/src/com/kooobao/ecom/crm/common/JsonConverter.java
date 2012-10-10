package com.kooobao.ecom.crm.common;

import java.util.Collection;
import java.util.Map;

import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.converters.Converter;
import org.eclipse.persistence.sessions.Session;

import com.kooobao.common.json.JsonMapReader;
import com.kooobao.common.json.JsonMapWriter;

public class JsonConverter implements Converter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5304833315869562381L;

	@Override
	public Object convertDataValueToObjectValue(Object data, Session session) {
		if (null == data)
			return null;
		JsonMapReader reader = new JsonMapReader();
		return reader.read(String.valueOf(data));
	}

	@Override
	public Object convertObjectValueToDataValue(Object value, Session session) {
		if (null == value)
			return null;
		@SuppressWarnings("unchecked")
		Map<String, Collection<String>> map = (Map<String, Collection<String>>) value;
		JsonMapWriter writer = new JsonMapWriter();
		writer.write(map);
		return writer.toString();
	}

	@Override
	public void initialize(DatabaseMapping arg0, Session arg1) {
	}

	@Override
	public boolean isMutable() {
		return false;
	}

}
