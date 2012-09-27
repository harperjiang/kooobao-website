package com.kooobao.dm;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import com.kooobao.common.domain.dao.AbstractMemoryDao;

public class MemoryFileItemDao extends AbstractMemoryDao<FileItem> implements
		FileItemDao {

	@Override
	public List<FileItem> getFiles(FileItem root) {
		List<FileItem> result = new ArrayList<FileItem>();
		for (FileItem item : getStorage().values()) {
			if (item.getParent().equals(root))
				result.add(item);
		}
		return result;
	}

	@Override
	public FileItem findByPath(String filePath) {
		for (FileItem item : getStorage().values()) {
			if (item.getFilePath().equals(filePath))
				return item;
		}
		throw new NoResultException();
	}

}
