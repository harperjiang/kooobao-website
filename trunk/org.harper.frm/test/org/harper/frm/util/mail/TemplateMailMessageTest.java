package org.harper.frm.util.mail;

import org.junit.Test;

public class TemplateMailMessageTest {

	@Test
	public void testLoadTemplate() {
		new TemplateMailMessage(null){
			{
				setTemplateName("/org/harper/frm/util/mail/test_mail.xhtml");
			}
		}.loadTemplate();
	}

}
