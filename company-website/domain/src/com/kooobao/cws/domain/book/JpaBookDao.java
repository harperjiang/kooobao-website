package com.kooobao.cws.domain.book;

import java.util.List;

import javax.persistence.TypedQuery;

import com.kooobao.common.domain.dao.AbstractJpaDao;

public class JpaBookDao extends AbstractJpaDao<Book> implements BookDao {

	@Override
	public List<Book> getLatestBooks() {
		TypedQuery<Book> bookQuery = getEntityManager()
				.createQuery("select b from Book order by createTime",
						Book.class).setFirstResult(1).setMaxResults(2);
		return bookQuery.getResultList();
	}

	@Override
	public List<Book> getHotBooks() {
		return null;
	}

}
