package com.kooobao.cws.web.index;

import java.util.List;

import com.kooobao.common.web.bean.SelfRefreshBean;
import com.kooobao.cws.domain.article.News;
import com.kooobao.cws.domain.article.Video;
import com.kooobao.cws.domain.book.Book;
import com.kooobao.cws.service.article.ArticleService;
import com.kooobao.cws.service.book.BookService;

public class IndexPageBean extends SelfRefreshBean {

	private List<Book> latestBooks;

	private List<Book> hotBooks;

	private List<News> latestNews;

	private List<Video> latestVideo;

	protected void refresh() {
		latestBooks = getBookService().getLatestBooks(2);
		hotBooks = getBookService().getHotBooks(2);
		latestNews = getArticleService().getLatestArticle(10, News.class);
		latestVideo = getArticleService().getLatestArticle(10,Video.class);
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

	public List<Video> getlatestVideo() {
		return latestVideo;
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
