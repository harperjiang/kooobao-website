package com.kooobao.fr.domain.entity;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;

import com.kooobao.common.domain.entity.SimpleEntity;

@Entity
@Table(name = "fr_file_storage")
public class FileStorage extends SimpleEntity {

	@Column(name = "path", columnDefinition = "varchar(255)")
	private String path;

	@Column(name = "content_type", columnDefinition = "varchar(100)")
	private String contentType;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getAbbreviatedPath() {
		if (StringUtils.isEmpty(getPath()))
			return "";
		return getPath().substring(
				getPath().lastIndexOf(File.separatorChar) + 1);
	}
}
