package com.kooobao.cws.dummy;

import java.util.List;

import com.kooobao.cws.domain.article.Article;
import com.kooobao.cws.domain.article.Section;
import com.kooobao.cws.service.article.ArticleService;

public class DummyArticleService implements ArticleService {

	@Override
	public <T extends Article> List<T> getLatestArticle(int limit,
			Class<T> articleClass) {
		return null;
	}

	@Override
	public Article getArticle(long articleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article saveArticle(Article article) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> findArticles(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Section> getSections(String type) {
		// TODO Auto-generated method stub
		return null;
	}

}
