package com.kooobao.cws.web.manage;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;

import com.kooobao.common.web.bean.PageSearchBean;
import com.kooobao.cws.domain.article.Article;
import com.kooobao.cws.domain.article.News;
import com.kooobao.cws.service.article.ArticleService;

public class ManageArticleBean extends PageSearchBean {

	@Override
	public String search() {
		List<Article> books = getArticleService().findArticles(
				getSearch().getKeyword());
		setArticles(books);
		return "success";
	}

	public String save() {
		getArticleService().saveArticle(getArticle());
		setArticle(new News());
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Article Saved",
						"Article Saved"));
		return "success";
	}

	public String edit() {
		UIData dataTable = (UIData) getComponent("resultDataTable");
		Article select = (Article) dataTable.getRowData();
		setArticle(select);
		return "success";
	}

	private List<Article> articles;

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	private SearchArticleBean search;

	public SearchArticleBean getSearch() {
		if (null == search)
			search = new SearchArticleBean();
		return search;
	}

	public void setSearch(SearchArticleBean search) {
		this.search = search;
	}

	private Article article;

	public Article getArticle() {
		if (null == article)
			article = new News();
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	private ArticleService articleService;

	public ArticleService getArticleService() {
		return articleService;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	public static class SearchArticleBean {
		private String keyword;

		public String getKeyword() {
			return keyword;
		}

		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}

	}

	public String getArticleType() {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("articleType");
	}

}
