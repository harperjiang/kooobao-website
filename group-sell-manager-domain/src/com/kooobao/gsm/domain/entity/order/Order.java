package com.kooobao.gsm.domain.entity.order;

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

import com.kooobao.common.domain.entity.SimpleEntity;
import com.kooobao.common.domain.entity.StatusUtils;
import com.kooobao.common.domain.entity.VersionEntity;
import com.kooobao.gsm.domain.entity.delivery.Delivery;
import com.kooobao.gsm.domain.entity.rule.DeliveryMethod;
import com.kooobao.gsm.domain.entity.rule.DeliveryTarget;

@Entity
@Table(name = "gsm_order")
public class Order extends VersionEntity implements DeliveryTarget {

	@Column(name = "group_code", columnDefinition = "varchar(20)")
	private String group;

	@Column(name = "ref_number", columnDefinition = "varchar(30)")
	private String refNumber;

	@Column(name = "customer", columnDefinition = "varchar(30)")
	private String customer;

	@Column(name = "status", columnDefinition = "varchar(20)")
	private String status = OrderStatus.CONFIRMED.name();

	@Column(name = "delivery_status", columnDefinition = "varchar(20)")
	private String deliveryStatus = DeliveryStatus.NOT_PREPARED.name();

	@Column(name = "create_date", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();

	@Column(name = "pay_date", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date payDate;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "name", column = @Column(name = "cont_name")),
			@AttributeOverride(name = "location", column = @Column(name = "cont_location")),
			@AttributeOverride(name = "address", column = @Column(name = "cont_address")),
			@AttributeOverride(name = "phone", column = @Column(name = "cont_phone")) })
	private ContactInfo contact;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "order")
	private List<OrderItem> items;

	@Column(name = "adjust", columnDefinition = "decimal(10,2)")
	private BigDecimal adjust = BigDecimal.ZERO;

	@Column(name = "total_amount", columnDefinition = "decimal(10,2)")
	private BigDecimal totalAmount = BigDecimal.ZERO;

	@Column(name = "remark", columnDefinition = "text")
	private String remark;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "delivery", referencedColumnName = "obj_id")
	private Delivery delivery;

	@Column(name = "expect_delivery_method", columnDefinition = "varchar(10)")
	private String expectDeliveryMethod;

	@Column(name = "paid_amount", columnDefinition = "decimal(10,2)")
	private BigDecimal paidAmount = BigDecimal.ZERO;

	@Column(name = "gross_weight", columnDefinition = "decimal(10,2)")
	private BigDecimal grossWeight = BigDecimal.ZERO;

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public ContactInfo getContact() {
		if (null == contact)
			contact = new ContactInfo();
		return contact;
	}

	public List<OrderItem> getItems() {
		if (null == items)
			items = new ArrayList<OrderItem>();
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public String getRefNumber() {
		return refNumber;
	}

	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFullStatus() {
		return StatusUtils.text(OrderStatus.valueOf(getStatus())) + "-"
				+ StatusUtils.text(DeliveryStatus.valueOf(getDeliveryStatus()));
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public BigDecimal getAdjust() {
		return adjust;
	}

	public void setAdjust(BigDecimal adjust) {
		this.adjust = adjust;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public void addItem(OrderItem item) {
		Validate.notNull(item);
		item.setOrder(this);
		getItems().add(item);
	}

	public String getExpectDeliveryMethod() {
		return expectDeliveryMethod;
	}

	public String getExpectDeliveryMethodDesc() {
		return StatusUtils.text(DeliveryMethod.valueOf(expectDeliveryMethod));
	}

	public void setExpectDeliveryMethod(String expectDeliveryMethod) {
		this.expectDeliveryMethod = expectDeliveryMethod;
	}

	public String getDisplayId() {
		return SimpleEntity.extend(getOid(), 8);
	}

	public boolean isModifyable() {
		return isCancellable();
	}

	public boolean isCancellable() {
		return !(DeliveryStatus.DELIVERED.name().equals(getDeliveryStatus()) || DeliveryStatus.PARTIALLY_DELIVERED
				.name().equals(getDeliveryStatus()));
	}

	public boolean isDeliveryPreparable() {
		return OrderStatus.CONFIRMED.name().equals(getStatus());
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getDeliveryMethod() {
		return getExpectDeliveryMethod();
	}

	public String getAddress() {
		return getContact().getAddress();
	}

	public BigDecimal getAmount() {
		return getTotalAmount();
	}

	public BigDecimal getWeight() {
		return getGrossWeight();
	}

	public BigDecimal getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(BigDecimal grossWeight) {
		this.grossWeight = grossWeight;
	}
}
