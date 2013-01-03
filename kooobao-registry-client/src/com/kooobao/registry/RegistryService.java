package com.kooobao.registry;

import java.util.Map;

/**
 * Interface to access module registry
 * 
 * @author harper
 * 
 */
public interface RegistryService {

	/**
	 * Register a callback address with the given info
	 * 
	 * @param system
	 * @param function
	 * @param callbackAddress
	 */
	public void register(String system, String function, String callbackAddress);

	/**
	 * Unregister a given info
	 * 
	 * @param system
	 * @param function
	 */
	public void unregister(String system, String function, String callback);

	/**
	 * Publish message to registry.
	 * 
	 * @param system
	 * @param function
	 * @param data
	 *            bean contains data info
	 */
	public void publish(String system, String function, Object data);

	/**
	 * Notify the registry that the given system had been installed
	 * 
	 * @param system
	 */
	public void install(String system, Map<String, String> properties);

	/**
	 * List all installed systems
	 * 
	 * @return
	 */
	public String[] list();
}
