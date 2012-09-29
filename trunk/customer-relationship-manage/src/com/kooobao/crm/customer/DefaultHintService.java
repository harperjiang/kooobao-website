package com.kooobao.crm.customer;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.Validate;

import com.kooobao.common.domain.dao.Cursor;
import com.kooobao.crm.common.Context;
import com.kooobao.crm.customer.dao.CustomerDao;
import com.kooobao.crm.customer.dao.HintDao;
import com.kooobao.crm.customer.entity.Customer;
import com.kooobao.crm.customer.entity.CustomerNature;
import com.kooobao.crm.customer.entity.CustomerStatus;
import com.kooobao.crm.customer.entity.Hint;
import com.kooobao.crm.customer.entity.HintFollowup;
import com.kooobao.crm.customer.entity.HintStatus;
import com.kooobao.crm.order.entity.Order;
import com.kooobao.crm.setting.dao.SettingDao;
import com.kooobao.crm.setting.entity.CustomerSetting;

public class DefaultHintService implements HintService {

	public void freeHints() {
		Cursor<Hint> hints = getHintDao().getOvertimeHints(
				getSettingDao().getCustomerSetting().getHintRetainTime());
		while (hints.hasNext()) {
			Hint hint = hints.next();
			hint.setOwnBy(null);
		}
	}

	protected boolean duplicate(Hint hint) {
		// TODO Not implemented
		return false;
	}

	@Override
	public boolean addHint(Context context, Hint hint) {
		// TODO Return Error Code
		if (duplicate(hint))
			return false;

		hint.setRegisterBy(context.getOperatorId());
		hint.setStatus(HintStatus.NEW);
		getHintDao().store(hint);
		return true;
	}

	@Override
	public void followup(Context context, HintFollowup followup) {
		Validate.isTrue(followup.getHint().getOwnBy()
				.equals(context.getOperatorId()));
		Validate.isTrue(!followup.getHint().getStatus()
				.equals(HintStatus.CUSTOMER));

		Hint copy = getHintDao().find(followup.getHint().getOid());
		followup.setOwnBy(context.getOperatorId());
		copy.addFollowup(followup);
	}

	@Override
	public void placeOrder(Context context, Hint hint, Order order,
			CustomerNature nature) {
		Validate.isTrue(hint.getStatus() != HintStatus.CUSTOMER);
		Customer cust = hintToCustomer(hint);
		cust.setNature(nature);
		cust.setStatus(CustomerStatus.OCCUPIED);
		cust.setOwnBy(context.getOperatorId());
		cust.setRegisterBy(context.getOperatorId());
		hint.setStatus(HintStatus.CUSTOMER);
		order.setCustomer(cust);

		getHintDao().store(hint);
		getCustomerDao().store(cust);
		// TODO Store the order
	}

	protected Customer hintToCustomer(Hint hint) {
		Customer cust = new Customer();
		cust.setName(hint.getName());
		cust.setStatus(CustomerStatus.FREE);
		cust.getContacts().put("DEFAULT", hint.getContact());

		return cust;
	}

	@Override
	public List<Hint> getAvailableHints(Context context) {
		return getHintDao().getHints(context.getOperatorId());
	}

	@Override
	public int request(Context context) {
		CustomerSetting cs = getSettingDao().getCustomerSetting();
		int current = getAvailableHints(context).size();
		if (cs.getHintLimit() <= current)
			return 0;
		List<Hint> hints = getHintDao().getFreeHints(
				cs.getHintLimit() - current);
		allocate(context.getOperatorId(), hints);
		return hints.size();
	}

	private void allocate(String operatorId, List<Hint> hints) {
		for (Hint hint : hints) {
			hint.setOwnBy(operatorId);
			hint.setUpdateTime(new Date());
		}
	}

	@Override
	public int exchange(Context context, List<Hint> hints) {
		// TODO Limit exchange size
		for (Hint hint : hints) {
			hint.setOwnBy(null);
		}
		List<Hint> newHints = getHintDao().getFreeHints(hints.size());
		allocate(context.getOperatorId(), newHints);
		return newHints.size();
	}

	private HintDao hintDao;

	private CustomerDao customerDao;

	private SettingDao settingDao;

	public HintDao getHintDao() {
		return hintDao;
	}

	public void setHintDao(HintDao hintDao) {
		this.hintDao = hintDao;
	}

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public SettingDao getSettingDao() {
		return settingDao;
	}

	public void setSettingDao(SettingDao settingDao) {
		this.settingDao = settingDao;
	}

}
