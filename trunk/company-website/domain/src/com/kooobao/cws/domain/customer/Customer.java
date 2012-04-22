package com.kooobao.cws.domain.customer;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.kooobao.common.domain.entity.VersionEntity;

@Entity
@Table(name = "cws_customer")
public class Customer extends VersionEntity {

	@Column(name = "email")
	private String email;

	@Column(name = "status")
	private String status;

	@Column(name = "last_login_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLoginTime;

	@Column(name = "is_company")
	private boolean companyUser;

	@Column(name = "receive_update")
	private boolean receiveUpdate;

	@ElementCollection
	@CollectionTable(name = "cws_customer_contact", joinColumns = { @JoinColumn(name = "customer_id", referencedColumnName = "obj_id") })
	private Set<Contact> contacts = new HashSet<Contact>();

	@Column(name = "level")
	private int level;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Set<Contact> getContacts() {
		return contacts;
	}

	public void addContact(Contact contact) {
		this.contacts.add(contact);
	}

	public void removeContact(Contact contact) {
		this.contacts.remove(contact);
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isCompanyUser() {
		return companyUser;
	}

	public void setCompanyUser(boolean companyUser) {
		this.companyUser = companyUser;
	}

	public Contact getContact(ContactType type) {
		for (Contact contact : getContacts()) {
			if (type.equals(contact.getType()))
				return contact;
		}
		return null;
	}

	public boolean isReceiveUpdate() {
		return receiveUpdate;
	}

	public void setReceiveUpdate(boolean receiveUpdate) {
		this.receiveUpdate = receiveUpdate;
	}

	public static enum Status {
		NEW, VALID
	}
}
