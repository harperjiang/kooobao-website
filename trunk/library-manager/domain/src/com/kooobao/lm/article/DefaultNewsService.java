package com.kooobao.lm.article;

import java.util.List;

import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.article.dao.NewsDao;
import com.kooobao.lm.article.entity.News;

public class DefaultNewsService implements NewsService {

	private NewsDao newsDao;

	public NewsDao getNewsDao() {
		return newsDao;
	}

	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}

	static int LIMIT = 20;

	public List<News> getLatestNews() {
		return getNewsDao().findLatestNews(LIMIT);
	}

	public News addNews(News newNews) {
		return getNewsDao().store(newNews);
	}

	public News findNews(long oid) {
		return getNewsDao().find(oid);
	}

	public PageSearchResult<News> searchNews(String keyword, int from, int to) {
		return getNewsDao().search(keyword, from, to);
	}

}
