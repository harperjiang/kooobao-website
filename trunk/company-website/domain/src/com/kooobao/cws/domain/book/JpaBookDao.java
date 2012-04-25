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
				"select b from Book b order by b.createTime desc", Book.class);
		if (limit != UNLIMITED)
			bookQuery.setMaxResults(limit);
		return bookQuery.getResultList();
	}

	@Override
	public List<Book> getHotBooks(int limit) {
		TypedQuery<Book> bookQuery = getEntityManager().createQuery(
				"select b from Book b order by b.level desc", Book.class);
		if (limit != UNLIMITED)
			bookQuery.setMaxResults(limit);
		return bookQuery.getResultList();
	}

	@Override
	public List<Book> getByCategory(Category category, int limit) {
		TypedQuery<Book> bookQuery = getEntityManager()
				.createQuery(
						"select b from Book b where b.category = :category order by b.oid",
						Book.class).setParameter("category", category);
		if (limit != UNLIMITED)
			bookQuery.setMaxResults(limit);
		return bookQuery.getResultList();
	}

	@Override
	public List<Book> findBooks(String keyword) {
		Validate.isTrue(!StringUtils.isEmpty(keyword));
		TypedQuery<Book> bookQuery = getEntityManager()
				.createQuery(
						"select b from Book b where b.name like :keyword "
								+ "or b.brief like :keyword order by b.oid",
						Book.class)
				.setParameter("keyword", "%" + keyword + "%");
		return bookQuery.getResultList();
	}
}
