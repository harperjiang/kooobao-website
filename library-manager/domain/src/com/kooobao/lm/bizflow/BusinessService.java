package com.kooobao.lm.bizflow;

import java.util.List;

import com.kooobao.lm.profile.Library;
import com.kooobao.lm.profile.Visitor;

public interface BusinessService {

	public Transaction borrow(Library library, Visitor visitor,
			List<BookPack> books);

	public Transaction getTransaction(long transId);

	public List<Transaction> findTransactions(Library library, Visitor visitor);

	public List<Transaction> findTransaction(Library library,
			Transaction.State[] status);

	public Transaction saveTransaction(Transaction transaction);
	
	public void expireTransactions();
	
	public void releaseReserved();
}
