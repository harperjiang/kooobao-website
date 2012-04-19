package com.kooobao.common.web.bean;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.kooobao.common.spring.ApplicationContextHolder;
import com.kooobao.common.util.ConfigLoader;

public class BeanStartuper implements ServletContextListener {

	private Set<String> keys;

	protected Set<String> getKeys() {
		if (null == keys) {
			keys = new HashSet<String>();
			String keyString = ConfigLoader.getInstance().load(
					"startup_bean_list", "key");
			for (String val : keyString.split(","))
				keys.add(val);
		}
		return keys;
	}

	private Map<String, JSFStartupAware> objects = new HashMap<String, JSFStartupAware>();

	protected JSFStartupAware getObject(String key) {
		if (objects.containsKey(key)) {
			return objects.get(key);
		}
		try {
			JSFStartupAware result = (JSFStartupAware) ApplicationContextHolder
					.getInstance().getApplicationContext().getBean(key);
			result.init();
			objects.put(key, result);
			return result;
		} catch (Exception e) {
			return null;
		}
	}

	public void contextInitialized(ServletContextEvent event) {
		for (String key : getKeys()) {
			event.getServletContext().setAttribute(key, getObject(key));
		}
	}

	public void contextDestroyed(ServletContextEvent event) {
		for (String key : getKeys()) {
			JSFStartupAware myObject = (JSFStartupAware) event
					.getServletContext().getAttribute(key);
			try {
				event.getServletContext().removeAttribute(key);
			} finally {
				if (null != myObject)
					myObject.dispose();
			}
		}
	}

}
