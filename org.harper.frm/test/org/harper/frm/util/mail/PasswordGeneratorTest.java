package org.harper.frm.util.mail;

import org.junit.Test;

public class PasswordGeneratorTest {

	@Test
	public void testGenPassword() {
		for (int i = 0; i < 5; i++)
			System.out.println(PasswordGenerator.genPassword(10));
	}

}
