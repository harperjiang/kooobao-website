package com.kooobao.cws.domain.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cws_resource_fileinfo")
public class FileInfo {

	@Id
	@Column(name = "uuid")
	private String uuid;

	@Column(name = "file_name")
	private String fileName;
	
	@Column(name="content_type")
	private String contentType;

	@Column(name="path")
	private String path;
	
	@Column(name = "score")
	private int score;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
}
