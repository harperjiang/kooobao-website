package com.kooobao.crm.common.wordsplit;

import java.text.MessageFormat;

public class Word {

	public Word(int location, String content) {
		super();
		setContent(content);
		setLocation(location);
	}

	private String content;

	private int location;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return MessageFormat.format("{0}@{1}", content, location);
	}
}
