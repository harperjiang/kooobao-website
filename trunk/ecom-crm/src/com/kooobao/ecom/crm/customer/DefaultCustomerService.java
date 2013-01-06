package com.kooobao.ecom.crm.customer;

import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import com.kooobao.common.domain.dao.Cursor;
import com.kooobao.common.domain.entity.SimpleEntity;
import com.kooobao.common.domain.service.Context;
import com.kooobao.ecom.crm.ErrorCode;
import com.kooobao.ecom.crm.common.ChangeHelper;
import com.kooobao.ecom.crm.common.wordsplit.WordService;
import com.kooobao.ecom.crm.customer.dao.CustomerDao;
import com.kooobao.ecom.crm.customer.dao.HintDao;
import com.kooobao.ecom.crm.customer.entity.Customer;
import com.kooobao.ecom.crm.customer.entity.CustomerFollowup;
import com.kooobao.ecom.crm.customer.entity.CustomerStatus;
import com.kooobao.ecom.crm.customer.entity.CustomerType;
import com.kooobao.ecom.crm.customer.entity.Hint;
import com.kooobao.ecom.crm.customer.entity.HintStatus;
import com.kooobao.ecom.crm.setting.CustomerSetting;
import com.kooobao.ecom.setting.dao.SettingDao;
import com.kooobao.registry.RegistryAccessor;

public class DefaultCustomerService implements CustomerService {

	@Override
	public Customer getCustomer(Context context, String source, String id) {
		return getCustomerDao().find(source, id);
	}

	@Override
	public Customer update(Context context, Customer customer) {
		Customer old = getCustomerDao().find(customer.getOid());
		// Generate change log
		Customer changed = addChange(context, old, customer);
		return changed;
	}

	/*
	 * Generate change log to modifications. If some unmodifiable fields was
	 * found changed, InvalidChangeException will be thrown out
	 */
	private Customer addChange(Context context, Customer old, Customer customer) {
		ChangeHelper.checkInvalidChange(old, customer, new String[] { "id",
				ErrorCode.CUST_UPDATE_CHANGE_ID_FORBID.name() });

		String change = ChangeHelper.generateChange(old, customer,
				new String[] { "name", "Name: {0} -> {1}", "status",
						"Status: {0} -> {1}", "contact.name",
						"Contact Name: {0} -> {1}", "contact.email",
						"Contact Email: {0} -> {1}", "contact.phone",
						"Contact Phone: {0} -> {1}", "contact.address",
						"Contact Address: {0} -> {1}", "contact.im",
						"Contact Im: {0} -> {1}" });

		if (!StringUtils.isEmpty(change)) {
			CustomerFollowup cfu = new CustomerFollowup();
			cfu.setCreateTime(new Date());
			cfu.setReference(change.toString());
			cfu.setOwnBy(context.getOperatorId());
			customer.addFollowup(cfu);
			customer = getCustomerDao().store(customer);
		}
		return customer;
	}

	@Override
	public Customer recordFollowup(Context context, CustomerFollowup followup) {
		Validate.notNull(followup.getCustomer(),
				ErrorCode.CUST_FOLLOWUP_NO_CUST.name());
		Validate.notNull(followup.getMethod(),
				ErrorCode.CUST_FOLLOWUP_NO_METHOD.name());
		Validate.notNull(followup.getReference(),
				ErrorCode.CUST_FOLLOWUP_NO_REF.name());
		Customer copy = getCustomerDao().find(followup.getCustomer().getOid());
		Validate.isTrue(
				SimpleEntity.equals(copy.getOwnBy(), context.getOperatorId()),
				ErrorCode.CUST_UPDATE_NOT_OWN.name());
		copy.setUpdateTime(new Date());
		followup.setCreateTime(new Date());
		followup.setOwnBy(context.getOperatorId());
		copy.addFollowup(followup);
		return getCustomerDao().store(copy);
	}

	@Override
	public List<Customer> getCustomers(Context context) {
		return getCustomerDao().getCustomersByOwner(context.getOperatorId());
	}

	@Override
	public int request(Context context) {
		CustomerSetting cs = getSettingDao().getSetting(CustomerSetting.class);
		int owned = getCustomers(context).size();
		if (owned >= cs.getCustomerLimit())
			return 0;

		List<Customer> freeCustomers = getCustomerDao().getFreeCustomers(
				cs.getCustomerLimit() - owned);

		for (Customer cust : freeCustomers) {
			occupy(context, cust, "Manual Request");
		}
		return freeCustomers.size();
	}

	@Override
	public int exchange(Context context, List<Customer> old) {
		for (Customer c : old) {
			Validate.isTrue(c.getOwnBy().equals(context.getOperatorId())
					&& c.getStatus().equals(CustomerStatus.OCCUPIED));
			c = getCustomerDao().find(c.getOid());
			free(context, c, "Exchange");
		}
		List<Customer> freeCustomers = getCustomerDao().getFreeCustomers(
				old.size());
		for (Customer cust : freeCustomers) {
			occupy(context, cust, "Exchange");
		}
		return freeCustomers.size();
	}

	@Override
	public void freeCustomers() {
		Context context = new Context();
		context.setOperatorId("SYSTEM");
		Cursor<Customer> customers = getCustomerDao().getOvertimeCustomers(
				getSettingDao().getSetting(CustomerSetting.class)
						.getCustomerRetainTime());
		while (customers.hasNext()) {
			Customer cust = customers.next();
			free(context, cust, "Time Out");
		}
	}

	protected void free(Context context, Customer customer, String comment) {
		customer.setOwnBy(null);
		customer.setStatus(CustomerStatus.FREE);
		customer.setUpdateTime(new Date());

		CustomerFollowup cf = new CustomerFollowup();
		cf.setReference("Status: Occupied -> Free");
		cf.setComment(comment);
		cf.setCreateTime(new Date());
		cf.setOwnBy(context.getOperatorId());
		customer.addFollowup(cf);
	}

	protected void occupy(Context context, Customer customer, String comment) {
		customer.setOwnBy(context.getOperatorId());
		customer.setStatus(CustomerStatus.OCCUPIED);
		customer.setUpdateTime(new Date());

		CustomerFollowup cf = new CustomerFollowup();
		cf.setReference("Status: Free -> Occupied");
		cf.setReference(comment);
		cf.setCreateTime(new Date());
		cf.setOwnBy(context.getOperatorId());
		customer.addFollowup(cf);
	}

	@Override
	public Customer upgrade(Context context, Hint hint, CustomerType type) {
		Validate.isTrue(hint.getStatus() != HintStatus.CUSTOMER);
		Customer cust = hintToCustomer(hint);
		cust.setType(type);
		cust.setStatus(CustomerStatus.OCCUPIED);
		cust.setOwnBy(context.getOperatorId());
		cust.setRegisterBy(context.getOperatorId());
		hint.setStatus(HintStatus.CUSTOMER);

		getHintDao().store(hint);
		cust = getCustomerDao().store(cust);
		assignId(cust);
		// Publish the created customer
		RegistryAccessor.getInstance().publish("createCustomer", cust);
		return cust;
	}

	private void assignId(Customer cust) {
		StringBuilder sb = new StringBuilder();
		sb.append("C");
		String oid = String.valueOf(cust.getOid());
		for (int i = 0; i < 10 - oid.length(); i++) {
			sb.append("0");
		}
		sb.append(oid);

		cust.setId(sb.toString());
	}

	protected Customer hintToCustomer(Hint hint) {
		Customer cust = new Customer();
		cust.setName(hint.getName());
		cust.setStatus(CustomerStatus.FREE);
		cust.getContact().setAddress(hint.getContact().getAddress());
		cust.getContact().setName(hint.getContact().getName());
		cust.getContact().setPhone(hint.getContact().getPhone());
		cust.getContact().setIm(hint.getContact().getIm());

		for (Entry<String, String> oc : hint.getOtherContact().entrySet())
			cust.getOtherContact().put(oc.getKey(), oc.getValue());

		return cust;
	}

	private CustomerDao customerDao;

	private WordService wordService;

	private SettingDao settingDao;

	private HintDao hintDao;

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

	public WordService getWordService() {
		return wordService;
	}

	public void setWordService(WordService wordService) {
		this.wordService = wordService;
	}

	public HintDao getHintDao() {
		return hintDao;
	}

	public void setHintDao(HintDao hintDao) {
		this.hintDao = hintDao;
	}

}
