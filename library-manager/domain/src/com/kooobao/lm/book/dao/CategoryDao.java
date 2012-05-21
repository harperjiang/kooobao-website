package com.kooobao.lm.book.dao;

import java.util.List;

import com.kooobao.common.domain.dao.Dao;
import com.kooobao.lm.book.entity.Category;

public interface CategoryDao extends Dao<Category> {

	List<Category> getRootCategories();

}
