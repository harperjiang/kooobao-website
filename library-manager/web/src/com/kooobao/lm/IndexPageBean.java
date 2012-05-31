package com.kooobao.lm;

import java.util.List;

import com.kooobao.common.web.bean.SelfRefreshBean;
import com.kooobao.lm.article.NewsService;
import com.kooobao.lm.article.entity.News;
import com.kooobao.lm.book.BookService;
import com.kooobao.lm.book.entity.Book;

public class IndexPageBean extends SelfRefreshBean {

	private List<Book> popularBooks;

	private List<Book> newBooks;

	private List<Book> editorRecommendBooks;

	private List<Book> otherBorrowBooks;

	private List<News> articles;

	protected void refresh() {
		popularBooks = getBookService().getPopularBooks();
		newBooks = getBookService().getNewBooks();
		editorRecommendBooks = getBookService().getEditorRecommendBooks();
		otherBorrowBooks = getBookService().getOtherBorrowBooks();
		articles = getNewsService().getLatestNews();
	}

	public List<Book> getPopularBooks() {
		return popularBooks;
	}

	public List<Book> getNewBooks() {
		return newBooks;
	}

	public List<Book> getEditorRecommendBooks() {
		return editorRecommendBooks;
	}

	public List<Book> getOtherBorrowBooks() {
		return otherBorrowBooks;
	}

	public List<News> getArticles() {
		return articles;
	}

	private BookService bookService;

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	private NewsService newsService;

	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

}
