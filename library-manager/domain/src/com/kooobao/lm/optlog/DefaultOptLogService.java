package com.kooobao.lm.optlog;

import java.util.Date;
import java.util.List;

import com.kooobao.lm.bizflow.dao.TransactionDao;
import com.kooobao.lm.optlog.dao.OperationLogDao;
import com.kooobao.lm.optlog.entity.BorrowCount;
import com.kooobao.lm.optlog.entity.SearchSummary;

public class DefaultOptLogService implements OptLogService {

	public void sumSearchKeyword() {
		Date lastUpdate = getOperationLogDao().getSearchSummaryUpdateTime();
		Date updateTime = new Date();
		List<SearchSummary> summary = getOperationLogDao().getSearchSummaries(
				lastUpdate);
		for (SearchSummary s : summary) {
			SearchSummary existed = getOperationLogDao().getSearchSummary(
					s.getKeyword());
			if (null == existed) {
				s.setCreateTime(updateTime);
				getOperationLogDao().store(s);
			} else {
				existed.setSearchCount(existed.getSearchCount()
						+ s.getSearchCount());
				existed.setCreateTime(updateTime);
			}
		}
	}

	public void countBorrowBook() {
		Date lastUpdate = getOperationLogDao().getBorrowCountUpdateTime();
		Date updateTime = new Date();
		List<BorrowCount> summary = getOperationLogDao().getBorrowCount(
				lastUpdate);
		for (BorrowCount bc : summary) {
			BorrowCount existed = getOperationLogDao().getBorrowCount(
					bc.getBook());
			if (null == existed) {
				bc.setUpdateTime(updateTime);
				getOperationLogDao().store(bc);
			} else {
				existed.setCount(existed.getCount()+bc.getCount());
				existed.setUpdateTime(updateTime);
			}
		}
	}

	private TransactionDao transactionDao;

	private OperationLogDao operationLogDao;

	public TransactionDao getTransactionDao() {
		return transactionDao;
	}

	public void setTransactionDao(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}

	public OperationLogDao getOperationLogDao() {
		return operationLogDao;
	}

	public void setOperationLogDao(OperationLogDao operationLogDao) {
		this.operationLogDao = operationLogDao;
	}

}
