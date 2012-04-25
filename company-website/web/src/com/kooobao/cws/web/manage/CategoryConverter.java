package com.kooobao.cws.web.manage;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.kooobao.cws.domain.book.Category;
import com.kooobao.cws.web.book.CategoryInfoBean;

public class CategoryConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent arg1,
			String layeredName) throws ConverterException {
		if (null == layeredName)
			return null;
		return context
				.getApplication()
				.evaluateExpressionGet(context, "#{categoryInfoBean}",
						CategoryInfoBean.class).getCategory(layeredName);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ConverterException {
		if (null == arg2)
			return null;
		return ((Category) arg2).getLayeredName();
	}

}
