package com.kooobao.ecom.user.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "meta_user")
public class User {

	private String id;

	private Role role;

	private List<Authority> authorities;
}
