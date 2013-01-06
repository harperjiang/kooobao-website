package com.kooobao.ecom.user.dao;

import java.util.List;

import com.kooobao.ecom.user.entity.Authority;
import com.kooobao.ecom.user.entity.Category;

public interface AuthorityDao {

	public Authority findAuthority(String id);
	
	public Category findCategory(String id);
	
	public List<Category> findAllCategories();
}
