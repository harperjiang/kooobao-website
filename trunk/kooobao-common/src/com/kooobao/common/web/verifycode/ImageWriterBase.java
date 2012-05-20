package com.kooobao.common.web.verifycode;


public abstract class ImageWriterBase implements ImageWriter {

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
