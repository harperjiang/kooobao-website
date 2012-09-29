package com.kooobao.crm.common.unique;

import java.util.HashMap;
import java.util.Map;

import com.kooobao.common.domain.entity.SimpleEntity;

public class UniqueEntry extends SimpleEntity {

	private String category;

	private Map<String, Map<String, String>> attributes = new HashMap<String, Map<String, String>>();

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Map<String, String> getAttributes(String section) {
		if (!attributes.containsKey(section))
			attributes.put(section, new HashMap<String, String>());
		return attributes.get(section);
	}

}
