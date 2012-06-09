package com.kooobao.lm.optlog.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.common.domain.entity.SimpleEntity;
import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.optlog.entity.BorrowCount;
import com.kooobao.lm.optlog.entity.SearchLog;
import com.kooobao.lm.optlog.entity.SearchSummary;

public class JpaOperationLogDao extends AbstractJpaDao<SimpleEntity> implements
		OperationLogDao {

	public void logSearch(String keyword) {
		Validate.isTrue(!StringUtils.isEmpty(keyword));
		SearchLog log = new SearchLog();
		log.setKeyword(keyword);
		getEntityManager().persist(log);
	}

	public List<String> getHotSearchWords() {
		return getEntityManager()
				.createQuery(
						"select ss.keyword from SearchSummary ss order by ss.searchCount desc",
						String.class).setFirstResult(0).setMaxResults(5)
				.getResultList();
	}

	public PageSearchResult<Book> getBorrowedBooks(int start, int stop) {
		int count = 0; // Temporarily don't support paged search due to
						// potentially to many records
		List<Book> bookList = getEntityManager()
				.createQuery(
						"select distinct t.book from Transaction t order by t.createTime desc",
						Book.class).setFirstResult(start)
				.setMaxResults(stop - start).getResultList();
		return new PageSearchResult<Book>(count, bookList);
	}

	public Date getSearchSummaryUpdateTime() {
		return getEntityManager().createQuery(
				"select max(ss.createTime) from SearchSummary ss", Date.class)
				.getSingleResult();
	}

	public Date getBorrowCountUpdateTime() {
		return getEntityManager().createQuery(
				"select max(ss.updateTime) from BorrowCount ss", Date.class)
				.getSingleResult();
	}

	public List<SearchSummary> getSearchSummaries(Date lastUpdate) {
		String query = "select new com.kooobao.lm.optlog.entity.SearchSummary(sl.keyword, count(sl)) from SearchLog sl where sl.createTime > :lastUpdate group by sl.keyword";
		return getEntityManager().createQuery(query, SearchSummary.class)
				.setParameter("lastUpdate", lastUpdate).getResultList();
	}

	public SearchSummary getSearchSummary(String keyword) {
		Validate.isTrue(!StringUtils.isEmpty(keyword));
		try {
			return getEntityManager()
					.createQuery(
							"select ss from SearchSummary ss where ss.keyword = :keyword",
							SearchSummary.class)
					.setParameter("keyword", keyword).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<BorrowCount> getBorrowCount(Date lastUpdate) {
		String query = "select new com.kooobao.lm.optlog.entity.BorrowCount(t.book, count(t)) from Transaction t where t.createTime > :lastUpdate group by t.book";
		return getEntityManager().createQuery(query, BorrowCount.class)
				.setParameter("lastUpdate", lastUpdate).getResultList();

	}

	public BorrowCount getBorrowCount(Book book) {
		try {
			return getEntityManager()
					.createQuery(
							"select bc from BorrowCount bc where bc.book = :book",
							BorrowCount.class).setParameter("book", book)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void store(BorrowCount bc) {
		getEntityManager().persist(bc);
	}

}
