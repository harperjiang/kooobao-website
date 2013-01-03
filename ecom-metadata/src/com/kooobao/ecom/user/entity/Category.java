package com.kooobao.ecom.user.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.SimpleEntity;

@Entity
@Table(name = "meta_auth_category")
public class Category extends SimpleEntity {

	private String name;

	private List<Authority> authorities;

}
