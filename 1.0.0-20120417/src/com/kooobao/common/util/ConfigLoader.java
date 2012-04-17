package com.kooobao.common.util;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.LogFactory;

public class ConfigLoader {

	private Map<String, Properties> cache;

	private ConfigLoader() {
		cache = new ConcurrentHashMap<String, Properties>();
	}

	private static ConfigLoader instance = new ConfigLoader();

	public static ConfigLoader getInstance() {
		return instance;
	}

	public String load(String module, String key) {
		if (cache.containsKey(module))
			return cache.get(module).getProperty(key);
		Properties prop = new Properties();
		try {
			if (!module.endsWith(".properties"))
				module += ".properties";
			prop.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(module));
			cache.put(module, prop);
			return prop.getProperty(key);
		} catch (Exception e) {
			LogFactory.getLog(getClass()).error("Failed to load " + module, e);
			return null;
		}

	}

}
