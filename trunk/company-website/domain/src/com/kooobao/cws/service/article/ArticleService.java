package com.kooobao.cws.service.article;

import java.util.List;

import com.kooobao.cws.domain.article.Article;

public interface ArticleService {

	<T extends Article> List<T> getLatestArticle(int limit,
			Class<T> articleClass);

	Article getArticle(long articleId);

}
