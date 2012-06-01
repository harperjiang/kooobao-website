package com.kooobao.lm.book.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.SimpleEntity;

@Entity
@Table(name = "lm_book_category")
public class Category extends SimpleEntity {

	@Column(name = "name")
	private String name;

	@OneToOne
	@JoinColumn(name = "parent")
	private Category parent;

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	private List<Category> children = new ArrayList<Category>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public List<Category> getChildren() {
		return children;
	}

	public void addChild(Category child) {
		this.children.add(child);
		child.setParent(this);
	}

	public void removeChild(Category child) {
		this.children.remove(child);
	}

	@Override
	public String toString() {
		return getLayeredName();
	}

	public String getLayeredName() {
		if (getParent() == null)
			return getName();
		return getParent().getLayeredName() + " / " + getName();
	}
}
