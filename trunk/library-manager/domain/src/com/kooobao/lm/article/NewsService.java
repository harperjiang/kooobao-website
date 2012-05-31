package com.kooobao.lm.article;

import java.util.List;

import com.kooobao.lm.article.entity.News;

public interface NewsService {

	public List<News> getLatestNews();
	
	public News addNews(News newNews);

	public News findNews(long oid);
}
