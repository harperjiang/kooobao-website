package com.kooobao.cws.domain.customer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.eclipse.persistence.annotations.Converter;

import com.kooobao.common.domain.converter.EnumConverter;

@Embeddable
public class Contact implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6717181573778839710L;

	@Column(name="type")
	@Converter(name = "contactTypeConverter",converterClass=ContactTypeConverter.class)
	private ContactType type;

	@Column(name="value")
	private String value;
	
	public Contact() {
		
	}

	public Contact(ContactType type, String value) {
		super();
		Validate.isTrue(!StringUtils.isEmpty(value));
		setType(type);
		setValue(value);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Contact) {
			return type.equals(((Contact) obj).type)
					&& value.equals(((Contact) obj).value);
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return type.hashCode() * 13 + value.hashCode();
	}

	public ContactType getType() {
		return type;
	}

	public void setType(ContactType type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static class ContactTypeConverter extends EnumConverter {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 8405019859699805285L;

		public ContactTypeConverter() {
			enumClass = ContactType.class;
		}
	}
}
