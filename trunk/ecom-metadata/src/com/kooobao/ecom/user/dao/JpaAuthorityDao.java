package com.kooobao.ecom.user.dao;

import java.util.List;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.ecom.user.entity.Authority;
import com.kooobao.ecom.user.entity.Category;

public class JpaAuthorityDao extends AbstractJpaDao<Authority> implements
		AuthorityDao {

	@Override
	public Authority findAuthority(String id) {
		return getEntityManager()
				.createQuery("select a from Authority a where a.id = :id",
						Authority.class).setParameter("id", id)
				.getSingleResult();
	}

	@Override
	public Category findCategory(String id) {
		return getEntityManager()
				.createQuery("select c from Category c where c.id = :id",
						Category.class).setParameter("id", id)
				.getSingleResult();
	}

	@Override
	public List<Category> findAllCategories() {
		return getEntityManager().createQuery("select c from Category c",
				Category.class).getResultList();
	}

}
