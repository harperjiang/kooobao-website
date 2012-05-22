package com.kooobao.lm.bizflow.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.kooobao.common.domain.entity.StatusUtils;
import com.kooobao.common.domain.entity.VersionEntity;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.profile.entity.Address;
import com.kooobao.lm.profile.entity.BasicAddress;
import com.kooobao.lm.profile.entity.Visitor;

@Entity
@Table(name = "lm_tran")
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

	@OneToOne
	@JoinColumn(name = "visitor")
	private Visitor visitor;

	@Column(name = "state")
	private String state;

	@Embedded
	private BasicAddress address;

	@Column(name = "delivery_mthd")
	private String delivery;

	@OneToOne
	@JoinColumn(name = "book_id")
	private Book book;

	@Column(name="due_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dueTime;

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

	public BasicAddress getAddress() {
		return address;
	}
	
	public void setAddress(BasicAddress address) {
		this.address = address;
	}

	public void setAddress(Address address) {
		this.address = new BasicAddress(address);
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
