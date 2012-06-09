package com.kooobao.fr.domain.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.SimpleEntity;

@Entity
@Table(name = "fr_attachment")
public class Attachment extends SimpleEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "size")
	private long size;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
	@JoinColumn(name = "file_id", referencedColumnName = "obj_id")
	private FileStorage file;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateDate() {
		return getCreateTime();
	}

	public void setCreateDate(Date createDate) {
		this.setCreateTime(createDate);
	}

	public FileStorage getFile() {
		return file;
	}

	public void setFile(FileStorage file) {
		this.file = file;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

}
