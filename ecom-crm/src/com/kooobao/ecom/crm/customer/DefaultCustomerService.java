package com.kooobao.ecom.crm.customer;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.Validate;

import com.kooobao.common.domain.dao.Cursor;
import com.kooobao.ecom.crm.common.Context;
import com.kooobao.ecom.crm.common.wordsplit.WordService;
import com.kooobao.ecom.crm.customer.dao.CustomerDao;
import com.kooobao.ecom.crm.customer.entity.Customer;
import com.kooobao.ecom.crm.customer.entity.CustomerFollowup;
import com.kooobao.ecom.crm.customer.entity.CustomerStatus;
import com.kooobao.ecom.crm.setting.dao.SettingDao;
import com.kooobao.ecom.crm.setting.entity.CustomerSetting;

public class DefaultCustomerService implements CustomerService {

	@Override
	public Customer getCustomer(Context context, String source, String id) {
		return getCustomerDao().find(source, id);
	}

	@Override
	public void update(Context context, Customer customer) {
		// TODO Set unmodifiable limit to some fields
		// TODO Generate change log
		Customer old = getCustomerDao().find(customer.getOid());

		CustomerFollowup change = generateChange(old, customer);
		if (null != change) {
			change.setOwnBy(context.getOperatorId());
			customer.addFollowup(change);

			getCustomerDao().store(customer);
		}
	}

	private CustomerFollowup generateChange(Customer old, Customer customer) {
		StringBuffer change = new StringBuffer();

		if (!old.getName().equals(customer.getName())) {
			change.append(MessageFormat.format("Name: {0} -> {1}\n",
					old.getName(), customer.getName()));
		}

		if (!old.getContact().equals(customer.getContact())) {
			change.append(MessageFormat.format("Contact: {0} -> {1}\n", old
					.getContact().toString(), customer.getContact().toString()));
		}

		if (change.length() != 0) {
			CustomerFollowup cfu = new CustomerFollowup();
			cfu.setReference(change.toString());
			return cfu;
		} else
			return null;
	}

	@Override
	public void recordFollowup(Context context, CustomerFollowup followup) {
		Validate.notNull(followup.getCustomer());
		Validate.notNull(followup.getMethod());
		Validate.notNull(followup.getReference());
		Validate.isTrue(followup.getCustomer().getOwnBy()
				.equals(context.getOperatorId()));

		Customer copy = getCustomerDao().find(followup.getCustomer().getOid());
		copy.setUpdateTime(new Date());
		followup.setCreateTime(new Date());
		followup.setOwnBy(context.getOperatorId());
		copy.addFollowup(followup);
	}

	@Override
	public List<Customer> getCustomers(Context context) {
		return getCustomerDao().getCustomersByOwner(context.getOperatorId());
	}

	@Override
	public int request(Context context) {
		CustomerSetting cs = getSettingDao().getCustomerSetting();
		int owned = getCustomers(context).size();
		if (owned >= cs.getCustomerLimit())
			return 0;

		List<Customer> freeCustomers = getCustomerDao().getFreeCustomers(
				cs.getCustomerLimit() - owned);

		for (Customer cust : freeCustomers) {
			cust.setOwnBy(context.getOperatorId());
			cust.setStatus(CustomerStatus.OCCUPIED);
			cust.setUpdateTime(new Date());
		}
		return freeCustomers.size();
	}

	@Override
	public int exchange(Context context, List<Customer> old) {
		for (Customer c : old) {
			Validate.isTrue(c.getOwnBy().equals(context.getOperatorId())
					&& c.getStatus().equals(CustomerStatus.OCCUPIED));
			c.setStatus(CustomerStatus.FREE);
			c.setOwnBy(null);
		}
		List<Customer> freeCustomers = getCustomerDao().getFreeCustomers(
				old.size());
		for (Customer cust : freeCustomers) {
			cust.setOwnBy(context.getOperatorId());
			cust.setStatus(CustomerStatus.OCCUPIED);
			cust.setUpdateTime(new Date());
		}
		return freeCustomers.size();
	}

	@Override
	public void freeCustomers() {
		Context context = new Context();
		context.setOperatorId("SYSTEM");
		Cursor<Customer> customers = getCustomerDao().getOvertimeCustomers(
				getSettingDao().getCustomerSetting().getCustomerRetainTime());
		while (customers.hasNext()) {
			Customer cust = customers.next();
			free(context, cust, "OVERTIME");
		}
	}

	public void free(Context context, Customer customer, String comment) {
		customer.setOwnBy(null);
		customer.setStatus(CustomerStatus.FREE);
		customer.setUpdateTime(new Date());

		CustomerFollowup cf = new CustomerFollowup();
		cf.setComment("FREE:" + comment);
		cf.setCreateTime(new Date());
		cf.setOwnBy(context.getOperatorId());
		customer.addFollowup(cf);
	}

	private CustomerDao customerDao;

	private WordService wordService;

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	private SettingDao settingDao;

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

}
