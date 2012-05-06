package com.kooobao.lm.bizflow;

import java.util.List;

import com.kooobao.common.domain.entity.VersionEntity;
import com.kooobao.lm.profile.Library;
import com.kooobao.lm.profile.Visitor;

public class Transaction extends VersionEntity {

	public void borrow(List<BookPack> bookPacks) {
		adjust(bookPacks);
	}

	public void borrowReceived(List<BookPack> bookPacks) {

	}

	public void expire() {

	}

	public void sendback(List<BookPack> bookPacks) {
		adjust(bookPacks);
	}

	public void returnReceived(List<BookPack> bookPacks) {
		adjust(bookPacks);
	}

	public void cancel(String reason) {

	}

	private void adjust(List<BookPack> bookPacks) {
		adjust(bookPacks);
	}

	private Library library;

	private Visitor visitor;

	private List<TransactionItem> items;

	private String state;

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	public List<TransactionItem> getItems() {
		return items;
	}

	public void addItem(TransactionItem item) {
		this.items.add(item);
	}

	public void removeItem(TransactionItem item) {
		this.items.remove(item);
	}

	private List<Operation> operations;

	public List<Operation> getOperations() {
		return operations;
	}

	protected void addOperation(Operation operation) {
		this.operations.add(operation);
	}

	public State getState() {
		return State.valueOf(this.state);
	}

	public void setState(State state) {
		this.state = state.name();
	}

	public static enum State {
		BORROW_REQUESTED, BORROW_SENT, BORROW_RECEIVED, RETURN_SENT, RETURN_EXPIRED, RETURN_RECEIVED, CANCELLED
	}
}
