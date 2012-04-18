package com.kooobao.cws.domain.book;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.VersionEntity;

@Entity
@Table(name = "cws_book_category")
public class Category extends VersionEntity {

	@OneToOne
	@JoinColumn(name = "parent_id", referencedColumnName = "obj_id")
	private Category parent;

	@Column(name = "name")
	private String name;

	@Column(name = "brief")
	private String brief;

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

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

}
