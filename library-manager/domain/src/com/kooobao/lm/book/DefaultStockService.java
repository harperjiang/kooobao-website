package com.kooobao.lm.book;

import com.kooobao.lm.book.dao.StockDao;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.book.entity.Stock;

public class DefaultStockService implements StockService {

	public void initStock(Book book) {
		Stock stock = getStockDao().findByBook(book);
		if (null == stock) {
			stock = new Stock();
			stock.setBook(book);
			stock.setStock(1);
			stock.setAvailable(1);
			getStockDao().store(stock);
		}
	}

	private StockDao stockDao;

	public StockDao getStockDao() {
		return stockDao;
	}

	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}

}
