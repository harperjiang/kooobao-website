package com.kooobao.ecom.user.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="meta_role")
public class Role {

	private String name;
	
	private List<Authority> authorities;
}