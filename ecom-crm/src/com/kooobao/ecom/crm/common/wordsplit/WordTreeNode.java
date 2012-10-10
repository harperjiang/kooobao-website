package com.kooobao.ecom.crm.common.wordsplit;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "crm_wordsplit_wtn")
public class WordTreeNode {

	@Id
	@Column(name = "id")
	private long id;

	// @Column(name = "content")
	Character content;

	@ManyToOne
	@JoinColumn(name = "parent_id", referencedColumnName = "id")
	WordTreeNode parent;

	@Column(name = "parent_id", updatable = false, insertable = false)
	long parentId;

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	@MapKey(name = "content")
	Map<Character, WordTreeNode> children = new HashMap<Character, WordTreeNode>();

	@Column(name = "can_stop")
	boolean canStop;

	public WordTreeNode() {

	}

	public WordTreeNode(Character content, boolean canStop) {
		this.setContent(content);
		this.setCanStop(canStop);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Character getContent() {
		return content;
	}

	public void setContent(Character content) {
		this.content = content;
	}

	public WordTreeNode getParent() {
		return parent;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public void setParent(WordTreeNode parent) {
		this.parent = parent;
		this.parentId = parent.getId();
		if (null != parent)
			parent.getChildren().put(getContent(), this);
	}

	public Map<Character, WordTreeNode> getChildren() {
		return children;
	}

	public boolean isCanStop() {
		return canStop;
	}

	public void setCanStop(boolean canStop) {
		this.canStop = canStop;
	}

}
