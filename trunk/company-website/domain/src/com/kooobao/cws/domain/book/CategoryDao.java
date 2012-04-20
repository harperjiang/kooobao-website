package com.kooobao.cws.domain.book;

import java.util.List;

import com.kooobao.common.domain.dao.Dao;

public interface CategoryDao extends Dao<Category> {

	List<Category> getCategory(Category parent);

}
