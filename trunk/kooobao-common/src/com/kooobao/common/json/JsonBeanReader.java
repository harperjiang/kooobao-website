package com.kooobao.common.json;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.ParameterizedType;
import java.util.Map;

import org.slf4j.LoggerFactory;

/**
 * This BeanReader can only handle single layer bean
 * 
 * @author Hao Jiang
 * @since Simulator 1.0
 * @version 1.0
 * 
 * 
 * @param <E>
 */
public class JsonBeanReader<E> {

	private JsonMapReader reader;

	public JsonBeanReader() {
		super();
		reader = new JsonMapReader();
	}

	public E read(String value) {
		try {
			Map<String, Object> vals = reader.read(value);
			E beaninst = getParamClass().newInstance();
			BeanInfo beaninfo = Introspector.getBeanInfo(getParamClass());

			for (PropertyDescriptor pd : beaninfo.getPropertyDescriptors()) {
				if (vals.containsKey(pd.getName())) {
					pd.getWriteMethod()
							.invoke(beaninst, vals.get(pd.getName()));
				}
			}
			return beaninst;
		} catch (Exception e) {
			LoggerFactory.getLogger(getClass()).error(
					"Error while reading json string", e);
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	protected Class<E> getParamClass() {
		return (Class<E>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
