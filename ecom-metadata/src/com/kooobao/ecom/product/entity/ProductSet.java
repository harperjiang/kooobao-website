package com.kooobao.ecom.product.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

@Entity
@DiscriminatorValue("SET")
public class ProductSet extends Product {

	@ElementCollection
	@CollectionTable(name = "meta_product_set", joinColumns = { @JoinColumn(name = "set_id", referencedColumnName = "obj_id") })
	private List<ProductUnit> products;

	public ProductSet() {
		super();
		products = new ArrayList<ProductUnit>();
	}

	public List<ProductUnit> getProducts() {
		return products;
	}

	public void add(ProductUnit product) {
		this.products.add(product);
	}

	public List<ProductUnit> getContent() {
		return products;
	}
}
