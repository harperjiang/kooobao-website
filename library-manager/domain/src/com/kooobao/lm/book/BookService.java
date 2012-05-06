package com.kooobao.lm.book;

import java.util.List;

public interface BookService {

	public List<Book> findRecommend(List<Book> selected);

	public List<String> getHotWords();

	public Book getBook(long oid);

	public List<Book> searchBooks(String keyword);
}
