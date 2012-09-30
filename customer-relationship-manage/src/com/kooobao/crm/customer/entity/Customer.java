package com.kooobao.crm.customer.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.persistence.annotations.Convert;

import com.kooobao.common.domain.entity.VersionEntity;

@Entity
@Table(name = "crm_customer")
public class Customer extends VersionEntity {

	@Column(name = "source")
	private String source;

	@Column(name = "id")
	private String id;

	@Column(name = "register_by")
	private String registerBy;

	@Column(name = "own_by")
	private String ownBy;

	@Column(name = "name")
	private String name;

	@Column(name = "status")
	private String status;

	@Column(name = "cust_nature")
	@Convert("enumConverter")
	private CustomerNature nature;

	@Embedded
	private Contact contact = new Contact();

	@ElementCollection
	@MapKeyColumn(name = "contact_type")
	@CollectionTable(name = "crm_customer_contact", joinColumns = { @JoinColumn(name = "customer_id", referencedColumnName = "obj_id") })
	private Map<String, String> otherContact = new HashMap<String, String>();

	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = { CascadeType.ALL }, mappedBy = "customer")
	private List<CustomerFollowup> followups = new ArrayList<CustomerFollowup>();

	@Column(name = "update_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	@Column(name = "desc_text")
	private String description;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRegisterBy() {
		return registerBy;
	}

	public void setRegisterBy(String registerBy) {
		this.registerBy = registerBy;
	}

	public String getOwnBy() {
		return ownBy;
	}

	public void setOwnBy(String ownBy) {
		this.ownBy = ownBy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CustomerNature getNature() {
		return nature;
	}

	public void setNature(CustomerNature nature) {
		this.nature = nature;
	}

	public Contact getContact() {
		return contact;
	}

	public Map<String, String> getOtherContact() {
		return otherContact;
	}

	public List<CustomerFollowup> getFollowups() {
		return followups;
	}

	public void addFollowup(CustomerFollowup f) {
		f.setCustomer(this);
		getFollowups().add(f);
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public CustomerStatus getStatus() {
		return CustomerStatus.valueOf(status);
	}

	public void setStatus(CustomerStatus status) {
		this.status = status.name();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
