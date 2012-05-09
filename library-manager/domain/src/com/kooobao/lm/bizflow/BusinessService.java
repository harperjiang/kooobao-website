package com.kooobao.lm.bizflow;

import java.util.List;

import com.kooobao.lm.profile.Library;
import com.kooobao.lm.profile.Visitor;

public interface BusinessService {

	public Transaction borrow(Library library, Visitor visitor,
			List<BookPack> books);

	public void expireTransactions();

	public void releaseReserved();
}
