package com.kooobao.ecom.order.delivery.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Convert;

import com.kooobao.common.domain.entity.VersionEntity;
import com.kooobao.ecom.common.entity.Contact;

@Entity
@Table(name = "order_delivery")
public class Delivery extends VersionEntity {

	@Column(name = "express_company")
	private String company = ExpressCompany.values()[0].name();

	@Column(name = "express_number")
	private String expressNumber;

	@Column(name = "status")
	@Convert("enumConverter")
	private DeliveryStatus status;

	@OneToOne
	@JoinColumn(name = "delivery_site", referencedColumnName = "obj_id")
	private DeliverySite deliverySite;

	@Column(name = "number")
	private String number;

	@OneToMany
	private List<DeliveryItem> items = new ArrayList<DeliveryItem>();

	@Embedded
	private Contact contact;

	@Column(name = "send_missed")
	private boolean sendMissed;

	@Column(name = "remark")
	private String remark;

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getExpressNumber() {
		return expressNumber;
	}

	public void setExpressNumber(String expressNumber) {
		this.expressNumber = expressNumber;
	}

	public DeliveryStatus getStatus() {
		return status;
	}

	public void setStatus(DeliveryStatus status) {
		this.status = status;
	}

	public DeliverySite getDeliverySite() {
		return deliverySite;
	}

	public void setDeliverySite(DeliverySite deliverySite) {
		this.deliverySite = deliverySite;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public boolean isSendMissed() {
		return sendMissed;
	}

	public void setSendMissed(boolean sendMissed) {
		this.sendMissed = sendMissed;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<DeliveryItem> getItems() {
		return items;
	}

	public void addItem(DeliveryItem di) {
		di.setHeader(this);
		getItems().add(di);
	}

}
