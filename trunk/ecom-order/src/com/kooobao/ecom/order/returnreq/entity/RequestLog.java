package com.kooobao.ecom.order.returnreq.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Convert;

import com.kooobao.common.domain.entity.SimpleEntity;

@Entity
@Table(name = "order_returnreq_log")
public class RequestLog extends SimpleEntity {

	@ManyToOne
	@JoinColumn(name = "request_id", referencedColumnName = "obj_id")
	private ReturnRequest header;

	@Column(name = "operator_id")
	private String operator;

	@Column(name = "from_status")
	@Convert("enumConverter")
	private RequestStatus fromStatus;

	@Column(name = "to_status")
	@Convert("enumConverter")
	private RequestStatus toStatus;

	@Column(name = "remark")
	private String remark;

	public ReturnRequest getHeader() {
		return header;
	}

	public void setHeader(ReturnRequest header) {
		this.header = header;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public RequestStatus getFromStatus() {
		return fromStatus;
	}

	public void setFromStatus(RequestStatus fromStatus) {
		this.fromStatus = fromStatus;
	}

	public RequestStatus getToStatus() {
		return toStatus;
	}

	public void setToStatus(RequestStatus toStatus) {
		this.toStatus = toStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
