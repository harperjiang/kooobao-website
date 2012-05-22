package com.kooobao.lm.book.dao;

import java.util.List;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.common.domain.entity.SimpleEntity;
import com.kooobao.lm.book.entity.Book;

public class JpaRecommendDao extends AbstractJpaDao<SimpleEntity> implements
		RecommendDao {

	public List<Book> recommend(List<Book> selected, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Book> recommend(Book book, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

}
