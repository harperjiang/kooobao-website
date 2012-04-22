package com.kooobao.cws.web.index;

import java.util.List;

import com.kooobao.common.web.bean.SelfRefreshBean;
import com.kooobao.cws.domain.article.News;
import com.kooobao.cws.domain.article.Resource;
import com.kooobao.cws.domain.book.Book;
import com.kooobao.cws.service.article.ArticleService;
import com.kooobao.cws.service.book.BookService;

public class IndexPageBean extends SelfRefreshBean {

	private List<Book> latestBooks;

	private List<Book> hotBooks;

	private List<News> latestNews;

	private List<Resource> latestResources;

	protected void refresh() {
		latestBooks = getBookService().getLatestBooks();
		hotBooks = getBookService().getLatestBooks();
		latestNews = getArticleService().getLatestArticle(10, News.class);
		latestResources = getArticleService().getLatestArticle(10,Resource.class);
	}

	public List<Book> getLatestBooks() {
		return latestBooks;
	}

	public List<Book> getHotBooks() {
		return hotBooks;
	}

	public List<News> getLatestNews() {
		return latestNews;
	}

	public List<Resource> getLatestResources() {
		return latestResources;
	}

	private BookService bookService;

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	private ArticleService articleService;

	public ArticleService getArticleService() {
		return articleService;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

}