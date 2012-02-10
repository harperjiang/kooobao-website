package com.kooobao.fr.web.dummy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.kooobao.fr.domain.entity.Actor;
import com.kooobao.fr.domain.entity.Attachment;
import com.kooobao.fr.domain.entity.FileStorage;
import com.kooobao.fr.domain.entity.FinancialRecord;
import com.kooobao.fr.domain.entity.PaymentRecord;
import com.kooobao.fr.domain.entity.ReceiveRecord;
import com.kooobao.fr.domain.entity.RecordHistory;
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
		for (int i = 0; i < limit; i++)
			records.add(createDummyRecord());
		return records;
	}

	private Random random;

	public FinancialRecord createDummyRecord() {
		if (null == random)
			random = new Random();
		int result = random.nextInt();
		FinancialRecord record = null;
		if (result % 2 == 0)
			record = new PaymentRecord();
		else
			record = new ReceiveRecord();
		record.setAmount(new BigDecimal(result % 10000));
		record.setAdjustAmount(new BigDecimal(result % 10));
		record.setCreateBy("Dummy创建");
		record.setCreateDate(new Date());
		record.setDescription("Some Description");
		record.getWith().setAccount("建设银行1231232134313131");
		record.getWith().setCompany("Dummy Company");
		record.getWith().setName("AADDAD");
		record.setFollowup("Dummy跟进");

		Attachment attachment = new Attachment();
		attachment.setName("证据.xls");
		FileStorage fs = new FileStorage();
		fs.setPath("aswrwera");
		fs.setContentType("application/pdf");
		attachment.setFile(fs);
		record.setAttachment(attachment);

		RecordHistory hist1 = new RecordHistory();
		hist1.setOperateDate(new Date());
		hist1.setOperation("REJECT");
		hist1.setOperator("出纳");
		hist1.setDescription("没钱了");
		record.addHistory(hist1);

		RecordHistory hist2 = new RecordHistory();
		hist2.setOperateDate(new Date());
		hist2.setOperation("APPROVE");
		hist2.setOperator("经理");
		hist2.setDescription("批准");
		record.addHistory(hist2);

		return record;
	}

	public FinancialRecord create(FinancialRecord record) {
		return record;
	}

	public FinancialRecord approvePayment(FinancialRecord record, Actor actor,
			String reason) {
		((PaymentRecord) record).approve(actor.getId(), reason);
		return record;
	}

	public FinancialRecord rejectPayment(FinancialRecord record, Actor actor,
			String reason) {
		((PaymentRecord) record).reject(actor.getId(), reason);
		return record;
	}

	public FinancialRecord cancelPayment(FinancialRecord record, Actor actor,
			String reason) {
		((PaymentRecord) record).cancel(actor.getId(), reason);
		return record;
	}

	public FinancialRecord payPayment(FinancialRecord record, Actor actor,
			BigDecimal commission, String reason) {
		((PaymentRecord) record).confirm(commission, actor.getId(), reason);
		return record;
	}

	public FinancialRecord failPayment(FinancialRecord record, Actor actor,
			String reason) {
		((PaymentRecord) record).failToPay(actor.getId(), reason);
		return record;
	}

	public FinancialRecord confirmReceive(FinancialRecord record, Actor actor,
			String comment) {
		((ReceiveRecord) record).confirm(actor.getId(), comment);
		return record;
	}

	public FinancialRecord cancelReceive(FinancialRecord record, Actor actor,
			String comment) {
		((ReceiveRecord) record).cancel(actor.getId(), comment);
		return record;
	}

}
