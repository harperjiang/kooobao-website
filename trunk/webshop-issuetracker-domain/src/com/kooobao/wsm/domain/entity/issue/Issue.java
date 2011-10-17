package com.kooobao.wsm.domain.entity.issue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.Validate;

import com.kooobao.wsm.domain.entity.VersionEntity;

/**
 * Issue means an issue to follow up.
 * 
 * @author Harper
 * 
 */
@Entity
@Table(name = "isue_main")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "category", columnDefinition = "varchar(40)")
public class Issue extends VersionEntity {

	@Column(name = "subject", columnDefinition = "varchar(100)", nullable = false)
	private String subject;

	@Column(name = "status", columnDefinition = "varchar(40)", nullable = false)
	private String status;

	@Column(name = "severity", columnDefinition = "varchar(20)", nullable = false)
	private String severity;

	@Column(name = "customer", columnDefinition = "varchar(40)", nullable = false)
	private String customer;

	@Column(name = "contact", columnDefinition = "text", nullable = true)
	private String contact;

	@Column(name = "issue_time", columnDefinition = "datetime", nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date issueTime;

	@Column(name = "create_time", columnDefinition = "datetime", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	@Column(name = "creator_id", columnDefinition = "varchar(10)", nullable = false)
	private String creatorId;

	@Column(name = "follower_id", columnDefinition = "varchar(10)", nullable = true)
	private String followerId;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "issue", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("logTime asc")
	private List<TrackLog> trackLogs;

	@Column(name = "desc_text", columnDefinition = "text", nullable = true)
	private String description;

	@Column(name = "solution_text", columnDefinition = "text", nullable = true)
	private String solution;

	public Issue() {
		super();
		this.createTime = new Date();
		this.issueTime = new Date();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getFollowerId() {
		return followerId;
	}

	public void setFollowerId(String followerId) {
		this.followerId = followerId;
	}

	public List<TrackLog> getTrackLogs() {
		if (null == trackLogs)
			trackLogs = new ArrayList<TrackLog>();
		return trackLogs;
	}

	public void addTrackLog(TrackLog log) {
		Validate.notNull(log);
		Validate.notNull(log.getStatusTo());
		Validate.notNull(log.getAssignTo());

		getTrackLogs().add(log);

		log.setIssue(this);

		log.setAssignFrom(getFollowerId());
		setFollowerId(log.getAssignTo());

		log.setStatusFrom(getStatus());
		setStatus(log.getStatusTo());
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getStatus() {
		return status;
	}

	public String getStatusText() {
		return getStatus();
	}

	protected void setStatus(String status) {
		this.status = status;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Date getIssueTime() {
		return issueTime;
	}

	public void setIssueTime(Date issueTime) {
		this.issueTime = issueTime;
	}

	public String getDisplayId() {
		return extend(getOid(), 8);
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(Severity severity) {
		this.severity = severity.name();
	}

	public String getSolution() {
		return solution;
	}

	public String getShortSolution() {
		return null == solution ? null : (solution.length() > 40 ? solution
				.substring(0, 40) : solution);
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	static final String extend(long number, int extendTo) {
		if (String.valueOf(number).length() >= extendTo) {
			return String.valueOf(number);
		}
		StringBuilder sb = new StringBuilder();
		sb.append(number);
		while (sb.length() < extendTo) {
			sb.insert(0, '0');
		}
		return sb.toString();
	}

	protected String getStatusTextFor(String status) {
		return status;
	}

	public boolean isEditable() {
		return true;
	}
}
