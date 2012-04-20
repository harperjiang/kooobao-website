package com.kooobao.cws.web.article;

import javax.faces.context.FacesContext;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.common.web.bean.JSFLifecycleAware;
import com.kooobao.cws.domain.article.Article;
import com.kooobao.cws.service.article.ArticleService;

public class ArticlePageBean extends AbstractBean implements JSFLifecycleAware {

	@Override
	public void onPageLoad() {
		try {
			long selectId =Long.parseLong(String.valueOf(FacesContext
					.getCurrentInstance().getExternalContext()
					.getRequestParameterMap().get("articleId")));
			selected = getArticleService().getArticle(selectId);
		} catch (NumberFormatException e) {
			selected = null;
		}
	}

	private Article selected;

	public Article getSelected() {
		return selected;
	}

	public void setSelected(Article selected) {
		this.selected = selected;
	}

	private ArticleService articleService;

	public ArticleService getArticleService() {
		return articleService;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

}
