package com.kooobao.lm.book.dao;

import com.kooobao.common.domain.dao.Dao;
import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.book.entity.Category;

public interface BookDao extends Dao<Book> {

	PageSearchResult<Book> findByCategory(Category selectedCategory, int start,
			int size);

	PageSearchResult<Book> findByKeyword(String keyword, int start, int size);

	PageSearchResult<Book> getPopularBooks(int start, int size);

	PageSearchResult<Book> getLatestBooks(int start, int size);

	PageSearchResult<Book> getEditorRecommendBooks(int start, int size);
}