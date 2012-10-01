package com.kooobao.crm.common.wordsplit;

import java.util.Map;

public class WordTreeNode {

	Character content;

	Map<Character, WordTreeNode> children;

	boolean canStop;

	public WordTreeNode(Character content, boolean canStop) {
		this.content = content;
		this.canStop = canStop;
	}

	public Character getContent() {
		return content;
	}

	public void setContent(Character content) {
		this.content = content;
	}

	public Map<Character, WordTreeNode> getChildren() {
		return children;
	}

	public void setChildren(Map<Character, WordTreeNode> children) {
		this.children = children;
	}

	public boolean isCanStop() {
		return canStop;
	}

	public void setCanStop(boolean canStop) {
		this.canStop = canStop;
	}

}
