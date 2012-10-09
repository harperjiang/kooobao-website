package com.kooobao.registry;

import java.text.MessageFormat;

import org.slf4j.LoggerFactory;

public class BlankCallback implements RegistryCallback {

	@Override
	public void invoke(String system, String function, String data) {
		LoggerFactory.getLogger(getClass()).info(
				MessageFormat.format("Received message from {0}.{1}: {2}",
						system, function, data));
	}

}
