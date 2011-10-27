package com.kooobao.util.print;


public class ContactInfo {

	private String name;

	private String email;

	private String address;

	private String phone;

	private String mobile;

	private String postalCode;

	public void copy(ContactInfo src) {
		setName(src.getName());
		setAddress(src.getAddress());
		setEmail(src.getEmail());
		setPhone(src.getPhone());
		setMobile(src.getMobile());
		setPostalCode(src.getPostalCode());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

}
