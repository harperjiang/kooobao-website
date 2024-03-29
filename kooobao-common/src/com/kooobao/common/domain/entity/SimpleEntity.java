package com.kooobao.common.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class SimpleEntity {

	@Id
	@Column(name = "obj_id", columnDefinition = "decimal(10)")
	private long oid;

	public long getOid() {
		return oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	@Column(name = "create_time", columnDefinition = "datetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	protected static final String extend(long number, int extendTo) {
		if (String.valueOf(number).length() >= extendTo) {
			return String.valueOf(number);
		}
		StringBuilder sb = new StringBuilder();
		sb.append(number);
		while (sb.length() < extendTo) {
			sb.insert(0, '0');
		}
		return sb.toString();
	}

	public static <T extends Comparable<T>> int compare(T a, T b) {
		if (a == b && a == null)
			return 0;
		if (a != null && b == null)
			return 1;
		if (a == null && b != null)
			return -1;
		return a.compareTo(b);
	}

	public static boolean equals(Object a, Object b) {
		if (a == b)
			return true;
		if (a == null)
			return false;
		return a.equals(b);
	}
}
