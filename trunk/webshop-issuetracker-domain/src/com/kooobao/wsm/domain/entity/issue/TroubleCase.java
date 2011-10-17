package com.kooobao.wsm.domain.entity.issue;

import java.util.ResourceBundle;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TC")
public class TroubleCase extends Issue {

	public static enum Status {
		NEW, WAIT_CUSTOMER, WAIT_SUPPORT, SOLVED, EXPIRED, CLOSED;

		public String text() {
			ResourceBundle rb = ResourceBundle.getBundle(TroubleCase.class
					.getName() + "Status");
			return rb.getString(name());
		}
	}

	public TroubleCase() {
		super();
		setStatus(Status.NEW);
	}

	public void setStatus(Status status) {
		super.setStatus(status.name());
	}

	public String getStatusText() {
		return Status.valueOf(getStatus()).text();
	}

	@Override
	protected String getStatusTextFor(String status) {
		return Status.valueOf(status).text();
	}

	@Override
	public boolean isEditable() {
		return !Status.SOLVED.name().equals(getStatus())
				&& !Status.CLOSED.name().equals(getStatus())
				&& !Status.EXPIRED.name().equals(getStatus());
	}
}
