package com.kooobao.lm.book.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.TypedQuery;

import org.eclipse.persistence.jpa.JpaHelper;
import org.eclipse.persistence.queries.ObjectLevelReadQuery;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.common.domain.dao.Cursor;
import com.kooobao.common.domain.dao.cursor.JpaCursor;
import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.book.entity.BookRelation;
import com.kooobao.lm.book.entity.Category;
import com.kooobao.lm.optlog.entity.BorrowCount;

public class JpaBookDao extends AbstractJpaDao<Book> implements BookDao {

	@Override
	public Book store(Book entity) {
		if (0 == entity.getOid()) {
			BorrowCount bc = new BorrowCount();
			bc.setBook(entity);
			bc.setCount(0);
			bc.setUpdateTime(new Date());
			getEntityManager().persist(bc);
		}
		return super.store(entity);
	}

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
		getEntityManager().createNativeQuery("delete from lm_book_relation")
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

	public Cursor<Book> findBooksToRate(Date from) {
		String queryStr = "select brs.book from BookRateSummary brs where brs.mergeMark = true";

		TypedQuery<Book> bookQuery = getEntityManager().createQuery(queryStr,
				Book.class);
		JpaHelper.getReadAllQuery(bookQuery).setCacheUsage(ObjectLevelReadQuery.DoNotCheckCache);
		JpaHelper.getReadAllQuery(bookQuery).setShouldRefreshIdentityMapResult(true);
		return new JpaCursor<Book>(bookQuery);
	}

	public void updateBookRateSummary(Date from) {
		String query = "replace into lm_book_ratesum "
				+ "(obj_id,create_time,book_id,rate1,rate2,rate3,rate4,rate5,merge_mark) "
				+ "select new.book_id,?,"
				+ "new.book_id,"
				+ "new.rate1+ifnull(rs.rate1,0) rate1,"
				+ "new.rate2+ifnull(rs.rate2,0) rate2,"
				+ "new.rate3+ifnull(rs.rate3,0) rate3,"
				+ "new.rate4+ifnull(rs.rate4,0) rate4,"
				+ "new.rate5+ifnull(rs.rate5,0) rate5,"
				+ "1 "
				+ "from "
				+ "(select "
				+ "sum(case when 1=c.rating then 1 else 0 end) rate1,"
				+ "sum(case when 2=c.rating then 1 else 0 end) rate2,"
				+ "sum(case when 3=c.rating then 1 else 0 end) rate3,"
				+ "sum(case when 4=c.rating then 1 else 0 end) rate4,"
				+ "sum(case when 5=c.rating then 1 else 0 end) rate5,"
				+ "c.book_id "
				+ "from lm_book_comment c where c.create_time > ? group by c.book_id) new "
				+ "left outer join lm_book_ratesum rs on rs.book_id = new.book_id and rs.create_time < ?;";
		getEntityManager().createNativeQuery(query).setParameter(1, from)
				.setParameter(2, from).setParameter(3, from).executeUpdate();
	}

}
