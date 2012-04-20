package com.kooobao.cws.service.article;

import java.util.List;

import com.kooobao.cws.domain.article.Article;

public class DefaultArticleService implements ArticleService {

	@Override
	public <T extends Article> List<T> getLatestArticle(int limit,
			Class<T> articleClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article getArticle(long articleId) {
		// TODO Auto-generated method stub
		return null;
	}

}
