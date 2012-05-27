package com.kooobao.lm.book.dao;

import java.math.BigDecimal;
import java.util.List;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.book.entity.BookRelation;
import com.kooobao.lm.book.entity.Category;

public class JpaBookDao extends AbstractJpaDao<Book> implements BookDao {

	public PageSearchResult<Book> findByCategory(Category selectedCategory,
			int start, int stop) {
		int count = getEntityManager()
				.createQuery(
						"select count(b) from Book b where b.category = :category",
						Long.class).setParameter("category", selectedCategory)
				.getSingleResult().intValue();
		List<Book> bookList = getEntityManager()
				.createQuery(
						"select b from Book b where b.category = :category",
						Book.class).setParameter("category", selectedCategory)
				.setFirstResult(start).setMaxResults(stop - start)
				.getResultList();
		return new PageSearchResult<Book>(count, bookList);
	}

	public PageSearchResult<Book> findByKeyword(String keyword, int start,
			int stop) {
		String kw = "%" + keyword + "%";
		int count = getEntityManager()
				.createQuery(
						"select count(b) from Book b where b.name like :keyword",
						Long.class).setParameter("keyword", kw)
				.getSingleResult().intValue();
		List<Book> bookList = getEntityManager()
				.createQuery("select b from Book b where b.name like :keyword",
						Book.class).setParameter("keyword", kw)
				.setFirstResult(start).setMaxResults(stop - start)
				.getResultList();
		return new PageSearchResult<Book>(count, bookList);
	}

	public PageSearchResult<Book> getPopularBooks(int start, int stop) {
		int count = getEntityManager()
				.createQuery("select count(b) from BorrowCount b", Long.class)
				.getSingleResult().intValue();
		List<Book> bookList = getEntityManager()
				.createQuery(
						"select b from BorrowCount bc join bc.book b order by bc.count desc",
						Book.class).setFirstResult(start)
				.setMaxResults(stop - start).getResultList();
		return new PageSearchResult<Book>(count, bookList);
	}

	public PageSearchResult<Book> getLatestBooks(int start, int stop) {
		int count = getEntityManager()
				.createQuery("select count(b) from Book b", Long.class)
				.getSingleResult().intValue();
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

	public void clearAssociations() {
		getEntityManager().createNativeQuery("truncate table lm_book_relation")
				.executeUpdate();

	}

	public void addAssociation(Book iBook, Book jBook, int score) {
		BookRelation br = new BookRelation();
		br.setFrom(iBook);
		br.setTo(jBook);
		br.setScore(new BigDecimal(score));
		BookRelation br1 = new BookRelation();
		br1.setFrom(jBook);
		br1.setTo(iBook);
		br1.setScore(new BigDecimal(score));
		getEntityManager().persist(br);
		getEntityManager().persist(br1);
	}

}
