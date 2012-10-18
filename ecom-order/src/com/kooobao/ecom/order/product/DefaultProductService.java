package com.kooobao.ecom.order.product;

import java.util.List;

import com.kooobao.ecom.order.product.dao.ProductDao;
import com.kooobao.ecom.order.product.entity.Product;

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
		return null;
	}

	private ProductDao productDao;

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

}
