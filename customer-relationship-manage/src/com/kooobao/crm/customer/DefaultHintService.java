package com.kooobao.crm.customer;

import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang.Validate;

import com.kooobao.common.domain.dao.Cursor;
import com.kooobao.crm.common.Context;
import com.kooobao.crm.common.unique.UniqueEntry;
import com.kooobao.crm.common.unique.UniquenessDao;
import com.kooobao.crm.common.unique.UniquenessException;
import com.kooobao.crm.common.wordsplit.WordService;
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
		Context context = new Context();
		context.setOperatorId("SYSTEM");
		Cursor<Hint> hints = getHintDao().getOvertimeHints(
				getSettingDao().getCustomerSetting().getHintRetainTime());
		while (hints.hasNext()) {
			Hint hint = hints.next();
			free(context, hint, "Over protection");
		}
	}

	protected boolean duplicate(Hint hint) {
		// Construct Uniqueness Entry
		UniqueEntry ue = new UniqueEntry();
		ue.setCategory("HINT");
		// Split and filter words
		String[] nameKeys = getWordService().splitWord(hint.getName());
		for (String nameKey : nameKeys) {
			ue.getAttributes("NAME").put(nameKey, "NAME");
		}
		ue.getAttributes("CONTACT").put(hint.getContact().getPhone(), "PHONE");
		ue.getAttributes("CONTACT").put(hint.getContact().getName(), "NAME");
		ue.getAttributes("CONTACT").put(hint.getContact().getQq(), "QQ");
		ue.getAttributes("CONTACT").put(hint.getContact().getAddress(),
				"ADDRESS");

		// Store to uniqueness store
		try {
			getUniquenessDao().store(ue);
			return false;
		} catch (UniquenessException e) {
			if (e.getCode() == UniquenessException.SUSPECT) {
				hint.setStatus(HintStatus.SUSPEND);
				return false;
			}
			return true;
		}
	}

	@Override
	public boolean addHint(Context context, Hint hint) {
		// TODO Return Error Code
		if (duplicate(hint))
			return false;
		if (HintStatus.SUSPEND == hint.getStatus()) {
			// Need human revise
		}
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
		cust.getContact().setAddress(hint.getContact().getAddress());
		cust.getContact().setName(hint.getContact().getName());
		cust.getContact().setPhone(hint.getContact().getPhone());
		cust.getContact().setQq(hint.getContact().getQq());

		for (Entry<String, String> oc : hint.getOtherContact().entrySet())
			cust.getOtherContact().put(oc.getKey(), oc.getValue());

		assignId(cust);

		return cust;
	}

	private void assignId(Customer cust) {
		// TODO Not implemented

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

	@Override
	public void discard(Context context, Hint hint, String comment) {
		HintFollowup hfu = new HintFollowup();
		hfu.setOwnBy(context.getOperatorId());
		hfu.setCreateTime(new Date());
		hfu.setComment("Discarded:" + comment);
		hfu.setReference("Discard");
		hint.setStatus(HintStatus.DISCARDED);

	}

	public void free(Context context, Hint hint, String comment) {
		HintFollowup hfu = new HintFollowup();
		hfu.setOwnBy(context.getOperatorId());
		hfu.setCreateTime(new Date());
		hfu.setComment("Free:" + comment);
		hfu.setReference("Free");
		hint.setStatus(HintStatus.FREE);
	}

	@Override
	public void revise(Context context, Hint hint, boolean pass) {
		if (pass) {
			free(context, hint, "Revise");
		} else {
			discard(context, hint, "Revise");
		}
	}

	@Override
	public void update(Context context, Hint hint) {
		getHintDao().store(hint);
	}

	private HintDao hintDao;

	private CustomerDao customerDao;

	private SettingDao settingDao;

	private UniquenessDao uniquenessDao;

	private WordService wordService;

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

	public UniquenessDao getUniquenessDao() {
		return uniquenessDao;
	}

	public void setUniquenessDao(UniquenessDao uniquenessDao) {
		this.uniquenessDao = uniquenessDao;
	}

	public WordService getWordService() {
		return wordService;
	}

	public void setWordService(WordService wordService) {
		this.wordService = wordService;
	}

}
