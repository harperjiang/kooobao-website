package com.kooobao.fr.domain.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.Validate;

import com.kooobao.common.domain.entity.StatusUtils;
import com.kooobao.common.domain.entity.VersionEntity;

@Entity
@Table(name = "fr_record")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", columnDefinition = "varchar(15)", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("BASE")
public class FinancialRecord extends VersionEntity {

	@Column(name = "status", columnDefinition = "varchar(30)")
	String status;

	@Column(name = "record_date", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	Date recordDate;

	@Column(name = "create_date", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	Date createDate;

	@Column(name = "create_by", columnDefinition = "varchar(30)")
	String createBy;

	@Column(name = "follow_up", columnDefinition = "varchar(30)")
	String followup;

	@Column(name = "amount", columnDefinition = "decimal(10,2)")
	BigDecimal amount = BigDecimal.ZERO;

	@Column(name = "adjust_amount", columnDefinition = "decimal(10,2)")
	BigDecimal adjustAmount = BigDecimal.ZERO;

	@Column(name = "record_desc", columnDefinition = "text")
	String description;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "name", column = @Column(name = "with_name")),
			@AttributeOverride(name = "account", column = @Column(name = "with_account")),
			@AttributeOverride(name = "company", column = @Column(name = "with_company")) })
	private AccountInfo with;

	@OneToMany(mappedBy = "record", fetch = FetchType.LAZY, targetEntity = RecordHistory.class)
	List<RecordHistory> histories;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attachment", referencedColumnName = "obj_id")
	Attachment attachment;

	public FinancialRecord() {
		// Should not be invoked explicitly
		with = new AccountInfo();
		createDate = new Date();
		recordDate = new Date();
	}

	public String getStatus() {
		return status;
	}

	protected void setStatus(RecordStatus status) {
		this.status = status.name();
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<RecordHistory> getHistories() {
		return Collections.unmodifiableList(histories);
	}

	public BigDecimal getAdjustAmount() {
		return adjustAmount;
	}

	public void setAdjustAmount(BigDecimal adjustAmount) {
		this.adjustAmount = adjustAmount;
	}

	public AccountInfo getWith() {
		return with;
	}

	public String getFollowup() {
		return followup;
	}

	public void setFollowup(String followup) {
		this.followup = followup;
	}

	public Attachment getAttachment() {
		return attachment;
	}

	public void setAttachment(Attachment attachment) {
		this.attachment = attachment;
	}

	public void addHistory(RecordHistory history) {
		if (null == histories)
			histories = new ArrayList<RecordHistory>();
		history.setRecord(this);
		histories.add(history);
	}

	public String getStatusText() {
		return StatusUtils.text(RecordStatus.valueOf(getStatus()));
	}

	void transit(RecordStatus from, RecordStatus to, String operator,
			String reason) {
		Validate.isTrue(getStatus().equals(from.name()));
		setStatus(to);

		RecordHistory hist = new RecordHistory();
		hist.setDescription(reason);
		hist.setOperateDate(new Date());
		hist.setOperator(operator);
		hist.setOperation(to.name());
		addHistory(hist);
	}

}
