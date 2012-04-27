package com.kooobao.cws.web.article;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.model.SelectItem;

import org.apache.myfaces.shared.util.SelectItemsIterator;

import com.kooobao.cws.domain.article.Section;

public class SectionConverter implements Converter {

	@Override
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

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ConverterException {
		if (null == arg2)
			return null;
		return ((Section) arg2).getName();
	}

}
