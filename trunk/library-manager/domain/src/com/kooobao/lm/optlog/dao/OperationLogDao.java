package com.kooobao.lm.optlog.dao;

import java.util.List;

import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.book.entity.Category;

public interface OperationLogDao {

	public void logSearch(String keyword);

	public List<String> getHotSearchWords();

	public void logBorrow();

	public PageSearchResult<Book> getBorrowedBooks(Category category,
			int start, int stop);
}
