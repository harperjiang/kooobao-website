package com.kooobao.common.json;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

/**
 * JsonMapWriter is a simplified version of Json writer that support only maps,
 * collections and strings
 * 
 * @author harper
 * 
 */
public class JsonMapWriter {

	private StringBuilder stringBuilder = new StringBuilder();

	protected void print(String info) {
		stringBuilder.append(info);
	}

	public void write(Map<?, ?> map) {
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

	public void write(String string) {
		print("\"");
		print(string);
		print("\"");
	}

	public void write(int intValue) {
		print(String.valueOf(intValue));
	}

	public void write(long longValue) {
		print(String.valueOf(longValue));
	}

	public void write(Object bean) {
		if (bean instanceof Collection)
			write((Collection) bean);
		else if (bean instanceof String)
			write((String) bean);
		else
			throw new UnsupportedOperationException(bean.getClass().getName());
	}

	public String toString() {
		return stringBuilder.toString();
	}
}
