package com.kooobao.fr.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.common.web.fileupload.FileBean;
import com.kooobao.fr.domain.dao.ActorDao;
import com.kooobao.fr.domain.dao.FinancialRecordDao;
import com.kooobao.fr.domain.entity.Actor;
import com.kooobao.fr.domain.entity.Attachment;
import com.kooobao.fr.domain.entity.Customer;
import com.kooobao.fr.domain.entity.FileStorage;
import com.kooobao.fr.domain.entity.FinancialRecord;
import com.kooobao.fr.domain.entity.PaymentRecord;
import com.kooobao.fr.domain.entity.ReceiveRecord;
import com.kooobao.fr.domain.entity.Role;

public class DefaultFinancialRecordService implements FinancialRecordService {

	public int getMyRecordCount(String actor) {
		return getFinancialRecordDao().getCountByCreateBy(actor);
	}

	private static final String[] MANAGER_STATUS = new String[] { "PAYMENT_SUBMIT" };

	private static final String[] TELLER_STATUS = new String[] {
			"PAYMENT_APPROVED", "RECEIVE_SUBMIT" };

	public int getMyFollowupCount(String actorId) {
		Actor actor = getActorDao().getActor(actorId);
		List<String> status = new ArrayList<String>();
		if (actor.getRoles().contains(Role.MANAGER.name())) {
			for (String sta : MANAGER_STATUS)
				status.add(sta);
		}
		if (actor.getRoles().contains(Role.TELLER.name())) {
			for (String sta : TELLER_STATUS)
				status.add(sta);
		}
		String[] statusArray = new String[status.size()];
		status.toArray(statusArray);
		return getFinancialRecordDao().getCountByStatus(statusArray);
	}

	public List<FinancialRecord> getMyRecords(String id, int limit) {
		return getFinancialRecordDao().getRecordsByCreatedBy(id, limit);
	}

	public List<FinancialRecord> getMyFollowups(String id, int limit) {
		Actor actor = getActorDao().getActor(id);
		List<String> status = new ArrayList<String>();
		if (actor.getRoles().contains(Role.MANAGER.name())) {
			for (String sta : MANAGER_STATUS)
				status.add(sta);
		}
		if (actor.getRoles().contains(Role.TELLER.name())) {
			for (String sta : TELLER_STATUS)
				status.add(sta);
		}
		String[] statusArray = new String[status.size()];
		status.toArray(statusArray);
		return getFinancialRecordDao().getRecordsByStatus(statusArray, limit);
	}

	public FinancialRecord create(FinancialRecord record) {
		Customer customer = new Customer();
		customer.setCreateDate(null);
		customer.setName(record.getWith().getName());
		customer.setCompany(record.getWith().getCompany());
		customer.setAccount(record.getWith().getAccount());
		DefaultCustomerService.addCustomerQueue.add(customer);
		return getFinancialRecordDao().store(record);
	}

	public FinancialRecord approvePayment(FinancialRecord record, Actor actor,
			String reason) {
		((PaymentRecord) record).approve(actor.getId(), reason);
		return getFinancialRecordDao().store(record);
	}

	public FinancialRecord rejectPayment(FinancialRecord record, Actor actor,
			String reason) {
		((PaymentRecord) record).reject(actor.getId(), reason);
		return getFinancialRecordDao().store(record);
	}

	public FinancialRecord cancelPayment(FinancialRecord record, Actor actor,
			String reason) {
		((PaymentRecord) record).cancel(actor.getId(), reason);
		return getFinancialRecordDao().store(record);
	}

	public FinancialRecord payPayment(FinancialRecord record,
			FileBean attachment, Actor actor, BigDecimal commission,
			BigDecimal creditPay, String reason) {
		((PaymentRecord) record).confirm(commission, actor.getId(), reason);
		record.getHistories().get(record.getHistories().size() - 1)
				.setAttachment(createAttachment(attachment));
		if (creditPay != null && !creditPay.equals(BigDecimal.ZERO)) {
			// Credit Pay
			ReceiveRecord creditReceive = new ReceiveRecord();
			creditReceive.getWith().fromCustomer(CREDIT_CUSTOMER);
			creditReceive.setAmount(creditPay);
			creditReceive.setDescription("信用卡付款");
			creditReceive.confirm(actor.getId(), "自动确认");
			getFinancialRecordDao().store(creditReceive);
		}
		return getFinancialRecordDao().store(record);
	}

	static Customer CREDIT_CUSTOMER = new Customer();

	static {
		CREDIT_CUSTOMER.setName("信用卡账户");
	}

	public FinancialRecord resubmitPayment(FinancialRecord record, Actor actor,
			String comment) {
		((PaymentRecord) record).resubmit(actor.getId());
		return getFinancialRecordDao().store(record);
	}

	public FinancialRecord failPayment(FinancialRecord record, Actor actor,
			String reason) {
		((PaymentRecord) record).failToPay(actor.getId(), reason);
		return getFinancialRecordDao().store(record);
	}

	public FinancialRecord confirmReceive(FinancialRecord record, Actor actor,
			String comment) {
		((ReceiveRecord) record).confirm(actor.getId(), comment);
		return getFinancialRecordDao().store(record);
	}

	public FinancialRecord cancelReceive(FinancialRecord record, Actor actor,
			String comment) {
		((ReceiveRecord) record).cancel(actor.getId(), comment);
		return getFinancialRecordDao().store(record);
	}

	public PageSearchResult<FinancialRecord> search(Date fromDate, Date toDate,
			String[] status, int recordStart, int recordStop) {
		int count = getFinancialRecordDao().searchCount(fromDate, toDate,
				status);
		List<FinancialRecord> resultList = getFinancialRecordDao().search(
				fromDate, toDate, status, recordStart, recordStop);
		return new PageSearchResult<FinancialRecord>(count, resultList);
	}

	private ActorDao actorDao;

	private FinancialRecordDao financialRecordDao;

	public ActorDao getActorDao() {
		return actorDao;
	}

	public void setActorDao(ActorDao actorDao) {
		this.actorDao = actorDao;
	}

	public FinancialRecordDao getFinancialRecordDao() {
		return financialRecordDao;
	}

	public void setFinancialRecordDao(FinancialRecordDao financialRecordDao) {
		this.financialRecordDao = financialRecordDao;
	}

	public static final Attachment createAttachment(FileBean fb) {
		if (null == fb)
			return null;

		// For Browsers that contains path info
		if (fb.getOriginName().contains("/")) {
			fb.setOriginName(fb.getOriginName().substring(
					fb.getOriginName().lastIndexOf('/') + 1));
		}
		if (fb.getOriginName().contains("\\")) {
			fb.setOriginName(fb.getOriginName().substring(
					fb.getOriginName().lastIndexOf('\\') + 1));
		}

		Attachment attachment = new Attachment();
		attachment.setCreateDate(new Date());
		attachment.setName(fb.getOriginName());
		attachment.setSize(fb.getSize());
		FileStorage fileStorage = new FileStorage();
		fileStorage.setPath(fb.getPath());
		fileStorage.setContentType(fb.getContentType());
		attachment.setFile(fileStorage);
		return attachment;
	}
}
