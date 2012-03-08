package com.kooobao.fr.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.common.web.fileupload.FileBean;
import com.kooobao.fr.domain.entity.Actor;
import com.kooobao.fr.domain.entity.FinancialRecord;

public interface FinancialRecordService {

	int getMyRecordCount(String actor);

	int getMyFollowupCount(String actor);

	List<FinancialRecord> getMyRecords(String id, int limit);

	List<FinancialRecord> getMyFollowups(String id, int limit);

	FinancialRecord create(FinancialRecord record);

	FinancialRecord approvePayment(FinancialRecord record, Actor actor,
			String reason);

	FinancialRecord rejectPayment(FinancialRecord record, Actor actor,
			String reason);

	FinancialRecord cancelPayment(FinancialRecord record, Actor actor,
			String reason);

	FinancialRecord payPayment(FinancialRecord record, FileBean attachment,
			Actor actor, BigDecimal commission, BigDecimal creditPay,
			String reason);

	FinancialRecord failPayment(FinancialRecord record, Actor actor,
			String reason);

	FinancialRecord balancePayment(FinancialRecord record, Actor actor,
			String reason);

	FinancialRecord confirmReceive(FinancialRecord record, Actor actor,
			String comment);

	FinancialRecord cancelReceive(FinancialRecord record, Actor actor,
			String comment);

	PageSearchResult<FinancialRecord> search(Date fromDate, Date toDate,
			String[] status, int recordStart, int recordStop);

	FinancialRecord resubmitPayment(FinancialRecord record, Actor actor,
			String comment);
}
