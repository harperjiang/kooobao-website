package com.kooobao.common.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.model.SelectItem;

import org.apache.myfaces.shared.util.SelectItemsIterator;

public class SelectItemConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent arg1,
			String name) throws ConverterException {
		if (null == name)
			return null;
		SelectItemsIterator iter = new SelectItemsIterator(arg1, context);
		while (iter.hasNext()) {
			SelectItem next = iter.next();
			if (name.equals(next.getLabel()))
				return next.getValue();
		}
		return null;
	}

	public String getAsString(FacesContext context, UIComponent arg1, Object arg2)
			throws ConverterException {
		if (null == arg2)
			return null;
		return arg2.toString();
	}

}
