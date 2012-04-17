package com.kooobao.common.domain.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class VersionEntity extends SimpleEntity {

	@Column(name = "obj_version", columnDefinition = "decimal(10)")
	@Version
	private long version;

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

}
