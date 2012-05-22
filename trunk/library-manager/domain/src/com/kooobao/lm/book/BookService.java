package com.kooobao.lm.book;

import java.util.List;

import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.book.entity.Category;

public interface BookService {

	public List<String> getHotWords();

	public Book getBook(long oid);

	public Category getCategory(long categoryOid);

	public PageSearchResult<Book> searchBooks(String keyword, int start,
			int size);

	public List<Category> getRootCategories();

	public List<Book> getPopularBooks();

	public List<Book> getNewBooks();

	public List<Book> getEditorRecommendBooks();

	public List<Book> getOtherBorrowBooks();

	public List<Book> findRecommend(List<Book> selected);

	public List<Book> findRecommend(Book book);

	public PageSearchResult<Book> getBooksInCategory(Category selectedCategory,
			int start, int stop);
}
