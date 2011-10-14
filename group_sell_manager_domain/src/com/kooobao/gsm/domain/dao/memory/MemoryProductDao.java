package com.kooobao.gsm.domain.dao.memory;

import java.math.BigDecimal;

import org.apache.commons.lang.Validate;

import com.kooobao.common.domain.dao.AbstractMemoryDao;
import com.kooobao.gsm.domain.dao.ProductDao;
import com.kooobao.gsm.domain.entity.product.Product;

public class MemoryProductDao extends AbstractMemoryDao<Product> implements
		ProductDao {

	@Override
	public Product findProductById(String productId) {
		Validate.notNull(productId);
		for (Product product : getStorage().values())
			if (productId.equals(product.getCode()))
				return product;

		Product product = new Product();
		product.setCode(productId);
		product.setName("Ref Product " + productId);
		product.setRefUnitPrice(BigDecimal.TEN);
		product.setNetWeight(new BigDecimal("0.23"));
		store(product);

		return product;
	}

}
