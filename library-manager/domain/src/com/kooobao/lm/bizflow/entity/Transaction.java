package com.kooobao.lm.bizflow.entity;

import java.util.Date;
import java.util.List;

import com.kooobao.common.domain.entity.StatusUtils;
import com.kooobao.common.domain.entity.VersionEntity;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.profile.entity.Address;
import com.kooobao.lm.profile.entity.Visitor;

public class Transaction extends VersionEntity {

	public void approve() {
	}

	public void borrowReceived() {

	}

	public void expire() {

	}

	public void sendback() {
	}

	public void returnReceived() {
	}

	public void cancel(String reason) {

	}

	private String id;

	private Visitor visitor;

	private String state;

	private Address address;

	private String delivery;

	private Book book;

	private int count;

	private Date dueTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	private List<Operation> operations;

	public List<Operation> getOperations() {
		return operations;
	}

	protected void addOperation(Operation operation) {
		this.operations.add(operation);
	}

	public TransactionState getState() {
		return TransactionState.valueOf(this.state);
	}

	public void setState(TransactionState state) {
		this.state = state.name();
	}

	public String getStateText() {
		return StatusUtils.text(getState());
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public Date getDueTime() {
		return dueTime;
	}

	public void setDueTime(Date dueTime) {
		this.dueTime = dueTime;
	}

}
