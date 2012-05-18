package com.kooobao.lm.book;

import java.util.List;

import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.book.entity.Category;

public interface BookService {

	public List<Book> findRecommend(List<Book> selected);

	public List<Book> findRecommend(Book book);
	
	public List<String> getHotWords();

	public Book getBook(long oid);

	public List<Book> searchBooks(String keyword);
	
	public List<Category> getRootCategories();

	public List<Book> getPopularBooks();

	public List<Book> getNewBooks();

	public List<Book> getEditorRecommendBooks();

	public List<Book> getOtherBorrowBooks();

	public List<Book> getBooksInCategory(Category selectedCategory);

	public Category getCategory(long categoryOid);

	
}
