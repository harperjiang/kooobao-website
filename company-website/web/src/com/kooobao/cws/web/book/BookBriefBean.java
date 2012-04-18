package com.kooobao.cws.web.book;

import com.kooobao.common.web.bean.AbstractBean;

public class BookBriefBean extends AbstractBean {

	private String pictureUrl;

	private String title;

	private String brief;

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

}
