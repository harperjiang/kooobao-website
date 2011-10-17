package com.kooobao.gsm.domain.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.springframework.orm.jpa.JpaCallback;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.gsm.domain.dao.ProductDao;
import com.kooobao.gsm.domain.entity.product.Product;

public class JpaProductDao extends AbstractJpaDao<Product> implements
		ProductDao {

	public Product findProductById(final String productId) {

		return getTemplate().execute(new JpaCallback<Product>() {
			public Product doInJpa(EntityManager em)
					throws PersistenceException {
				TypedQuery<Product> query = em.createQuery(
						"select p from Product p where p.code = :code",
						Product.class);
				query.setParameter("code", productId);
				return query.getSingleResult();
			}
		});

	}

}
