package com.kooobao.lm.article;

import java.util.List;

import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.article.entity.News;

public interface NewsService {

	List<News> getLatestNews();

	News addNews(News newNews);

	News findNews(long oid);

	PageSearchResult<News> searchNews(String keyword, int from, int to);
}
