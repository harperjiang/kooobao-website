package com.kooobao.common.web.verifycode;

import java.io.InputStream;

public interface ImageWriter {

	InputStream getInputStream();
	
	String getContentType();
}
