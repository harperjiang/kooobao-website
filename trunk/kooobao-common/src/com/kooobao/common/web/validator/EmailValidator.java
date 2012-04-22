package com.kooobao.common.web.validator;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class EmailValidator implements Validator {

	static final String pattern = "[a-zA-Z0-9_]+@([a-zA-Z]+\\.)+[a-zA-Z]+";
	
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
		if(!Pattern.matches(pattern, String.valueOf(value))){
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,"Invalid Email Format","Invalid Email Format"));
		}
	}

}
