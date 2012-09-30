package com.kooobao.crm.common.unique;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.kooobao.common.domain.entity.SimpleEntity;

public class UniqueEntry extends SimpleEntity {

	private String category;

	private Map<String, Collection<String>> attributes = new HashMap<String, Collection<String>>();

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Map<String, Collection<String>> getAttributes() {
		return attributes;
	}

}
