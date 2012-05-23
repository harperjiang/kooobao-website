package com.kooobao.lm.book.dao;

import javax.persistence.NoResultException;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.book.entity.Stock;

public class JpaStockDao extends AbstractJpaDao<Stock> implements StockDao {

	public Stock findByBook(Book book) {
		try {
			return getEntityManager()
					.createQuery("select s from Stock s where s.book = :book",
							Stock.class).setParameter("book", book)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
