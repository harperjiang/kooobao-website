package com.kooobao.lm.book.dao;

import java.util.List;

import javax.persistence.Query;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.common.domain.entity.SimpleEntity;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.profile.entity.Visitor;

public class JpaRecommendDao extends AbstractJpaDao<SimpleEntity> implements
		RecommendDao {

	public List<Book> recommend(Visitor visitor, List<Book> selected, int limit) {
		// TODO Ignore Visitor info
		String queryStr = "select distinct book.* from lm_book book "
				+ "join "
				+ "(select book_to book_id, score from lm_book_relation where book_from in "
				+ genParamStr(selected.size()) + " and book_to not in "
				+ genParamStr(selected.size())
				+ " order by score desc limit ?) "
				+ "x on x.book_id = book.obj_id";
		Query query = getEntityManager()
				.createNativeQuery(queryStr, Book.class);
		for (int i = 0; i < selected.size(); i++) {
			query.setParameter(i + 1, selected.get(i).getOid());
		}
		for (int i = 0; i < selected.size(); i++) {
			query.setParameter(i + selected.size() + 1, selected.get(i)
					.getOid());
		}
		query.setParameter(2 * selected.size() + 1, limit);
		return query.getResultList();
	}

	public List<Book> recommend(Visitor visitor, Book book, int limit) {
		// TODO Ignore Visitor info
		String query = "select book.* from lm_book book "
				+ "join "
				+ "(select book_to book_id, score from lm_book_relation where book_from = ? order by score desc limit ?) "
				+ "x on x.book_id = book.obj_id";
		return getEntityManager().createNativeQuery(query, Book.class)
				.setParameter(1, book.getOid()).setParameter(2, limit)
				.getResultList();
	}

}
