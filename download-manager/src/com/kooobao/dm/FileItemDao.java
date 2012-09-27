package com.kooobao.dm;

import java.util.List;

import com.kooobao.common.domain.dao.Dao;

public interface FileItemDao extends Dao<FileItem> {
	
	List<FileItem> getFiles(FileItem root);

	FileItem findByPath(String filePath);
}
