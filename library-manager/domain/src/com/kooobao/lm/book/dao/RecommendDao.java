package com.kooobao.lm.book.dao;

import java.util.List;

import com.kooobao.lm.book.entity.Book;

public interface RecommendDao {

	List<Book> recommend(List<Book> selected, int limit);

	List<Book> recommend(Book book, int limit);

}
