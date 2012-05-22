package com.kooobao.lm.bizflow;


public interface BusinessService {

	public void expireTransactions();

	public void releaseReserved();
	
	public void updateExpireRecords();
}
