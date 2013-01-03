package com.kooobao.ecom.product;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.kooobao.ecom.product.dao.ProductDao;
import com.kooobao.ecom.product.entity.Product;

public class DefaultProductService implements ProductService {

	@Override
	public void addProduct(Product product) {
		getProductDao().store(product);
	}

	@Override
	public Product getProduct(long id) {
		return getProductDao().find(id);
	}

	@Override
	public List<Product> search(ProductSearchBean search) {
		// Validate Search Bean
		if(StringUtils.isEmpty(search.getName()))
			throw new IllegalArgumentException("Not enough info for search");
		return getProductDao().search(search.getName());
	}

	private ProductDao productDao;

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

}
