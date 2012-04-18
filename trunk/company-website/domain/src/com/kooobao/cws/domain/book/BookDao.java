package com.kooobao.cws.domain.book;

import java.util.List;

import com.kooobao.common.domain.dao.Dao;

public interface BookDao extends Dao<Book> {

	List<Book> getLatestBooks();
	
	List<Book> getHotBooks();
}
