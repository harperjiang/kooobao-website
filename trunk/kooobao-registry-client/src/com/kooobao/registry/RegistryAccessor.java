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

	private RegistryService registryService = ApplicationContextHolder
			.getInstance().getApplicationContext()
			.getBean("registryService", RegistryService.class);

	public void register() {
		if (!inited) {
			synchronized (this) {
				if (!inited) {
					try {
						String functionStr = ConfigLoader.getInstance().load(
								"registry", "functions");
						if (StringUtils.isEmpty(functionStr))
							return;
						String[] functions = functionStr.split(",");
						RegistryService registryService = getRegistryService();
						for (String function : functions) {
							String[] functionPart = function.split("\\.");

							String localLocation = ConfigLoader.getInstance()
									.load("registry", function + "_url");
							if (!StringUtils.isEmpty(localLocation)) {
								registryService.register(functionPart[0],
										functionPart[1], localLocation);
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

	public void publish(String function, Object data) {
		RegistryService registryService = getRegistryService();
		String publishSystem = ConfigLoader.getInstance().load("registry",
				"publish_system");
		if (StringUtils.isEmpty(publishSystem))
			publishSystem = "default";
		registryService.publish(publishSystem, function, data);
	}

	public void install() {
		String publishSystem = ConfigLoader.getInstance().load("registry",
				"publish_system");
		RegistryService registryService = getRegistryService();
		registryService.install(publishSystem);
	}

	public String[] list() {
		return getRegistryService().list();
	}

	private RegistryService getRegistryService() {
		return registryService;
	}

}
