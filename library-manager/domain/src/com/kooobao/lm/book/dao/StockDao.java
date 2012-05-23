package com.kooobao.lm.book.dao;

import com.kooobao.common.domain.dao.Dao;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.book.entity.Stock;

public interface StockDao extends Dao<Stock> {

	Stock findByBook(Book book);

}
