package com.kooobao.cws.domain.article;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.VersionEntity;

@Entity
@Table(name = "cws_article_section")
public class Section extends VersionEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private String type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
