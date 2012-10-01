package com.kooobao.crm.common.unique;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UniqueEntry {

	private long oid;

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

	public long getOid() {
		return oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

}
