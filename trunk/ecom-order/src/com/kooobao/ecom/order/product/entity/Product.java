package com.kooobao.ecom.order.product.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.SimpleEntity;

@Entity
@Table(name = "order_product")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "type")
@DiscriminatorValue("BASIC")
public class Product extends SimpleEntity implements Comparable<Product> {

	@Column(name = "id")
	private String id;

	@Column(name = "name")
	private String name;

	/**
	 * Weight in kg
	 */
	@Column(name = "weight")
	private BigDecimal weight;

	@Column(name = "desc_text")
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	@Override
	public boolean equals(Object input) {
		if (input instanceof Product)
			return (null != getId())
					&& getId().equals(((Product) input).getId());
		return super.equals(input);
	}

	@Override
	public int hashCode() {
		return null == getId() ? super.hashCode() : getId().hashCode();
	}

	@Override
	public int compareTo(Product o) {
		if (null == o)
			return 1;
		return compare(getName(), o.getName());
	}
}
