package com.kooobao.lm.profile.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(IdClass.class)
@Table(name = "lm_act_record")
public class ActivationRecord {

	public static class IdClass {
		private String visitorId;

		private String activationId;

		public String getVisitorId() {
			return visitorId;
		}

		public void setVisitorId(String visitorId) {
			this.visitorId = visitorId;
		}

		public String getActivationId() {
			return activationId;
		}

		public void setActivationId(String activationId) {
			this.activationId = activationId;
		}
	}

	@Id
	@Column(name = "visitor_id")
	private String visitorId;

	@Id
	@Column(name = "activation_id")
	private String activationId;

	public String getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(String visitorId) {
		this.visitorId = visitorId;
	}

	public String getActivationId() {
		return activationId;
	}

	public void setActivationId(String activationId) {
		this.activationId = activationId;
	}

}
