package com.kooobao.fr.web.dummy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.kooobao.fr.domain.entity.FinancialRecord;
import com.kooobao.fr.domain.entity.PaymentRecord;
import com.kooobao.fr.domain.entity.ReceiveRecord;
import com.kooobao.fr.service.FinancialRecordService;

public class DummyFinancialRecordService implements FinancialRecordService {

	public int getMyRecordCount() {
		return 20;
	}

	public int getMyFollowupCount() {
		return 10;
	}

	public List<FinancialRecord> getMyRecords(int limit) {
		return createDummyRecords(limit);
	}

	public List<FinancialRecord> getMyFollowups(int limit) {
		return createDummyRecords(limit);
	}
	
	protected List<FinancialRecord> createDummyRecords(int limit) {
		List<FinancialRecord> records = new ArrayList<FinancialRecord>();
		for(int i = 0 ; i < limit ;i++)
			records.add(createDummyRecord());
		return records;
	}
	
	private Random random;
	
	protected FinancialRecord createDummyRecord() {
		if(null == random)
			random = new Random();
		int result = random.nextInt();
		FinancialRecord record = null;
		if(result%2==0)
			record = new PaymentRecord();
		else record = new ReceiveRecord();
		record.setAmount(new BigDecimal(result%10000));
		record.setAdjustAmount(new BigDecimal(result%10));
		record.setCreateBy("Dummy创建");
		record.setCreateDate(new Date());
		record.setDescription("Some Description");
		record.getWith().setAccount("建设银行1231232134313131");
		record.getWith().setCompany("Dummy Company");
		record.getWith().setName("AADDAD");
		record.setFollowup("Dummy跟进");
		return record;
	}

	public FinancialRecord save(FinancialRecord record) {
		return record;
	}

}
