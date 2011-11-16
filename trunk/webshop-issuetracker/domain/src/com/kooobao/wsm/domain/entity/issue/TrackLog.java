package com.kooobao.wsm.domain.entity.issue;

import java.text.MessageFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.kooobao.wsm.domain.entity.SimpleEntity;

@Entity
@Table(name = "isue_track_log")
public class TrackLog extends SimpleEntity {

	@ManyToOne
	@JoinColumn(name = "issue_id", referencedColumnName = "obj_id")
	private Issue issue;

	@Column(name = "log_time", columnDefinition = "datetime", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date logTime;

	@Column(name = "input_by", columnDefinition = "varchar(10)", nullable = false)
	private String inputBy;

	@Column(name = "status_from", columnDefinition = "varchar(50)", nullable = false)
	private String statusFrom;

	@Column(name = "status_to", columnDefinition = "varchar(50)", nullable = false)
	private String statusTo;

	@Column(name = "assign_from", columnDefinition = "varchar(10)", nullable = true)
	private String assignFrom;

	@Column(name = "assign_to", columnDefinition = "varchar(10)", nullable = true)
	private String assignTo;

	@Column(name = "desc_text", columnDefinition = "text", nullable = true)
	private String description;

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public String getInputBy() {
		return inputBy;
	}

	public void setInputBy(String inputBy) {
		this.inputBy = inputBy;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatusFrom() {
		return statusFrom;
	}

	public void setStatusFrom(String statusFrom) {
		this.statusFrom = statusFrom;
	}

	public String getStatusTo() {
		return statusTo;
	}

	public void setStatusTo(String statusTo) {
		this.statusTo = statusTo;
	}

	public String getAssignFrom() {
		return assignFrom;
	}

	public void setAssignFrom(String assignFrom) {
		this.assignFrom = assignFrom;
	}

	public String getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}

	public String getStatusChange() {
		if (null != getStatusFrom() && null != getStatusFrom()
				&& !getStatusFrom().equals(getStatusTo())) {
			return MessageFormat.format("{0} -> {1}", getIssue()
					.getStatusTextFor(getStatusFrom()), getIssue()
					.getStatusTextFor(getStatusTo()));
		}
		return "";
	}

}
