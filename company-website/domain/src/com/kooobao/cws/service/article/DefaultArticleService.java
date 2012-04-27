package com.kooobao.cws.service.article;

import java.util.List;

import com.kooobao.cws.domain.article.Article;
import com.kooobao.cws.domain.article.ArticleDao;
import com.kooobao.cws.domain.article.Section;
import com.kooobao.cws.domain.article.SectionDao;

public class DefaultArticleService implements ArticleService {

	@Override
	public <T extends Article> List<T> getLatestArticle(int limit,
			Class<T> articleClass) {
		return getArticleDao().getLatest(articleClass, limit);
	}

	@Override
	public Article getArticle(long articleId) {
		return getArticleDao().find(articleId);
	}

	@Override
	public Article saveArticle(Article article) {
		return getArticleDao().store(article);
	}

	@Override
	public List<Article> findArticles(String keyword) {
		return getArticleDao().findByKeyword(keyword);
	}

	private ArticleDao articleDao;

	public ArticleDao getArticleDao() {
		return articleDao;
	}

	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	private SectionDao sectionDao;

	public SectionDao getSectionDao() {
		return sectionDao;
	}

	public void setSectionDao(SectionDao sectionDao) {
		this.sectionDao = sectionDao;
	}

	@Override
	public List<Section> getSections(String type) {
		return getSectionDao().getSections(type);
	}

}
