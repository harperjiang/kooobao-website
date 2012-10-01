package com.kooobao.crm.common.wordsplit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultDictDao implements DictDao {

	@Override
	public List<String> getWord(String part) {
		List<String> result = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		WordTreeNode current = root.get(part.charAt(0));
		if (null == current)
			return result;
		int index = 1;
		while (current != null) {
			sb.append(current.getContent());
			if (current.canStop)
				result.add(sb.toString());
			current = current.getChildren().get(part.charAt(index++));
		}
		return result;
	}

	Map<Character, WordTreeNode> root;

	public void init() {
		root = new HashMap<Character, WordTreeNode>();
		// Load Tree
	}

}
