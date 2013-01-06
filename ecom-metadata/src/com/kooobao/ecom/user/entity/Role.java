package com.kooobao.ecom.user.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.VersionEntity;

@Entity
@Table(name = "meta_role")
public class Role extends VersionEntity {

	@Column(name = "name")
	private String name;

	@ManyToMany
	@JoinTable(name = "meta_role_auth", joinColumns = {
			@JoinColumn(name = "role_id", table = "meta_role", referencedColumnName = "obj_id"),
			@JoinColumn(name = "auth_id", table = "meta_auth_item", referencedColumnName = "id") })
	private List<Authority> authorities;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

}
