package com.kooobao.lm.manage;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.kooobao.common.web.bean.PageSearchBean;
import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.article.NewsService;
import com.kooobao.lm.article.entity.News;

@ManagedBean(name = "manageArticleBean")
@SessionScoped
public class ManageArticleBean extends PageSearchBean {

	private List<News> articles;

	private String keyword;

	private News article = new News();

	public List<News> getArticles() {
		return articles;
	}

	public void setArticles(List<News> articles) {
		this.articles = articles;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public News getArticle() {
		return article;
	}

	public void setArticle(News article) {
		this.article = article;
	}

	public String search() {
		PageSearchResult<News> result = getNewsService().searchNews(keyword,
				getRecordStart(), getRecordStop());
		setRecordCount(result.getCount());
		setArticles(result.getResult());
		return "success";
	}

	public String select() {
		long newsId = Long.parseLong(getParameter("news_id"));
		setArticle(getNewsService().findNews(newsId));
		return "success";
	}

	public String save() {
		getNewsService().addNews(article);
		return "success";
	}

	@ManagedProperty("#{newsService}")
	private NewsService newsService;

	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

}
