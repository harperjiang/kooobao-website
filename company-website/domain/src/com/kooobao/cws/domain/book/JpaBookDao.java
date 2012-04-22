package com.kooobao.cws.domain.book;

import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import com.kooobao.common.domain.dao.AbstractJpaDao;

public class JpaBookDao extends AbstractJpaDao<Book> implements BookDao {

	@Override
	public List<Book> getLatestBooks(int limit) {
		TypedQuery<Book> bookQuery = getEntityManager().createQuery(
				"select b from Book order by createTime desc", Book.class)
				.setMaxResults(limit);
		return bookQuery.getResultList();
	}

	@Override
	public List<Book> getHotBooks(int limit) {
		TypedQuery<Book> bookQuery = getEntityManager().createQuery(
				"select b from Book order by level desc", Book.class)
				.setMaxResults(limit);
		return bookQuery.getResultList();
	}

	@Override
	public List<Book> getByCategory(Category category, int limit) {
		TypedQuery<Book> bookQuery = getEntityManager()
				.createQuery(
						"select b from Book where category = :category order by oid",
						Book.class).setParameter("category", category)
				.setMaxResults(limit);
		return bookQuery.getResultList();
	}

	@Override
	public List<Book> findBooks(String keyword) {
		Validate.isTrue(!StringUtils.isEmpty(keyword));
		TypedQuery<Book> bookQuery = getEntityManager().createQuery(
				"select b from Book where name like :keyword "
						+ "or brief like :keyword order by oid", Book.class)
				.setParameter("keyword", "%" + keyword + "%");
		return bookQuery.getResultList();
	}
}
