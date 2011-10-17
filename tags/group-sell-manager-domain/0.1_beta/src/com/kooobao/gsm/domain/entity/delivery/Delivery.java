package com.kooobao.gsm.domain.entity.delivery;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.Validate;

import com.kooobao.common.domain.entity.StatusUtils;
import com.kooobao.common.domain.entity.VersionEntity;
import com.kooobao.gsm.domain.entity.order.ContactInfo;
import com.kooobao.gsm.domain.entity.order.Order;
import com.kooobao.gsm.domain.entity.order.OrderItem;
import com.kooobao.gsm.domain.entity.rule.DeliveryTarget;
import com.kooobao.gsm.service.OrderService;

@Entity
@Table(name = "gsm_delivery")
public class Delivery extends VersionEntity implements DeliveryTarget {

	@Column(name = "company", columnDefinition = "varchar(20)")
	private String company;

	@Column(name = "number", columnDefinition = "varchar(30)")
	private String number;

	@Column(name = "status", columnDefinition = "varchar(10)")
	private String status = DOStatus.CREATED.name();

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "name", column = @Column(name = "contact_name")),
			@AttributeOverride(name = "location", column = @Column(name = "contact_location")),
			@AttributeOverride(name = "address", column = @Column(name = "contact_address")),
			@AttributeOverride(name = "phone", column = @Column(name = "contact_phone")) })
	private ContactInfo contact;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "header", fetch = FetchType.LAZY)
	private List<DeliveryItem> items;

	@Column(name = "create_date", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();

	@Column(name = "send_date", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date sendDate;

	@OneToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "order_id", columnDefinition = "decimal(10)", referencedColumnName = "obj_id")
	private Order order;

	@Column(name = "gross_weight", columnDefinition = "decimal(10,2)")
	private BigDecimal grossWeight = BigDecimal.ZERO;;

	@Column(name = "delivery_fee", columnDefinition = "decimal(10,2)")
	private BigDecimal deliveryFee = BigDecimal.ZERO;;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDisplayStatus() {
		return StatusUtils.text(DOStatus.valueOf(getStatus()));
	}

	public String getDisplayId() {
		return extend(getOid(), 10);
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public ContactInfo getContact() {
		if (null == contact)
			contact = new ContactInfo();
		return contact;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public List<DeliveryItem> getItems() {
		if (null == items)
			items = new ArrayList<DeliveryItem>();
		return items;
	}

	public void setItems(List<DeliveryItem> items) {
		this.items = items;
	}

	public void addItem(DeliveryItem item) {
		Validate.notNull(item);
		item.setHeader(this);
		getItems().add(item);
	}

	public void prepare() {
		Order order = null;
		for (DeliveryItem di : getItems()) {
			OrderItem oi = di.getOrderItem();
			order = oi.getOrder();
			oi.setPreparedCount(oi.getPreparedCount() + di.getCount());
		}
		OrderService.updateOrderPrepareStatus(order);
		setOrder(order);
		if (null == order.getDelivery())
			order.setDelivery(this);
	}

	public void deliver() {
		Order order = null;
		setStatus(DOStatus.DELIVERED.name());
		for (DeliveryItem di : getItems()) {
			OrderItem oi = di.getOrderItem();
			order = oi.getOrder();
			oi.setSentCount(oi.getSentCount() + di.getCount());
		}
		OrderService.updateOrderSendStatus(order);
	}

	public void cancel() {
		setStatus(DOStatus.CANCELLED.name());
		for (DeliveryItem di : getItems()) {
			OrderItem oi = di.getOrderItem();
			oi.setPreparedCount(oi.getPreparedCount() - di.getCount());
			oi.setSentCount(oi.getSentCount() - di.getCount());
		}
		OrderService.updateOrderSendStatus(getOrder());
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public BigDecimal getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(BigDecimal grossWeight) {
		this.grossWeight = grossWeight;
	}

	public BigDecimal getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(BigDecimal deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public String getDeliveryMethod() {
		return getCompany();
	}

	public String getAddress() {
		return getContact().getAddress();
	}

	public BigDecimal getAmount() {
		return null;
	}

	public BigDecimal getWeight() {
		return getGrossWeight();
	}

}
