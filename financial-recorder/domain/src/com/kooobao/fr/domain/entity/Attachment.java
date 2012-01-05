package com.kooobao.fr.domain.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.SimpleEntity;

@Entity
@Table(name = "fr_attachment")
public class Attachment extends SimpleEntity {

	private String name;

	private Date createDate;

	@OneToOne
	private FileStorage file;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public FileStorage getFile() {
		return file;
	}

	public void setFile(FileStorage file) {
		this.file = file;
	}

}
