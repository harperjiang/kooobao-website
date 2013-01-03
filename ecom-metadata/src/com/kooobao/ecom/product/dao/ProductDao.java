package com.kooobao.ecom.product.dao;

import java.util.List;

import com.kooobao.common.domain.dao.Dao;
import com.kooobao.ecom.product.entity.Product;

public interface ProductDao extends Dao<Product> {

	List<Product> search(String name);

}
