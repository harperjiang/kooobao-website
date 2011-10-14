package com.kooobao.gsm.domain.entity.order;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.SimpleEntity;
import com.kooobao.gsm.domain.entity.product.Product;

@Entity
@Table(name = "gsm_order_item")
public class OrderItem extends SimpleEntity {

	@OneToOne
	@JoinColumn(name = "product_id", columnDefinition = "decimal(10)", referencedColumnName = "obj_id")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "order_id", columnDefinition = "decimal(10)", referencedColumnName = "obj_id")
	private Order order;

	@Column(name = "unit_price", columnDefinition = "decimal(10,2)")
	private BigDecimal unitPrice;

	@Column(name = "item_count", columnDefinition = "decimal(5)")
	private int count;

	@Column(name = "prepared_count", columnDefinition = "decimal(5)")
	private int preparedCount;

	@Column(name = "sent_count", columnDefinition = "decimal(5)")
	private int sentCount;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		int oldCount = getCount();
		this.count = count;
		if (oldCount != getCount())
			setPreparedCount(0);
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getPreparedCount() {
		return preparedCount;
	}

	public void setPreparedCount(int preparedCount) {
		this.preparedCount = preparedCount;
	}

	public int getSentCount() {
		return sentCount;
	}

	public void setSentCount(int sentCount) {
		this.sentCount = sentCount;
	}

}
