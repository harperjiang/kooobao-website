package com.kooobao.lm.profile.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.StatusUtils;
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
	private BigDecimal deposit = BigDecimal.ZERO;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "default_addr")
	private Address address;

	@OneToMany(mappedBy = "visitor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Address> availableAddresses = new ArrayList<Address>();

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "info")
	private PersonalInfo info;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "inst_info")
	private InstituteInfo instInfo;

	@OneToMany(mappedBy = "visitor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<BalanceLog> balanceLogs = new ArrayList<BalanceLog>();

	@Column(name = "visitor_type")
	private String type;

	@Column(name = "recommend_by")
	private String recommendBy;

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

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setStatus(VisitorStatus status) {
		setStatus(status.name());
	}

	public PersonalInfo getInfo() {
		if (getType() == VisitorType.PERSON)
			return info;
		return null;
	}

	public void setInfo(PersonalInfo info) {
		if (getType() == VisitorType.PERSON)
			this.info = info;
	}

	public InstituteInfo getInstInfo() {
		if (getType() == VisitorType.INSTITUTE)
			return instInfo;
		return null;
	}

	public void setInstInfo(InstituteInfo instInfo) {
		if (getType() == VisitorType.INSTITUTE)
			this.instInfo = instInfo;
	}

	public void addAddress(Address addr) {
		if (null == getAddress())
			setAddress(addr);
		addr.setVisitor(this);
		getAvailableAddresses().add(addr);
	}

	public void removeAddress(Address addr) {
		addr.setVisitor(null);
		getAvailableAddresses().remove(addr);
	}

	public List<BalanceLog> getBalanceLogs() {
		return balanceLogs;
	}

	public VisitorType getType() {
		return VisitorType.valueOf(type);
	}

	public void setType(VisitorType type) {
		this.type = type.name();
	}

	public String getRecommendBy() {
		return recommendBy;
	}

	public void setRecommendBy(String recommendBy) {
		this.recommendBy = recommendBy;
	}

	public String getTypeText() {
		return StatusUtils.text(getType());
	}

	public String getStatusText() {
		return StatusUtils.text(VisitorStatus.valueOf(getStatus()));
	}
}
