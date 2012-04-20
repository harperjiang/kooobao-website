package com.kooobao.cws.domain.book;

import java.util.List;

import com.kooobao.common.domain.dao.Dao;

public interface BookDao extends Dao<Book> {

	int UNLIMITED = -1;

	List<Book> getLatestBooks(int LIMIT);

	List<Book> getHotBooks(int LIMIT);

	List<Book> getByCategory(Category category, int limit);

	List<Book> findBooks(String keyword);
}
