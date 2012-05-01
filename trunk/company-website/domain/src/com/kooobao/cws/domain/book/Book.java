package com.kooobao.cws.domain.book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.VersionEntity;

@Entity
@Table(name = "cws_book")
public class Book extends VersionEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "brief")
	private String brief;

	@Column(name = "picture_url")
	private String pictureUrl;
	
	@Column(name = "level")
	private int level;

	@OneToOne
	@JoinColumn(name = "category", referencedColumnName = "obj_id")
	private Category category;

	@Column(name = "content", columnDefinition = "text")
	private String content;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
