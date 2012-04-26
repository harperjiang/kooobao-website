package com.kooobao.cws.domain.article;

import java.util.List;

import com.kooobao.common.domain.dao.Dao;

public interface ArticleDao extends Dao<Article> {

	List<Article> findByKeyword(String keyword);

	<T extends Article> List<T> getLatest(Class<T> articleClass, int limit);
}
