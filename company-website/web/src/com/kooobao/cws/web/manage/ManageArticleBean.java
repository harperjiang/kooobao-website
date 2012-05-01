package com.kooobao.cws.web.manage;

import java.io.File;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;

import com.kooobao.common.web.Utilities;
import com.kooobao.common.web.bean.PageSearchBean;
import com.kooobao.common.web.fileupload.FileBean;
import com.kooobao.common.web.fileupload.MultipartRequestWrapper;
import com.kooobao.cws.domain.article.Article;
import com.kooobao.cws.domain.article.News;
import com.kooobao.cws.domain.article.Resource;
import com.kooobao.cws.domain.article.Video;
import com.kooobao.cws.domain.resource.FileInfo;
import com.kooobao.cws.service.article.ArticleService;

public class ManageArticleBean extends PageSearchBean {

	@Override
	public String search() {
		if (!StringUtils.isEmpty(getSearch().getKeyword())) {
			List<Article> books = getArticleService().findArticles(
					getSearch().getKeyword());
			setArticles(books);
		}
		return "success";
	}

	public String save() {
		if (getArticle().getOid() == 0) {
			// New Article, verify the hidden type
			String type = getArticleType();
			Article newArticle = null;
			if (!StringUtils.isEmpty(type)) {
				if (Video.getType().equals(type)) {
					newArticle = new Video();
				}
				if (Resource.getType().equals(type)) {
					Resource resource = new Resource();
					resource.setFile(getFileInfo());
					extractFileInfo(resource.getFile());
					newArticle = resource;
				}
			}
			if (null != newArticle) {
				newArticle.setTitle(getArticle().getTitle());
				newArticle
						.setArticleAbstract(getArticle().getArticleAbstract());
				newArticle.setContent(getArticle().getContent());
				newArticle.setSection(getArticle().getSection());
				setArticle(newArticle);
			}
		}

		getArticleService().saveArticle(getArticle());
		reset();
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Article Saved",
						"Article Saved"));
		return search();
	}

	private void extractFileInfo(FileInfo file) {
		FileBean fb = ((MultipartRequestWrapper) FacesContext
				.getCurrentInstance().getExternalContext().getRequest())
				.getFile("fileText");
		if (null == fb)
			return;
		file.setContentType(fb.getContentType());
		file.setFileName(fb.getOriginName());
		file.setUuid(fb.getPath().substring(
				fb.getPath().lastIndexOf(File.separatorChar)));
		file.setPath(fb.getPath());
	}

	private void reset() {
		setArticle(new News());
		fileInfo = null;
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

	private FileInfo fileInfo;

	public FileInfo getFileInfo() {
		if (null == fileInfo)
			fileInfo = new FileInfo();
		return fileInfo;
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
		return Utilities.nvl(FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("newArticleForm:articleType"),FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("newVideoForm:articleType"),FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("newResourceForm:articleType"));
	}

}
