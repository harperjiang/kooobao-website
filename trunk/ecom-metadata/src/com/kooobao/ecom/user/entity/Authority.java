package com.kooobao.ecom.user.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.SimpleEntity;

@Entity
@Table(name = "meta_auth_item")
public class Authority extends SimpleEntity {

	private String name;

	private Category category;
}
