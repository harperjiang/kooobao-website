package com.kooobao.lm.article.entity;

import javax.persistence.Column;

import com.kooobao.common.domain.entity.SimpleEntity;

public class News extends SimpleEntity {

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
