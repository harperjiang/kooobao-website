package com.kooobao.gsm.domain.dao.jpa;

import javax.persistence.TypedQuery;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.gsm.domain.dao.ProductDao;
import com.kooobao.gsm.domain.entity.product.Product;

public class JpaProductDao extends AbstractJpaDao<Product> implements
		ProductDao {

	public Product findProductById(final String productId) {

		TypedQuery<Product> query = getEntityManager().createQuery(
				"select p from Product p where p.code = :code", Product.class);
		query.setParameter("code", productId);
		return query.getSingleResult();

	}

}
