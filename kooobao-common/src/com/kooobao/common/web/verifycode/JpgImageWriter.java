package com.kooobao.common.web.verifycode;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class JpgImageWriter extends ImageWriterBase {

	public InputStream getInputStream() {
		BufferedImage bi = new BufferedImage(80, 36,
				BufferedImage.TYPE_INT_RGB);
		new ImageWriterHelper().write(bi.getGraphics(), getContent(), 80, 36);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(bi, "jpg", baos);
			baos.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return new ByteArrayInputStream(baos.toByteArray());
	}

	public String getContentType() {
		return "image/jpeg";
	}
}
