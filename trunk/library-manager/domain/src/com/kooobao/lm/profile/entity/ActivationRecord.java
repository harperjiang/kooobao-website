package com.kooobao.lm.profile.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.kooobao.lm.profile.entity.ActivationRecord.ARIdClass;

@Entity
@IdClass(ARIdClass.class)
@Table(name = "lm_visitor_actrecord")
public class ActivationRecord {

	public static class ARIdClass {
		@Id
		private String visitorId;
		@Id
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
