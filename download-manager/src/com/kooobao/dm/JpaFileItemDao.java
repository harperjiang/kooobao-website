package com.kooobao.dm;

import java.util.List;

import com.kooobao.common.domain.dao.AbstractJpaDao;

public class JpaFileItemDao extends AbstractJpaDao<FileItem> implements
		FileItemDao {

	@Override
	public List<FileItem> getFiles(FileItem root) {
		if (null == root)
			return getEntityManager().createQuery(
					"select f from FileItem f where f.parent is null",
					FileItem.class).getResultList();
		else
			return getEntityManager()
					.createQuery(
							"select f from FileItem f where f.parent = :p",
							FileItem.class).setParameter("p", root)
					.getResultList();
	}

	@Override
	public FileItem findByPath(String filePath) {
		return getEntityManager()
				.createQuery(
						"select f from FileItem f where f.filePath = :path",
						FileItem.class).setParameter("path", filePath)
				.getSingleResult();
	}

}
