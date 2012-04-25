package com.kooobao.cws.domain.book;

import java.util.List;

import com.kooobao.common.domain.dao.AbstractJpaDao;

public class JpaCategoryDao extends AbstractJpaDao<Category> implements
		CategoryDao {

	@Override
	public List<Category> getCategory(Category parent) {
		if (null == parent)
			return getEntityManager().createQuery(
					"select c from Category c where c.parent is null",
					Category.class).getResultList();
		return getEntityManager()
				.createQuery("select c from Category c where c.parent = :p",
						Category.class).setParameter("p", parent)
				.getResultList();
	}

}
