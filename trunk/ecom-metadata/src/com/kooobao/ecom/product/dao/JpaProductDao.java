package com.kooobao.ecom.product.dao;

import java.util.List;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.ecom.product.entity.Product;

public class JpaProductDao extends AbstractJpaDao<Product> implements
		ProductDao {

	@Override
	public List<Product> search(String name) {
		// Allow Search for at most 100 items;
		return getEntityManager()
				.createQuery(
						"select p from Product p where p.name like %:name%",
						Product.class).setParameter("name", name)
				.setMaxResults(100).getResultList();
	}

}
