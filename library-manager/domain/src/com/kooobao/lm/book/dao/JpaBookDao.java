package com.kooobao.lm.book.dao;

import java.util.List;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.book.entity.Category;

public class JpaBookDao extends AbstractJpaDao<Book> implements BookDao {

	public PageSearchResult<Book> findByCategory(Category selectedCategory,
			int start, int stop) {
		int count = getEntityManager()
				.createQuery(
						"select count(b) from Book b where b.category = :category",
						Integer.class)
				.setParameter("category", selectedCategory).getSingleResult();
		List<Book> bookList = getEntityManager()
				.createQuery(
						"select b from Book b where b.category = :category",
						Book.class).setParameter("category", selectedCategory)
				.setFirstResult(start).setMaxResults(stop).getResultList();
		return new PageSearchResult<Book>(count, bookList);
	}

	public PageSearchResult<Book> findByKeyword(String keyword, int start,
			int stop) {
		String kw = "%" + keyword + "%";
		int count = getEntityManager()
				.createQuery(
						"select count(b) from Book b where b.name like :keyword",
						Integer.class).setParameter("keyword", kw)
				.getSingleResult();
		List<Book> bookList = getEntityManager()
				.createQuery("select b from Book b where b.name like :keyword",
						Book.class).setParameter("keyword", kw)
				.setFirstResult(start).setMaxResults(stop).getResultList();
		return new PageSearchResult<Book>(count, bookList);
	}

	public PageSearchResult<Book> getPopularBooks(int start, int stop) {
		int count = getEntityManager().createQuery(
				"select count(b) from BorrowCount b", Integer.class)
				.getSingleResult();
		List<Book> bookList = getEntityManager()
				.createQuery(
						"select b from BorrowCount bc join bc.book b order by bc.count desc",
						Book.class).setFirstResult(start).setMaxResults(stop)
				.getResultList();
		return new PageSearchResult<Book>(count, bookList);
	}

	public PageSearchResult<Book> getLatestBooks(int start, int stop) {
		int count = getEntityManager().createQuery(
				"select count(b) from Book b", Integer.class).getSingleResult();
		List<Book> bookList = getEntityManager()
				.createQuery("select b from Book b order by b.createTime desc",
						Book.class).setFirstResult(start).setMaxResults(stop)
				.getResultList();
		return new PageSearchResult<Book>(count, bookList);
	}

	public PageSearchResult<Book> getEditorRecommendBooks(int start, int stop) {
		// TODO Not implemented
		return getPopularBooks(start, stop);
	}

}
