package com.kooobao.cws.domain.customer;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.kooobao.common.domain.entity.VersionEntity;

public class Customer extends VersionEntity {

	@Column(name="id")
	private String id;
	
	@Column(name="last_login_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLoginTime;
	
	private Set<Contact> contact;
	
	private int level;
}
