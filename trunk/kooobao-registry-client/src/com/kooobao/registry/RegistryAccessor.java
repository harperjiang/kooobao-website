package com.kooobao.registry;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

import com.kooobao.common.spring.ApplicationContextHolder;
import com.kooobao.common.util.ConfigLoader;

public class RegistryAccessor {

	private RegistryAccessor() {

	}

	protected static RegistryAccessor instance = new RegistryAccessor();

	public static RegistryAccessor getInstance() {
		return instance;
	}

	private boolean inited = false;

	public void register() {
		if (!inited) {
			synchronized (this) {
				if (!inited) {
					try {
						String system = ConfigLoader.getInstance().load(
								"registry", "system");
						String functionStr = ConfigLoader.getInstance().load(
								"registry", "functions");
						if (StringUtils.isEmpty(system)
								|| StringUtils.isEmpty(functionStr))
							return;
						String[] functions = functionStr.split(",");
						RegistryService registryService = ApplicationContextHolder
								.getInstance()
								.getApplicationContext()
								.getBean("registryService",
										RegistryService.class);
						for (String function : functions) {
							String localLocation = ConfigLoader.getInstance()
									.load("registry", function + "_url");
							if (!StringUtils.isEmpty(localLocation)) {
								registryService.register(system, function,
										localLocation);
							}
						}
						inited = true;
					} catch (Exception e) {
						LoggerFactory.getLogger(getClass()).error(
								"Failed to register local service callback", e);
						inited = false;
					}
				}
			}
		}
	}
}
