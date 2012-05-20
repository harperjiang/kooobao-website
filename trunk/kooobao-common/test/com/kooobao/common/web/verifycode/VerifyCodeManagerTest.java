package com.kooobao.common.web.verifycode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class VerifyCodeManagerTest extends VerifyCodeManager {

	@Test
	public void testGenerate() throws IOException {
		ImageWriter iw = VerifyCodeManager.getInstance().generate(
				new VerifyKey());

		assertEquals("image/jpeg", iw.getContentType());
		
		FileOutputStream fos = new FileOutputStream("result.jpg");
		IOUtils.copy(iw.getInputStream(), fos);
		fos.close();
	}

	@Test
	public void testCreateKey() {
		assertNotNull(VerifyCodeManager.getInstance().createKey());
	}

}
