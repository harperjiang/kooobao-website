package com.kooobao.crm.customer;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.Validate;

import com.kooobao.common.domain.dao.Cursor;
import com.kooobao.crm.common.Context;
import com.kooobao.crm.customer.dao.CustomerDao;
import com.kooobao.crm.customer.entity.Customer;
import com.kooobao.crm.customer.entity.CustomerFollowup;
import com.kooobao.crm.customer.entity.CustomerStatus;
import com.kooobao.crm.setting.dao.SettingDao;
import com.kooobao.crm.setting.entity.CustomerSetting;

public class DefaultCustomerService implements CustomerService {

	protected boolean duplicate(Customer customer) {
		return false;
	}

	@Override
	public void addCustomer(Context context, Customer customer) {
		// TODO Global Lock to ensure uniqueness
		if (!duplicate(customer)) {
			customer.setStatus(CustomerStatus.OCCUPIED);
			customer.setRegisterBy(context.getOperatorId());
			customer.setOwnBy(context.getOperatorId());
			customer.setUpdateTime(new Date());
			getCustomerDao().store(customer);
		}
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
		Cursor<Customer> customers = getCustomerDao().getOvertimeCustomers(
				getSettingDao().getCustomerSetting().getCustomerRetainTime());
		while (customers.hasNext()) {
			Customer cust = customers.next();
			cust.setOwnBy(null);
			cust.setStatus(CustomerStatus.FREE);
			cust.setUpdateTime(new Date());
		}
	}

	private CustomerDao customerDao;

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

}
