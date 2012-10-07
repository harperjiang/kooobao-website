package com.kooobao.common.json;

import java.beans.PropertyDescriptor;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * JsonWriter is a general purpose json converter that read beans and write json
 * strings
 * 
 * @author harper
 * 
 */
public class JsonWriter {

	private StringBuilder buffer;

	public JsonWriter() {
		buffer = new StringBuilder();
	}

	public void write(Collection<?> collection) {
		print("[");
		int count = 0;
		for (Object o : collection) {
			write(o);
			count++;
			if (count < collection.size())
				print(",");
		}
		print("]");
	}

	public void write(Map<String, ?> map) {
		print("{");
		int count = 0;
		for (Entry<?, ?> entry : map.entrySet()) {
			if (entry.getKey() instanceof String) {
				write((String) entry.getKey());
				print(":");
				write(entry.getValue());
			} else {
				throw new IllegalArgumentException("Not allowed: Key of type: "
						+ entry.getKey().getClass().getName());
			}
			count++;
			if (count < map.size())
				print(",");
		}
		print("}");
	}

	public void write(String string) {
		print("\"");
		print(string);
		print("\"");
	}

	public void write(Number number) {
		print(number.toString());
	}

	public void write(Object data) {
		if (data instanceof Map)
			write((Map) data);
		else if (data instanceof Collection)
			write((Collection) data);
		else if (data instanceof String)
			write((String) data);
		else if (data instanceof Class) {
			write(((Class) data).getName());
		} else
			writeBean(data);
	}

	protected void writeBean(Object bean) {
		print("{");
		PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(bean
				.getClass());
		for (PropertyDescriptor pd : pds) {
			if (null != pd.getReadMethod()) {
				String name = pd.getName();
				write(name);
				print(":");
				try {
					Object value = pd.getReadMethod().invoke(bean,
							(Object[]) null);
					write(value);
				} catch (Exception e) {
					write("");
				}
			}
		}
		print("}");
	}

	protected void print(String data) {
		buffer.append(data);
	}

	@Override
	public String toString() {
		return buffer.toString();
	}
}
