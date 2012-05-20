package com.kooobao.lm.profile.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.VersionEntity;

@Entity
@Table(name = "lm_visitor")
public class Visitor extends VersionEntity {

	@Column(name = "id")
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "status")
	private String status;

	@Column(name = "level")
	private int level;

	@Column(name = "deposit")
	private BigDecimal deposit;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "name", column = @Column(name = "addr_name")),
			@AttributeOverride(name = "location", column = @Column(name = "addr_loc")),
			@AttributeOverride(name = "phone", column = @Column(name = "addr_phone")) })
	private Address address;

	@ElementCollection
	@CollectionTable(name = "lm_visitor_addrs", joinColumns = { @JoinColumn(name = "visitor_id", referencedColumnName = "obj_id") })
	private List<Address> availableAddresses = new ArrayList<Address>();

	@OneToOne
	@JoinColumn(name = "info")
	private PersonalInfo info;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Address> getAvailableAddresses() {
		return availableAddresses;
	}

	public void setAvailableAddresses(List<Address> availableAddresses) {
		this.availableAddresses = availableAddresses;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public static enum Status {
		VALID, INVALID, LACK_BALANCE
	}

	public void setStatus(VisitorStatus status) {
		setStatus(status.name());
	}

	public PersonalInfo getInfo() {
		return info;
	}

	public void setInfo(PersonalInfo info) {
		this.info = info;
	}
}
