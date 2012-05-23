package com.kooobao.lm.bizflow;


public interface BusinessService {
	
	public void clearInactivateVisitors();

	public void expireTransactions();
	
	public void updateExpireRecords();
}
