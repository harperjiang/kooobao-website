package com.kooobao.gsm.domain.entity.group;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.kooobao.common.domain.entity.VersionEntity;

@Entity
@Table(name = "gsm_group", uniqueConstraints = @UniqueConstraint(name = "uk_code", columnNames = "code"))
public class Group extends VersionEntity {

	@Column(name = "status", columnDefinition = "varchar(10)")
	private String status = GroupStatus.NOT_START.name();

	@Column(name = "code", columnDefinition = "varchar(10)")
	private String code;

	@Column(name = "name", columnDefinition = "varchar(40)")
	private String name;

	@Column(name = "desc_text", columnDefinition = "text")
	private String description;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
