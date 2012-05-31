package com.kooobao.lm.article;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.LogFactory;

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
		} catch (Exception e) {
			LogFactory.getLog(getClass())
					.error("No article id was provided", e);
			FacesContext
					.getCurrentInstance()
					.getApplication()
					.getNavigationHandler()
					.handleNavigation(FacesContext.getCurrentInstance(), null,
							"not_found");
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
