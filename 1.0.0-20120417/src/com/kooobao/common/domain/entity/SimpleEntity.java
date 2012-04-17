package com.kooobao.common.domain.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class SimpleEntity {

	@Id
	@Column(name = "obj_id", columnDefinition = "decimal(10)")
	private long oid;

	public long getOid() {
		return oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	protected static final String extend(long number, int extendTo) {
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

}
