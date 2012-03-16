package com.kooobao.fr.domain.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.kooobao.common.domain.dao.Dao;
import com.kooobao.fr.domain.entity.FinancialRecord;

public interface FinancialRecordDao extends Dao<FinancialRecord> {

	int getCountByCreateBy(String actor);

	int getCountByStatus(String[] status);

	List<FinancialRecord> getRecordsByCreatedBy(String id, int limit);

	List<FinancialRecord> getRecordsByStatus(String[] strings, int limit);

	int searchCount(Date fromDate, Date toDate, String[] status);

	List<FinancialRecord> search(Date fromDate, Date toDate, String[] status,
			int recordStart, int recordStop);

	BigDecimal getSumAmount(Date from,Date to, String status);
}
