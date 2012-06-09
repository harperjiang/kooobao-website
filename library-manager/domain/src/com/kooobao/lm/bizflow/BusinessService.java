package com.kooobao.lm.bizflow;

import com.kooobao.lm.bizflow.entity.Transaction;


public interface BusinessService {
	
	public void clearInactivateVisitors();

	public void expireTransactions();
	
	public void updateExpireRecords();
	
	/**
	 * Auto Reserve stock for requested transactions
	 */
	public void reserveStocks();
	
	public void reserveStock(Transaction tran);
	
	/**
	 * Auto assume user had received the package
	 */
	public void assumeReceived();
	
	public void assumeReceived(Transaction tran);
	
	public void buildBookAssociations();

	public void expireTransaction(Transaction t);
}
