package com.kooobao.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ConfigLoaderTest {

	@Test
	public void testLoad() {
		assertNotNull(ConfigLoader.getInstance().load("sample", "a"));
		assertEquals("1",ConfigLoader.getInstance().load("sample", "a"));
		
	}

}
