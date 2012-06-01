package com.kooobao.lm.book;

import java.util.List;

import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.book.dao.BookDao;
import com.kooobao.lm.book.dao.CategoryDao;
import com.kooobao.lm.book.dao.RecommendDao;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.book.entity.Category;
import com.kooobao.lm.optlog.dao.OperationLogDao;

public class DefaultBookService implements BookService {

	public Book getBook(long oid) {
		return getBookDao().find(oid);
	}

	public Category getCategory(long categoryOid) {
		return getCategoryDao().find(categoryOid);
	}

	public PageSearchResult<Book> getBooksInCategory(Category selectedCategory,
			int start, int size) {
		return getBookDao().findByCategory(selectedCategory, start, size);
	}

	public List<String> getHotWords() {
		return getOpLogDao().getHotSearchWords();
	}

	public PageSearchResult<Book> searchBooks(String keyword, int start,
			int size) {
		if (start == 0)
			// Record the first search only
			getOpLogDao().logSearch(keyword);
		return getBookDao().findByKeyword(keyword, start, size);
	}

	public List<Category> getRootCategories() {
		return getCategoryDao().getRootCategories();
	}

	static int LIMIT = 4;

	public List<Book> getPopularBooks() {
		PageSearchResult<Book> psr = getBookDao().getPopularBooks(0, LIMIT);
		return psr.getResult();
	}

	public List<Book> getNewBooks() {
		PageSearchResult<Book> psr = getBookDao().getLatestBooks(0, LIMIT);
		return psr.getResult();
	}

	public List<Book> getEditorRecommendBooks() {
		PageSearchResult<Book> psr = getBookDao().getEditorRecommendBooks(0,
				LIMIT);
		return psr.getResult();
	}

	public List<Book> getOtherBorrowBooks() {
		return getOpLogDao().getBorrowedBooks(1, LIMIT).getResult();
	}

	static int RECOMMEND_LIMIT = 10;

	public List<Book> findRecommend(List<Book> selected) {
		return getRecommendDao().recommend(null, selected, RECOMMEND_LIMIT);
	}

	public List<Book> findRecommend(Book book) {
		return getRecommendDao().recommend(null, book, RECOMMEND_LIMIT);
	}

	public Book save(Book book) {
		return getBookDao().store(book);
	}

	private BookDao bookDao;

	private CategoryDao categoryDao;

	private OperationLogDao opLogDao;

	private RecommendDao recommendDao;

	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public OperationLogDao getOpLogDao() {
		return opLogDao;
	}

	public void setOpLogDao(OperationLogDao opLogDao) {
		this.opLogDao = opLogDao;
	}

	public RecommendDao getRecommendDao() {
		return recommendDao;
	}

	public void setRecommendDao(RecommendDao recommendDao) {
		this.recommendDao = recommendDao;
	}

}
