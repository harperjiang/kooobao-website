package com.kooobao.ecom.crm.customer.entity;

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

import com.kooobao.common.domain.entity.VersionEntity;

@Entity
@Table(name = "crm_hint")
public class Hint extends VersionEntity {

	@Column(name = "name")
	private String name;

	@Embedded
	private Contact contact = new Contact();

	@Column(name = "register_by")
	private String registerBy;

	@Column(name = "own_by")
	private String ownBy;

	@Column(name = "status")
	private String status;

	@Column(name = "update_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	@Column(name = "desc_text")
	private String description;

	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "hint")
	private List<HintFollowup> followups = new ArrayList<HintFollowup>();

	@ElementCollection
	@MapKeyColumn(name = "contact_type")
	@Column(name = "contact_info")
	@CollectionTable(name = "crm_hint_contact", joinColumns = { @JoinColumn(name = "hint_id", referencedColumnName = "obj_id") })
	private Map<String, String> otherContact = new HashMap<String, String>();

	@Column(name = "ref_id")
	private String refId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Contact getContact() {
		return contact;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, String> getOtherContact() {
		return otherContact;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

}
