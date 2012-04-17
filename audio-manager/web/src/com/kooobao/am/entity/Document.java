package com.kooobao.am.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.VersionEntity;

@Entity
@Table(name="am_document")
public class Document extends VersionEntity {
	
	@Column(name="doc_id")
	private String docId;
	
	@Column(name="author")
	private String author;
	
	@Column(name="name")
	private String name;
	
	@Column(name="section_count")
	private int sectionCount;
	
	@Column(name="disk_path")
	private String diskPath;

	@Column(name="size")
	private int size;
	
	
}
