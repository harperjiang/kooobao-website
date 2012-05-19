package com.kooobao.lm.bizflow;

import java.util.List;

import com.kooobao.lm.bizflow.entity.BookPack;
import com.kooobao.lm.bizflow.entity.Transaction;
import com.kooobao.lm.profile.entity.Visitor;

public interface BusinessService {

	public Transaction borrow(Visitor visitor, List<BookPack> books);

	public void expireTransactions();

	public void releaseReserved();
}
