package com.kooobao.dm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.VersionEntity;

@Entity
@Table(name = "dm_file_item")
public class FileItem extends VersionEntity {

	@Column(name = "file_path")
	private String filePath;

	@Column(name = "display_name")
	private String displayName;

	@ManyToOne
	@JoinColumn(name = "parent", referencedColumnName = "obj_id")
	private FileItem parent;

	@Column(name = "is_file")
	private boolean file;

	public long getFileId() {
		return getOid();
	}

	public void setFileId(long fileId) {
		setOid(fileId);
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public FileItem getParent() {
		return parent;
	}

	public void setParent(FileItem parent) {
		this.parent = parent;
	}

	public boolean isFile() {
		return file;
	}

	public void setFile(boolean file) {
		this.file = file;
	}

}
