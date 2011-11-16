package com.kooobao.wsm.domain.entity;

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

}
