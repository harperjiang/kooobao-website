package com.kooobao.dm;

import java.io.InputStream;
import java.util.List;

public interface DownloadService {

	InputStream getFileContent(long fileId, long position);

	void indexFiles();
	
	void indexFile(FileItem file);

	FileItem getFile(long fileId);

	List<FileItem> listFiles(FileItem root);

}
