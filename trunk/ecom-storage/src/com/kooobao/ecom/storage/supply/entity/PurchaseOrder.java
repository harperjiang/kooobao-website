package com.kooobao.ecom.storage.supply.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import oracle.toplink.indirection.ValueHolder;
import oracle.toplink.indirection.ValueHolderInterface;

import org.apache.commons.lang.Validate;
import org.harper.bookstore.domain.deliver.DeliveryOrder;
import org.harper.bookstore.domain.profile.BookUnit;
import org.harper.bookstore.domain.profile.Customer;
import org.harper.bookstore.domain.store.StoreSite;
import org.harper.bookstore.repo.RepoFactory;
import org.harper.bookstore.repo.StoreRepo;
import org.harper.bookstore.service.TaobaoOrderStatus;
import org.harper.frm.ValidateException;

public class PurchaseOrder extends Order {

	public static enum Status {
		NEW, DRAFT, CONFIRM, FINISH, CANCEL;
	}

	private Customer customer;

	// External Status
	private String refStatus;

	// External Item
	private List<DisplayItem> dispItems;

	public static enum DeliveryStatus {
		NOT_SENT, FULLY_SENT, PARTIAL_SENT
	}

	private int deliveryStatus;

	private ValueHolderInterface delivery;

	private ValueHolderInterface deliveryOrders;

	public PurchaseOrder() {
		super();
		customer = new Customer();
		dispItems = new ArrayList<DisplayItem>();

		DeliveryOrder infoDo = new DeliveryOrder();
		infoDo.setValid(false);
		delivery = new ValueHolder(infoDo);
		deliveryOrders = new ValueHolder(new Vector());
	}

	public void addDispItem(DisplayItem item) {
		this.dispItems.add(item);
		item.setOrder(this);
	}

	public void removeDispItem(DisplayItem item) {
		this.dispItems.remove(item);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getRefStatus() {
		return refStatus;
	}

	public String getRefStatusDesc() {
		if (null == refStatus)
			return null;
		try {
			return TaobaoOrderStatus.valueOf(refStatus).desc();
		} catch (Exception e) {
			return refStatus;
		}
	}

	public void setRefStatus(String refStatus) {
		this.refStatus = refStatus;
	}

	public Customer getParty() {
		return customer;
	}

	public Status getOrderStatus() {
		return Status.values()[super.getStatus()];
	}

	public String getOrderExpressStatus() {
		ResourceBundle rb = ResourceBundle
				.getBundle("org.harper.bookstore.domain.order.DeliveryStatus");
		return rb
				.getString(DeliveryStatus.values()[getDeliveryStatus()].name());
	}

	public CollectPlan getCollectPlan() {
		return null;
	}

	public void place() {
		Validate.isTrue(getOrderStatus() == Status.NEW);
		setStatus(Status.DRAFT.ordinal());
		// lockStorage();
	}

	public void confirm() {
		Validate.isTrue(getOrderStatus() == Status.DRAFT);
		setStatus(Status.CONFIRM.ordinal());
		setTotalAmt(getTotal());
		// collectStorage();
	}

	public void cancel() {
		int oldStatus = getStatus();
		Validate.isTrue(getDeliveryStatus() == DeliveryStatus.NOT_SENT
				.ordinal());
		setStatus(Status.CANCEL.ordinal());
		// if (oldStatus == Status.DRAFT.ordinal()) {
		// releaseStorage();
		// }
		// if (oldStatus == Status.CONFIRM.ordinal()) {
		// returnStorage();
		// }
	}

	protected void lockStorage() {

		// Retrieve Item from StoreSite
		for (OrderItem item : getItems()) {
			if (item.isAgent())
				// Agent Item is retrieved from others rather than own store, no
				// need to lock
				continue;
			StoreSite site = getSite();
			for (BookUnit book : item.getBook().getContent()) {
				int remains = item.getCount() * book.getCount();
				remains -= site.lock(book.getBook(), remains);
				if (remains > 0)
					throw ValidateException.notEnoughBook(book.getBook(),
							item.getCount() - remains);
			}
		}
	}

	protected void collectStorage() {
		StoreRepo storeRepo = RepoFactory.INSTANCE.getStoreRepo();

		// Retrieve Item from StoreSite
		for (OrderItem item : getItems()) {
			if (item.isAgent())
				continue;
			BigDecimal sum = BigDecimal.ZERO;
			StoreSite site = getSite();
			for (BookUnit book : item.getBook().getContent()) {

				int remains = item.getCount() * book.getCount();
				BookUnit unit = site.retrieve(book.getBook(), remains);
				if (null != unit) {
					remains -= unit.getCount();
					sum = sum.add(unit.getSum());
				}
				if (remains > 0)
					throw ValidateException.notEnoughBook(book.getBook(),
							item.getCount() - remains);

			}
			if (0 != item.getCount())
				item.setUnitCost(sum.divide(new BigDecimal(item.getCount()),
						BigDecimal.ROUND_HALF_UP));
		}
	}

	protected void releaseStorage() {
		StoreRepo storeRepo = RepoFactory.INSTANCE.getStoreRepo();

		// Retrieve Item from StoreSite
		for (OrderItem item : getItems()) {
			if (item.isAgent())
				continue;
			StoreSite site = getSite();
			for (BookUnit book : item.getBook().getContent()) {

				int remains = item.getCount() * book.getCount();
				remains -= site.cancel(book.getBook(), remains);
				if (remains > 0)
					throw ValidateException.notEnoughBook(book.getBook(),
							item.getCount() - remains);
			}
		}
	}

	protected void returnStorage() {
		// Retrieve Item from StoreSite
		for (OrderItem item : getItems()) {
			if (item.isAgent())
				continue;
			BigDecimal[] splitPrice = CalcHelper.split(item.getUnitCost(), item
					.getBook().getContent());
			for (int i = 0; i < item.getBook().getContent().size(); i++) {
				BookUnit book = item.getBook().getContent().get(i);
				StoreSite site = getSite();
				site.putInto(book.getBook(), item.getCount() * book.getCount(),
						splitPrice[i]);
			}
		}
	}

	public void removeAllDispItems() {
		dispItems.clear();
	}

	public List<DisplayItem> getDispItems() {
		return dispItems;
	}

	public DeliveryOrder getDelivery() {
		if (null == delivery.getValue())
			delivery.setValue(new DeliveryOrder());
		return (DeliveryOrder) delivery.getValue();
	}

	public void setDelivery(DeliveryOrder delivery) {
		this.delivery.setValue(delivery);
	}

	public List<DeliveryOrder> getDeliveryOrders() {
		return (List<DeliveryOrder>) deliveryOrders.getValue();
	}

	public void setDeliveryOrders(List<DeliveryOrder> deliveryOrders) {
		this.deliveryOrders.setValue(deliveryOrders);
	}

	public int getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(int deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getDeliveryNumber() {
		String doNumber = null == getDelivery() ? "" : getDelivery()
				.getNumber();
		if (null != getDeliveryOrders() && getDeliveryOrders().size() > 1)
			doNumber += "+";
		return doNumber;
	}

	/**
	 * Make a quick, full delivery order for this PO
	 */
	public void quickDeliver() {
		Validate.isTrue(DeliveryStatus.NOT_SENT.ordinal() == getDeliveryStatus());
		// Update the delivery order to be valid;
		getDelivery().setValid(true);
		if (null == getDelivery().getCreateDate())
			getDelivery().setCreateDate(new Date());

		getDelivery().getContact().copy(getContact());
		getDeliveryOrders().add(getDelivery());
		if (DeliveryOrder.Status.NEW.ordinal() == getDelivery().getStatus())
			getDelivery().create();
		getDelivery().deliver();
	}

	/**
	 * Calculate the sent status based on item's sent amount
	 */
	public void makeDelivery() {
		Validate.isTrue(getDeliveryStatus() != DeliveryStatus.FULLY_SENT
				.ordinal());
		int size = 0;
		for (OrderItem item : getItems())
			if (item.getCount() == item.getSentCount())
				size += 2;
			else if (item.getSentCount() == 0)
				size += 0;
			else
				size += 1;
		if (size == getItems().size() * 2)
			setDeliveryStatus(DeliveryStatus.FULLY_SENT.ordinal());
		else if (0 == size)
			setDeliveryStatus(DeliveryStatus.NOT_SENT.ordinal());
		else
			setDeliveryStatus(DeliveryStatus.PARTIAL_SENT.ordinal());
	}
}
