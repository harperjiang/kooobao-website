package com.kooobao.gsm.domain.dao.jpa;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.gsm.domain.dao.ProductDao;
import com.kooobao.gsm.domain.entity.product.Product;

public class JpaProductDao extends AbstractJpaDao<Product> implements
		ProductDao {

	@Override
	public Product findProductById(String productId) {
		// TODO Auto-generated method stub
		return null;
	}

}
