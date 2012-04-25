package com.kooobao.cws.domain.book;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.collections.CollectionUtils;

import com.kooobao.common.domain.entity.VersionEntity;

@Entity
@Table(name = "cws_book_category")
public class Category extends VersionEntity {

	@OneToOne
	@JoinColumn(name = "parent_id", referencedColumnName = "obj_id")
	private Category parent;

	@OneToMany(mappedBy = "parent", cascade = { CascadeType.ALL })
	private List<Category> children = new ArrayList<Category>();

	@Column(name = "name")
	private String name;

	@Column(name = "brief")
	private String brief;

	@Column(name = "seq")
	private int sequence;

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

	protected void setParent(Category parent) {
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
		child.setParent(null);
	}

	public boolean isLeaf() {
		return CollectionUtils.isEmpty(getChildren());
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getLayeredName() {
		if (getParent() == null)
			return getName();
		return getParent().getLayeredName() + " / " + getName();
	}
}
