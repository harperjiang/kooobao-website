package com.kooobao.lm.article;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.lm.article.entity.News;

@ManagedBean(name = "newsBean")
@RequestScoped
public class NewsBean extends AbstractBean {

	@Override
	public void onPageLoad() {
		try {
			long oid = Long.parseLong(getParameter("news_id"));
			article = getNewsService().findNews(oid);
			if (null == article)
				navigate("not_found");
		} catch (Exception e) {
			navigate("not_found");
		}
	}

	private News article;

	public News getArticle() {
		return article;
	}

	public void setArticle(News article) {
		this.article = article;
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
