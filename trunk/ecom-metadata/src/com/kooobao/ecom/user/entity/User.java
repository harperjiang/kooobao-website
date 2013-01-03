package com.kooobao.ecom.user.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.kooobao.ecom.common.entity.ContactInfo;

@Entity
@Table(name = "meta_user")
public class User {

	private String id;

	private Role role;

	private List<Authority> authorities;
	
	private ContactInfo contact;
	
	public Set<Authority> getAllAuthorities() {
		return null;
	}
}
