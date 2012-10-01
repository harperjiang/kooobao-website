package com.kooobao.crm.common.unique;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.kooobao.crm.common.unique.UniqueItem.UniqueItemId;

@Entity
@Table(name = "crm_unique_item")
@IdClass(UniqueItemId.class)
public class UniqueItem {
	@Id
	@Column(name = "uuid")
	private String uuid;
	@Id
	@Column(name = "category")
	private String category;
	@Id
	@Column(name = "entry_key")
	private String key;
	@Id
	@Column(name = "entry_value")
	private String value;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static class UniqueItemId {

		private String uuid;

		private String category;

		private String key;

		private String value;

		public String getUuid() {
			return uuid;
		}

		public void setUuid(String uuid) {
			this.uuid = uuid;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof UniqueItemId) {
				UniqueItemId uei = (UniqueItemId) obj;
				return getUuid().equals(uei.getUuid())
						&& getCategory().equals(uei.getCategory())
						&& getKey().equals(uei.getKey())
						&& getValue().equals(uei.getValue());
			}
			return super.equals(obj);
		}

		@Override
		public int hashCode() {
			return getUuid().hashCode() * 31 * 31 * 31
					+ getCategory().hashCode() * 31 * 31 + getKey().hashCode()
					* 31 + getValue().hashCode();
		}
	}
}
