package com.kooobao.lm.book;

import java.util.ArrayList;
import java.util.List;

import com.kooobao.common.domain.entity.SimpleEntity;

public class Category extends SimpleEntity {

	private String name;

	private Category parent;

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
	}
	
	public void removeChild(Category child) {
		this.children.remove(child);
	}

}
