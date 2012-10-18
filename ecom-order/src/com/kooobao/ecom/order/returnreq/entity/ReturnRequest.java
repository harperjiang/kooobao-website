package com.kooobao.ecom.order.returnreq.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Convert;

import com.kooobao.common.domain.entity.VersionEntity;

@Entity
@Table(name = "order_returnreq")
public class ReturnRequest extends VersionEntity {

	@Column(name = "status")
	@Convert("enumConverter")
	private RequestStatus status;

	@OneToMany(mappedBy = "header")
	private List<ReturnItem> items = new ArrayList<ReturnItem>();

	@OneToMany(mappedBy = "header")
	private List<RequestLog> logs = new ArrayList<RequestLog>();

	@Column(name = "express_company")
	private String expressCompany;

	@Column(name = "express_no")
	private String expressNumber;

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public String getExpressCompany() {
		return expressCompany;
	}

	public void setExpressCompany(String expressCompany) {
		this.expressCompany = expressCompany;
	}

	public String getExpressNumber() {
		return expressNumber;
	}

	public void setExpressNumber(String expressNumber) {
		this.expressNumber = expressNumber;
	}

	public List<ReturnItem> getItems() {
		return items;
	}

	public List<RequestLog> getLogs() {
		return logs;
	}

	public void addItem(ReturnItem item) {
		item.setHeader(this);
		this.getItems().add(item);
	}

	public void addLog(RequestLog log) {
		log.setHeader(this);
		this.getLogs().add(log);
	}
}
