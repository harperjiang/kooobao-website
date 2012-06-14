package com.kooobao.lm.bizflow.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import com.kooobao.common.domain.entity.StatusUtils;
import com.kooobao.common.domain.entity.VersionEntity;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.profile.entity.Address;
import com.kooobao.lm.profile.entity.BasicAddress;
import com.kooobao.lm.profile.entity.Operator;
import com.kooobao.lm.profile.entity.Visitor;

@Entity
@Table(name = "lm_tran")
public class Transaction extends VersionEntity {

	public void create() {
		Validate.isTrue(StringUtils.isEmpty(state));
		setState(TransactionState.BORROW_REQUESTED);

		Operation operation = new Operation();
		operation.setCreateTime(new Date());
		operation.setFromState((TransactionState) null);
		operation.setToState(TransactionState.BORROW_REQUESTED);
		operation.setDescription(OperationDescriptor.describe(operation));
		addOperation(operation);
	}

	public void approve(String operatorId) {
		Validate.isTrue(getState() == TransactionState.BORROW_REQUESTED);
		setState(TransactionState.BORROW_APPROVED);

		Operation operation = new Operation();
		operation.setOperatorId(operatorId);
		operation.setCreateTime(new Date());
		operation.setFromState(TransactionState.BORROW_REQUESTED);
		operation.setToState(TransactionState.BORROW_APPROVED);
		operation.setDescription(OperationDescriptor.describe(operation));
		addOperation(operation);
	}

	public void sendout(String operatorId, String expressInfo) {
		Validate.isTrue(getState() == TransactionState.BORROW_APPROVED);
		Validate.isTrue(!StringUtils.isEmpty(expressInfo)
				|| DeliveryMethod.SELF_PICK.name().equals(getDelivery()));
		Validate.notEmpty(operatorId);
		setState(TransactionState.BORROW_SENT);

		Operation operation = new Operation();
		operation.setCreateTime(new Date());
		operation.setFromState(TransactionState.BORROW_APPROVED);
		operation.setToState(TransactionState.BORROW_SENT);
		operation.setComment(expressInfo);
		operation.setOperatorId(operatorId);
		operation.setDescription(OperationDescriptor.describe(operation));
		addOperation(operation);
	}

	public void assumeReceived() {
		Validate.isTrue(getState() == TransactionState.BORROW_SENT);
		setState(TransactionState.RETURN_WAIT);

		Operation operation = new Operation();
		operation.setCreateTime(new Date());
		operation.setFromState(TransactionState.BORROW_SENT);
		operation.setToState(TransactionState.RETURN_WAIT);
		operation.setDescription(OperationDescriptor.describe(operation));
		addOperation(operation);
	}

	public void expire() {
		Validate.isTrue(getState() == TransactionState.RETURN_SENT
				|| getState() == TransactionState.RETURN_WAIT);

		Operation operation = new Operation();
		operation.setCreateTime(new Date());
		operation.setFromState(getState());
		operation.setToState(TransactionState.RETURN_EXPIRED);
		operation.setDescription(OperationDescriptor.describe(operation));
		addOperation(operation);

		setState(TransactionState.RETURN_EXPIRED);
	}

	public void sendback(String operatorId, String expressInfo) {
		Validate.isTrue(getState() == TransactionState.RETURN_WAIT
				|| getState() == TransactionState.RETURN_EXPIRED);
		validateExpressInfo(expressInfo);

		Operation operation = new Operation();
		operation.setCreateTime(new Date());
		operation.setFromState(getState());
		operation.setComment(expressInfo);
		operation.setOperatorId(operatorId);
		operation.setTransaction(this);
		addOperation(operation);

		if (getState() == TransactionState.RETURN_WAIT)
			setState(TransactionState.RETURN_SENT);

		operation.setToState(getState());
		operation.setDescription(OperationDescriptor.describe(operation));
	}

	private void validateExpressInfo(String expressInfo) {

	}

	public void returnReceived(String operatorId, BigDecimal penalty,
			String comment) {
		Validate.isTrue(getState() == TransactionState.RETURN_SENT
				|| getState() == TransactionState.RETURN_EXPIRED);
		setState(TransactionState.RETURN_RECEIVED);
		setReturnTime(new Date());
		Operation operation = new Operation();
		operation.setCreateTime(new Date());
		operation.setFromState(getState());
		operation.setToState(TransactionState.RETURN_RECEIVED);
		if (null != penalty)
			operation.setComment("逾期扣款 " + penalty);
		operation.setDescription(OperationDescriptor.describe(operation));
		operation.setComment(comment);
		operation.setOperatorId(operatorId);
		addOperation(operation);
	}

	// Cancel By User
	public void cancel(String reason) {
		Validate.isTrue(getState() == TransactionState.BORROW_REQUESTED
				|| getState() == TransactionState.BORROW_APPROVED);
		Operation operation = new Operation();
		operation.setCreateTime(new Date());
		operation.setFromState(getState());
		setState(TransactionState.CANCELLED);
		operation.setToState(getState());
		operation.setComment(reason);
		operation.setDescription(OperationDescriptor.describe(operation));
		addOperation(operation);
	}

	// Cancel by manager
	public void interrupt(String operatorId, String reason) {
		Validate.isTrue(getState() != TransactionState.CANCELLED
				&& getState() != TransactionState.INTERRUPTED);
		Operation operation = new Operation();
		operation.setCreateTime(new Date());
		operation.setFromState(getState());
		setState(TransactionState.INTERRUPTED);
		operation.setToState(getState());
		operation.setComment(reason);
		operation.setOperatorId(operatorId);
		operation.setDescription(OperationDescriptor.describe(operation));
		addOperation(operation);
	}

	@OneToOne
	@JoinColumn(name = "visitor")
	private Visitor visitor;

	@Column(name = "state")
	private String state;

	@Embedded
	private BasicAddress address = new BasicAddress();

	@Column(name = "delivery_mthd")
	private String delivery;

	@Column(name = "delivery_fee")
	private BigDecimal deliveryFee;

	@OneToOne
	@JoinColumn(name = "book_id")
	private Book book;

	@Column(name = "due_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dueTime;

	@Column(name = "flag")
	private int flag;

	@Column(name = "comment")
	private String comment;

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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "transaction", orphanRemoval = true, targetEntity = Operation.class)
	private List<Operation> operations = new ArrayList<Operation>();

	public List<Operation> getOperations() {
		return operations;
	}

	protected void addOperation(Operation operation) {
		this.operations.add(operation);
		operation.setTransaction(this);
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

	public String getDeliveryText() {
		return StatusUtils.text(DeliveryMethod.valueOf(getDelivery()));
	}

	public void setDelivery(DeliveryMethod delivery) {
		this.delivery = delivery.name();
	}

	public Date getDueTime() {
		return dueTime;
	}

	public void setDueTime(Date dueTime) {
		this.dueTime = dueTime;
	}

	public BigDecimal getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(BigDecimal deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	static final int FLAG_STOCK_RESERVE = 1 << 1;

	public boolean isStockReserved() {
		return (FLAG_STOCK_RESERVE & flag) > 0;
	}

	public void setStockReserved(boolean res) {
		if (res)
			flag |= FLAG_STOCK_RESERVE;
		else
			flag &= (-1 ^ FLAG_STOCK_RESERVE);
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "rating")
	private int rating;

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Column(name = "return_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date returnTime;

	public Date getReturnTime() {
		return returnTime;
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	@OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<TranComment> comments = new ArrayList<TranComment>();

	public List<TranComment> getComments() {
		return comments;
	}

	public void addComment(String comment, Operator o) {
		TranComment c = new TranComment();
		c.setContent(comment);
		c.setCreateTime(new Date());
		c.setTransaction(this);
		if (null != o)
			c.setOperatorId(o.getId());
		getComments().add(c);
	}

}
