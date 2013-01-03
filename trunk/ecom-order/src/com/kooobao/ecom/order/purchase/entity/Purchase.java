package com.kooobao.ecom.order.purchase.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Convert;

import com.kooobao.common.domain.entity.VersionEntity;
import com.kooobao.ecom.common.entity.ContactInfo;
import com.kooobao.ecom.order.customer.entity.Customer;
import com.kooobao.ecom.order.delivery.entity.Delivery;

@Entity
@Table(name = "order_purchase")
public class Purchase extends VersionEntity {

	@Column(name = "status")
	@Convert("enumConverter")
	private PurchaseStatus status;

	@Column(name = "number")
	private String number;

	@OneToMany(mappedBy = "header")
	private List<PurchaseItem> items = new ArrayList<PurchaseItem>();

	// Customer Remark
	@Column(name = "customer_remark")
	private String customerRemark;

	// CSV Remark;
	@Column(name = "csv_remark")
	private String csvRemark;

	@Column(name = "surcharge_name")
	private String surchargeName;

	@Column(name = "surcharge_amount")
	private BigDecimal surchargeAmount;

	@Column(name = "ref_no")
	private String refno;

	@Embedded
	private ContactInfo contact = new ContactInfo();

	@Column(name = "total_amount")
	private BigDecimal totalAmount;

	@OneToOne
	@JoinColumn(name = "customer", referencedColumnName = "obj_id")
	private Customer customer;

	// External Status
	@Column(name = "ref_status")
	private String refStatus;

	// External Item
	@OneToMany(mappedBy = "header")
	private List<DisplayItem> dispItems = new ArrayList<DisplayItem>();

	// For Delivery Maintenance
	@Column(name = "delivery_status")
	@Convert("enumConverter")
	private PurchaseDeliveryStatus deliveryStatus;

	// Primary Delivery.
	// For single delivery order, this is the delivery to display
	@OneToOne
	@JoinColumn(name = "delivery", referencedColumnName = "obj_id")
	private Delivery delivery;

	// All the deliveries
	@ManyToMany
	@JoinTable(name = "order_purchase_delivery", joinColumns = { @JoinColumn(name = "purchase_id", referencedColumnName = "obj_id") }, inverseJoinColumns = { @JoinColumn(name = "delivery_id", referencedColumnName = "obj_id") })
	private List<Delivery> deliveries = new ArrayList<Delivery>();

	public PurchaseStatus getStatus() {
		return status;
	}

	public void setStatus(PurchaseStatus status) {
		this.status = status;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCustomerRemark() {
		return customerRemark;
	}

	public void setCustomerRemark(String customerRemark) {
		this.customerRemark = customerRemark;
	}

	public String getCsvRemark() {
		return csvRemark;
	}

	public void setCsvRemark(String csvRemark) {
		this.csvRemark = csvRemark;
	}

	public String getSurchargeName() {
		return surchargeName;
	}

	public void setSurchargeName(String surchargeName) {
		this.surchargeName = surchargeName;
	}

	public BigDecimal getSurchargeAmount() {
		return surchargeAmount;
	}

	public void setSurchargeAmount(BigDecimal surchargeAmount) {
		this.surchargeAmount = surchargeAmount;
	}

	public String getRefno() {
		return refno;
	}

	public void setRefno(String refno) {
		this.refno = refno;
	}

	public ContactInfo getContact() {
		return contact;
	}

	public void setContact(ContactInfo contact) {
		this.contact = contact;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getRefStatus() {
		return refStatus;
	}

	public void setRefStatus(String refStatus) {
		this.refStatus = refStatus;
	}

	public PurchaseDeliveryStatus getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(PurchaseDeliveryStatus deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public List<PurchaseItem> getItems() {
		return items;
	}

	public void addItem(PurchaseItem pi) {
		pi.setHeader(this);
		getItems().add(pi);
	}

	public List<DisplayItem> getDispItems() {
		return dispItems;
	}

	public void addDispItem(DisplayItem di) {
		di.setHeader(this);
		getDispItems().add(di);
	}

	public List<Delivery> getDeliveries() {
		return deliveries;
	}

	public void addDelivery(Delivery delivery) {
		if (null == getDelivery()) {
			setDelivery(delivery);
		}
		getDeliveries().add(delivery);
	}

}
