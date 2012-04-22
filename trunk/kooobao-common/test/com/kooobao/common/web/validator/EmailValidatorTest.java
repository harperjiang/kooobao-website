package com.kooobao.common.web.validator;

import org.junit.Test;

public class EmailValidatorTest {

	@Test
	public void testValidate() {
		EmailValidator validator = new EmailValidator();
		validator.validate(null, null, "abc@def.com");
	}

}
