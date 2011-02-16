package org.harper.ourwebsite.domain.entity.profile;

import org.harper.ourwebsite.domain.entity.common.Entity;

public class Group extends Entity {

	private String id;

	private String name;

	private int accessLevel;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}

}
