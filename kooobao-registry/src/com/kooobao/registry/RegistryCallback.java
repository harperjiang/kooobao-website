package com.kooobao.registry;

/**
 * Application must export this interface to be able to receive message
 * notifications
 * 
 * @author harper
 * 
 */
public interface RegistryCallback {

	/**
	 * 
	 * @param system
	 * @param function
	 * @param data
	 */
	public void invoke(String system, String function, String data);

}
