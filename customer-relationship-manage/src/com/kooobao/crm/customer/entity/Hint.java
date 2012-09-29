package com.kooobao.crm.customer.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.VersionEntity;

@Entity
@Table(name = "crm_hint")
public class Hint extends VersionEntity {

	@Column(name = "name")
	private String name;

	@Embedded
	private Contact contact;

	@Column(name = "register_by")
	private String registerBy;

	@Column(name = "own_by")
	private String ownBy;

	@Column(name = "status")
	private String status;

	@Column(name = "update_time")
	private Date updateTime;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "hint")
	private List<HintFollowup> followups = new ArrayList<HintFollowup>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
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

	public HintStatus getStatus() {
		return HintStatus.valueOf(status);
	}

	public void setStatus(HintStatus status) {
		this.status = status.name();
	}

	public List<HintFollowup> getFollowups() {
		return followups;
	}

	public void addFollowup(HintFollowup followup) {
		this.getFollowups().add(followup);
		followup.setHint(this);
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
