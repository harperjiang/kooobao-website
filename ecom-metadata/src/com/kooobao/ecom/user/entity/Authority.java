package com.kooobao.ecom.user.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "meta_auth_item")
public class Authority {

	private String name;

	private Category category;
}
