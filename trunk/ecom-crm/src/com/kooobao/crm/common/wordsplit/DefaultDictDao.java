package com.kooobao.crm.common.wordsplit;

import java.util.ArrayList;
import java.util.List;

import com.kooobao.common.domain.dao.AbstractJpaDao;

public class DefaultDictDao extends AbstractJpaDao<WordTreeNode> implements
		DictDao {

	@Override
	public List<String> getWord(String part) {
		List<String> result = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		WordTreeNode current = root;
		int index = 0;
		while (current != null) {
			sb.append(current.getContent());
			if (current.canStop) {
				result.add(sb.toString());
				sb = new StringBuilder();
				current = root;
			}
			if (index == part.length())
				break;
			current = current.getChildren().get(part.charAt(index++));
		}
		return result;
	}

	WordTreeNode root;

	public void init() {
		root = getRootNode();
	}

	public WordTreeNode getRootNode() {
		return getEntityManager().createQuery(
				"select w from WordTreeNode w where w.id = -1",
				WordTreeNode.class).getSingleResult();
	}

}
