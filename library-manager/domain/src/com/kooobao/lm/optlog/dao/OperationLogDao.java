package com.kooobao.lm.optlog.dao;

import java.util.Date;
import java.util.List;

import com.kooobao.common.domain.dao.Dao;
import com.kooobao.common.domain.entity.SimpleEntity;
import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.optlog.entity.BorrowCount;
import com.kooobao.lm.optlog.entity.SearchSummary;

public interface OperationLogDao extends Dao<SimpleEntity> {

	void logSearch(String keyword);

	List<String> getHotSearchWords();

	PageSearchResult<Book> getBorrowedBooks(int start, int stop);

	Date getSearchSummaryUpdateTime();

	Date getBorrowCountUpdateTime();

	List<SearchSummary> getSearchSummaries(Date lastUpdate);

	SearchSummary getSearchSummary(String keyword);

	List<BorrowCount> getBorrowCount(Date lastUpdate);

	BorrowCount getBorrowCount(Book book);

	void store(BorrowCount bc);
}
