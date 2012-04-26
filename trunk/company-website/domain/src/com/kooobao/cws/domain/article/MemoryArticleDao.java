package com.kooobao.cws.domain.article;

import java.util.List;

import com.kooobao.common.domain.dao.AbstractMemoryDao;

public class MemoryArticleDao extends AbstractMemoryDao<Article> implements
		ArticleDao {

	@Override
	public List<Article> findByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Article> List<T> getLatest(Class<T> articleClass,
			int limit) {
		// TODO Auto-generated method stub
		return null;
	}

}
