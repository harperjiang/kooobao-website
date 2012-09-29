package com.kooobao.cws.domain.resource;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kooobao.cws.domain.customer.Customer;
import com.kooobao.cws.domain.resource.CustomerScore.CustomerScorePK;

@Entity
@IdClass(CustomerScorePK.class)
@Table(name = "cws_customer_score")
public class CustomerScore {

	@Id
	@Column(name = "customer_id", updatable = false, insertable = false)
	private long customerId;

	@OneToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "obj_id")
	private Customer customer;

	@Id
	@Column(name = "score")
	private int score;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
		this.customerId = customer.getOid();
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public static class CustomerScorePK implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -2877046036804052931L;

		private long customerId;

		private int score;

		public Long getCustomerId() {
			return customerId;
		}

		public void setCustomerId(long customerId) {
			this.customerId = customerId;
		}

		public Integer getScore() {
			return score;
		}

		public void setScore(int score) {
			this.score = score;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof CustomerScorePK) {
				CustomerScorePK csp = (CustomerScorePK) obj;
				return csp.getCustomerId() == getCustomerId()
						&& csp.getScore() == getScore();
			}
			return super.equals(obj);
		}

		@Override
		public int hashCode() {
			return getScore().hashCode() * 39 + getCustomerId().hashCode();
		}
	}

}
