package com.kooobao.lm.optlog.dao;

import java.util.List;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.common.domain.entity.SimpleEntity;
import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.optlog.entity.SearchLog;

public class JpaOperationLogDao extends AbstractJpaDao<SimpleEntity> implements
		OperationLogDao {

	public void logSearch(String keyword) {
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

	public void logBorrow() {

	}

	public PageSearchResult<Book> getBorrowedBooks(int start, int stop) {
		int count = 0; // Temporarily don't support paged search due to
						// potentially to many records
		List<Book> bookList = getEntityManager()
				.createQuery(
						"select t.book from Transaction t order by t.createTime desc",
						Book.class).setFirstResult(start)
				.setMaxResults(stop - start).getResultList();
		return new PageSearchResult<Book>(count, bookList);
	}

}
