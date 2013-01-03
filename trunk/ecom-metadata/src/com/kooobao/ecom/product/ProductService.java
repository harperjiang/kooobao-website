package com.kooobao.ecom.product;

import java.util.List;

import com.kooobao.ecom.product.entity.Product;

public interface ProductService {

	void addProduct(Product product);

	Product getProduct(long id);

	List<Product> search(ProductSearchBean search);
}
