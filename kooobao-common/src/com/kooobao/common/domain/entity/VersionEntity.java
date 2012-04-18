package com.kooobao.common.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
public class VersionEntity extends SimpleEntity {

	@Column(name = "obj_version", columnDefinition = "decimal(10)")
	@Version
	private long version;

	@Column(name = "create_time", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
}
