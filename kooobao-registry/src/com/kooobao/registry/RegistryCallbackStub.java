package com.kooobao.registry;

import org.springframework.remoting.rmi.RmiProxyFactoryBean;

public class RegistryCallbackStub implements RegistryCallback {

	private RmiProxyFactoryBean rmi;

	public RegistryCallbackStub(String callbackAddress) {
		rmi = new RmiProxyFactoryBean();
		rmi.setServiceInterface(RegistryCallback.class);
		rmi.setServiceUrl(callbackAddress);
	}

	public String getAddress() {
		return rmi.getServiceUrl();
	}

	@Override
	public void invoke(String system, String function, String data) {
		if (null == rmi.getObject()) {
			// Initialization
			synchronized (rmi) {
				if (null == rmi.getObject())
					rmi.afterPropertiesSet();
			}
		}
		((RegistryCallback) rmi.getObject()).invoke(system, function, data);
	}

}
