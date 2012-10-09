package com.kooobao.registry;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.kooobao.common.spring.ApplicationContextHolder;

@ContextConfiguration(locations = { "/registry.xml" })
public class RegistryAccessorTest extends AbstractJUnit4SpringContextTests {

	@Test
	public void testRegister() {
		RegistryAccessor.getInstance().register();
		ApplicationContextHolder.getInstance().getApplicationContext()
				.getBean("registryService", RegistryService.class)
				.publish("demo", "a", "This is a sample data");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
