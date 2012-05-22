package com.kooobao.lm.bizflow.dao;

import java.util.List;

import com.kooobao.common.domain.dao.Dao;
import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.bizflow.TransactionService.TransactionSearchBean;
import com.kooobao.lm.bizflow.entity.Transaction;
import com.kooobao.lm.bizflow.entity.TransactionState;
import com.kooobao.lm.profile.entity.Visitor;

public interface TransactionDao extends Dao<Transaction> {

	List<Transaction> findToExpire();

	List<Transaction> findByVisitor(Visitor visitor);

	int getTransactionCount(Visitor visitor, TransactionState... states);

	List<Transaction> getTransactions(Visitor visitor,
			TransactionState... states);

	PageSearchResult<Transaction> search(Visitor visitor,
			TransactionSearchBean searchBean);

}
