package com.kooobao.gsm.domain.entity.product;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.kooobao.common.domain.entity.VersionEntity;

@Entity
@Table(name = "gsm_product", uniqueConstraints = @UniqueConstraint(name = "uk_code", columnNames = "code"))
public class Product extends VersionEntity {

	@Column(name = "category", columnDefinition = "varchar(20)")
	private String category;

	@Column(name = "code", columnDefinition = "varchar(50)")
	private String code;

	@Column(name = "name", columnDefinition = "varchar(100)")
	private String name;

	@Column(name = "image_path", columnDefinition = "varchar(200)")
	private String imagePath;

	@Column(name = "ref_unit_price", columnDefinition = "decimal(10,2)")
	private BigDecimal refUnitPrice;

	@Column(name = "net_weight", columnDefinition = "decimal(10,2)")
	private BigDecimal netWeight;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public BigDecimal getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(BigDecimal netWeight) {
		this.netWeight = netWeight;
	}

	public BigDecimal getRefUnitPrice() {
		return refUnitPrice;
	}

	public void setRefUnitPrice(BigDecimal refUnitPrice) {
		this.refUnitPrice = refUnitPrice;
	}

}
