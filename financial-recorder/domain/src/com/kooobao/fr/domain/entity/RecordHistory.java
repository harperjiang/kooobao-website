package com.kooobao.fr.domain.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.kooobao.common.domain.entity.SimpleEntity;

@Entity
@Table(name = "fr_record_history")
public class RecordHistory extends SimpleEntity {

	@ManyToOne
	@JoinColumn(name = "record_id", referencedColumnName = "obj_id", columnDefinition = "decimal(10)")
	private FinancialRecord record;

	@Column(name = "operator")
	private String operator;

	@Column(name = "operate_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date operateDate;

	@Column(name = "operation")
	private String operation;

	@Column(name = "desc_text")
	private String description;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "attachment", referencedColumnName = "obj_id")
	private Attachment attachment;

	public FinancialRecord getRecord() {
		return record;
	}

	protected void setRecord(FinancialRecord record) {
		this.record = record;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getOperateDate() {
		return operateDate;
	}

	public void setOperateDate(Date operateDate) {
		this.operateDate = operateDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

}
