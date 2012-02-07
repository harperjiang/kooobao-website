package com.kooobao.fr.service;

import java.util.List;

import com.kooobao.fr.domain.entity.FinancialRecord;

public interface FinancialRecordService {

	FinancialRecord save(FinancialRecord record);
	
	int getMyRecordCount();

	int getMyFollowupCount();

	List<FinancialRecord> getMyRecords(int limit);

	List<FinancialRecord> getMyFollowups(int limit);
}
