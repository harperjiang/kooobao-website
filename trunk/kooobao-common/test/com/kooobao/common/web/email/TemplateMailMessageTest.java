package com.kooobao.common.web.email;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class TemplateMailMessageTest {

	@Test
	public void testComposeContent() {
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("name", "王小三");
		context.put("uuid", "44roadsjfidsid");
		context.put("website", "http://www.kooobao.cn/");
		TemplateMailMessage message = new TemplateMailMessage(
				"/com/kooobao/common/web/email/reg_mail.vm", context);
		assertEquals("",message.composeContent());
	}

}
