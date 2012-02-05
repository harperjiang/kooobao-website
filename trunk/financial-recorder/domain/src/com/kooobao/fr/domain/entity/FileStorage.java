package com.kooobao.fr.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.SimpleEntity;

@Entity
@Table(name = "fr_file_storage")
public class FileStorage extends SimpleEntity {

	@Column(name = "path", columnDefinition = "varchar(255)")
	private String path;

	@Column(name = "size", columnDefinition = "decimal(10)")
	private long size;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

}
