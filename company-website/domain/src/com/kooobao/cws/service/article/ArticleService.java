package com.kooobao.cws.service.article;

import java.util.List;

import com.kooobao.cws.domain.article.Article;
import com.kooobao.cws.domain.article.Section;

public interface ArticleService {

	<T extends Article> List<T> getLatestArticle(int limit,
			Class<T> articleClass);

	Article getArticle(long articleId);

	Article saveArticle(Article article);

	List<Article> findArticles(String keyword);
	
	List<Section> getSections(String type);
}
