package com.kooobao.lm.book.dao;

import java.util.List;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.lm.book.entity.Category;

public class JpaCategoryDao extends AbstractJpaDao<Category> implements
		CategoryDao {

	public List<Category> getRootCategories() {
		return getEntityManager()
				.createQuery(
						"select c from Category c where c.parent is null order by c.oid",
						Category.class).getResultList();
	}

}
