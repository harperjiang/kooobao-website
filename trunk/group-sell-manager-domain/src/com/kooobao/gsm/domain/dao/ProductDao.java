package com.kooobao.gsm.domain.dao;

import com.kooobao.common.domain.dao.Dao;
import com.kooobao.gsm.domain.entity.product.Product;

public interface ProductDao extends Dao<Product> {

	public Product findProductById(String productId);
	
}
