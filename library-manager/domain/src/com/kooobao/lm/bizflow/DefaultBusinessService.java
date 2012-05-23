package com.kooobao.lm.bizflow;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.kooobao.common.domain.dao.Cursor;
import com.kooobao.lm.bizflow.dao.ExpireRecordDao;
import com.kooobao.lm.bizflow.dao.TransactionDao;
import com.kooobao.lm.bizflow.entity.ExpireRecord;
import com.kooobao.lm.bizflow.entity.Transaction;
import com.kooobao.lm.bizflow.entity.TransactionState;
import com.kooobao.lm.profile.dao.VisitorDao;
import com.kooobao.lm.profile.entity.Visitor;
import com.kooobao.lm.profile.entity.VisitorStatus;
import com.kooobao.lm.rule.dao.RuleDao;
import com.kooobao.lm.rule.entity.PenaltyRule;

public class DefaultBusinessService implements BusinessService {

	public void expireTransactions() {
		List<Transaction> toExpire = getTransactionDao().findToExpire(new Date());
		for (Transaction expire : toExpire) {
			expire.expire();
			ExpireRecord er = new ExpireRecord();
			er.setDueTime(new Date());
			er.setTransaction(expire);
			getExpireRecordDao().store(er);
		}
	}

	public void updateExpireRecords() {
		Cursor<ExpireRecord> cursor = getExpireRecordDao()
				.findActivateRecords();
		while (cursor.hasNext()) {
			ExpireRecord record = cursor.next();
			if (TransactionState.RETURN_EXPIRED == record.getTransaction()
					.getState()) {
				// Update Penalty
				BigDecimal oldPenalty = record.getPenalty();
				PenaltyRule rule = getRuleDao().getPenaltyRule();
				BigDecimal penalty = rule.getPenalty(record.getTransaction()
						.getVisitor(), record.getDueTime(), new Date());
				// Update User Remaining
				Visitor visitor = record.getTransaction().getVisitor();
				visitor.setDeposit(visitor.getDeposit().subtract(
						penalty.subtract(oldPenalty)));
				// Update User Status
				if (visitor.getDeposit().compareTo(BigDecimal.ZERO) < 0)
					visitor.setStatus(VisitorStatus.LOCKED.name());

				// getVisitorDao().store(visitor);
			}
		}
	}

	public void clearInactivateVisitors() {
		// TODO Auto-generated method stub

	}

	private TransactionDao transactionDao;

	public TransactionDao getTransactionDao() {
		return transactionDao;
	}

	public void setTransactionDao(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}

	private ExpireRecordDao expireRecordDao;

	public ExpireRecordDao getExpireRecordDao() {
		return expireRecordDao;
	}

	public void setExpireRecordDao(ExpireRecordDao expireRecordDao) {
		this.expireRecordDao = expireRecordDao;
	}

	private VisitorDao visitorDao;

	public VisitorDao getVisitorDao() {
		return visitorDao;
	}

	public void setVisitorDao(VisitorDao visitorDao) {
		this.visitorDao = visitorDao;
	}

	private RuleDao ruleDao;

	public RuleDao getRuleDao() {
		return ruleDao;
	}

	public void setRuleDao(RuleDao ruleDao) {
		this.ruleDao = ruleDao;
	}

}
