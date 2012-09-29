package com.kooobao.crm.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Convert;

import com.kooobao.common.domain.entity.SimpleEntity;

@Entity
@Table(name = "crm_hint_followup")
public class HintFollowup extends SimpleEntity {

	@ManyToOne
	@JoinColumn(name = "hint_id", referencedColumnName = "obj_id")
	private Hint hint;

	@Column(name = "comment")
	private String comment;

	@Column(name = "method")
	@Convert("enumConverter")
	private FollowupMethod method;

	@Column(name = "reference")
	private String reference;

	@Column(name = "own_by")
	private String ownBy;

	public Hint getHint() {
		return hint;
	}

	public void setHint(Hint hint) {
		this.hint = hint;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public FollowupMethod getMethod() {
		return method;
	}

	public void setMethod(FollowupMethod method) {
		this.method = method;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getOwnBy() {
		return ownBy;
	}

	public void setOwnBy(String ownBy) {
		this.ownBy = ownBy;
	}

}