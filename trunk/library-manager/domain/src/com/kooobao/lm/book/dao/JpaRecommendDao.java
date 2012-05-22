package com.kooobao.lm.book.dao;

import java.util.List;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.common.domain.entity.SimpleEntity;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.profile.entity.Visitor;

public class JpaRecommendDao extends AbstractJpaDao<SimpleEntity> implements
		RecommendDao {

	public List<Book> recommend(Visitor visitor, List<Book> selected, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Book> recommend(Visitor visitor, Book book, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

}
