package com.kooobao.gsm.web.delivery;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIData;

import org.apache.commons.lang.StringUtils;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.common.web.bean.RowIndexCounter;
import com.kooobao.gsm.domain.dao.DeliveryDao;
import com.kooobao.gsm.domain.entity.delivery.Delivery;

@ManagedBean
@SessionScoped
public class SearchDeliveryBean extends AbstractBean {

	private String groupName;

	private String customer;

	private String contactName;

	private String status;

	private List<Delivery> deliveries;

	@ManagedProperty("#{deliveryDao}")
	private DeliveryDao deliveryDao;

	private RowIndexCounter counter = new RowIndexCounter();

	public String search() {
		if (StringUtils.isEmpty(customer) && StringUtils.isEmpty(contactName)) {
			addMessage(FacesMessage.SEVERITY_WARN, "至少输入一项查询条件");
			return "failed";
		}
		setDeliveries(getDeliveryDao().search(
				new DeliveryDao.SearchBean(groupName, status, customer,
						contactName)));

		// ArrayList<Delivery> list = new ArrayList<Delivery>();
		// Delivery d = new Delivery();
		// d.setOid(10);
		// d.setCompany(ExpressCompany.圆通.name());
		// d.setCreateDate(new Date());
		// d.getContact().setName("aaa");
		// d.setStatus(DOStatus.CREATED.name());
		// list.add(d);
		//
		// Order o = new Order();
		// o.setDeliveryStatus(DeliveryStatus.PREPARED.name());
		// OrderItem i = new OrderItem();
		// i.setCount(1);
		// i.setPreparedCount(1);
		// i.setSentCount(0);
		// o.addItem(i);
		//
		// DeliveryItem di = new DeliveryItem();
		// di.setCount(1);
		// di.setOrderItem(i);
		// d.addItem(di);
		// d.setOrder(o);
		// setDeliveries(list);

		counter.reset();

		return "success";
	}

	public String send() {
		UIData dataTable = (UIData) getComponent("dataTable");
		Delivery select = (Delivery) dataTable.getRowData();
		select.deliver();
		getDeliveryDao().store(select);
		return "success";
	}

	public String cancel() {
		UIData dataTable = (UIData) getComponent("dataTable");
		Delivery select = (Delivery) dataTable.getRowData();
		select.cancel();
		getDeliveryDao().store(select);
		return "success";
	}

	public String view() {
		UIData dataTable = (UIData) getComponent("dataTable");
		Delivery select = (Delivery) dataTable.getRowData();

		PrepareDeliveryBean pdb = findBean("prepareDeliveryBean");
		pdb.setDelivery(select);
		return "success";
	}

	public RowIndexCounter getCounter() {
		return counter;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public List<Delivery> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(List<Delivery> deliveries) {
		this.deliveries = deliveries;
	}

	public DeliveryDao getDeliveryDao() {
		return deliveryDao;
	}

	public void setDeliveryDao(DeliveryDao deliveryDao) {
		this.deliveryDao = deliveryDao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
