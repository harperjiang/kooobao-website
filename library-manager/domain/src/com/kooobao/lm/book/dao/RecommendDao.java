package com.kooobao.lm.book.dao;

import java.util.List;

import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.profile.entity.Visitor;

public interface RecommendDao {

	List<Book> recommend(Visitor visitor, List<Book> selected, int limit);

	List<Book> recommend(Visitor visitor, Book book, int limit);

	List<Book> recommend(Visitor visitor, int limit);

}
