package com.kooobao.ecom.order.delivery.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.SimpleEntity;

@Entity
@Table(name="order_delivery_site")
public class DeliverySite extends SimpleEntity {

	@Column(name="name")
	private String name;

	@Column(name="is_default")
	private boolean defaultSite;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDefaultSite() {
		return defaultSite;
	}

	public void setDefaultSite(boolean defaultSite) {
		this.defaultSite = defaultSite;
	}

}
