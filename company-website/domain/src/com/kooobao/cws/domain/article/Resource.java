package com.kooobao.cws.domain.article;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.kooobao.cws.domain.resource.FileInfo;

@Entity
@DiscriminatorValue("RESOURCE")
public class Resource extends Article {

	public static String getType() {
		return "RESOURCE";
	}
	public String getArticleType() {
		return getType();
	}
	
	@OneToOne
	@JoinColumn(name="file",referencedColumnName="uuid")
	private FileInfo file;

	public FileInfo getFile() {
		if(null == file)
			file = new FileInfo();
		return file;
	}
	public void setFile(FileInfo file) {
		this.file = file;
	}
	
	
}
