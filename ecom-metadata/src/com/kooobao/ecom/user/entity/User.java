package com.kooobao.ecom.user.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.VersionEntity;
import com.kooobao.ecom.common.entity.ContactInfo;

@Entity
@Table(name = "meta_user")
public class User extends VersionEntity {

	@Column(name = "id")
	private String id;

	@ManyToOne
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private Role role;

	@Embedded
	private ContactInfo contact;

	public User() {
		super();
		contact = new ContactInfo();
	}

	public Set<Authority> getAllAuthorities() {
		Set<Authority> set = new HashSet<Authority>();
		set.addAll(getRole().getAuthorities());
		return set;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public ContactInfo getContact() {
		return contact;
	}
}
