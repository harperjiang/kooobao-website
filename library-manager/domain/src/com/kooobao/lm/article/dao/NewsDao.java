package com.kooobao.lm.article.dao;

import java.util.List;

import com.kooobao.common.domain.dao.Dao;
import com.kooobao.lm.article.entity.News;

public interface NewsDao extends Dao<News> {

	List<News> findLatestNews(int limit);

}
