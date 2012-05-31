package com.kooobao.lm.manage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.lm.article.NewsService;
import com.kooobao.lm.article.entity.News;

@ManagedBean(name = "manageArticleBean")
@SessionScoped
public class ManageArticleBean extends AbstractBean {

	private News article = new News();

	public News getArticle() {
		return article;
	}

	public void setArticle(News article) {
		this.article = article;
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
