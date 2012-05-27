package com.kooobao.lm.bizflow;


public interface BusinessService {
	
	public void clearInactivateVisitors();

	public void expireTransactions();
	
	public void updateExpireRecords();
	
	/**
	 * Auto Reserve stock for requested transactions
	 */
	public void reserveStocks();
	
	/**
	 * Auto assume user had received the package
	 */
	public void assumeReceived();
	
	public void buildBookAssociations();
}
