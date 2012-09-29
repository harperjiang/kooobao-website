package com.kooobao.crm.customer.entity;

import java.util.Map;


public class Customer {

	private String registerBy;

	private String ownBy;

	private String name;
	
	private CustomerNature nature;

	private Map<String, Contact> contacts;

}
