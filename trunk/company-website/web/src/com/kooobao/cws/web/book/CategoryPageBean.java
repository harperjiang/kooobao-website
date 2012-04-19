package com.kooobao.cws.web.book;

import java.util.List;

import javax.faces.context.FacesContext;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.cws.domain.book.Category;
import com.kooobao.cws.service.book.BookService;

public class CategoryPageBean extends AbstractBean {

	private Category selectedCategory;

	public Category getSelectedCategory() {
		try {
			long selected = Long.parseLong(String.valueOf(FacesContext
					.getCurrentInstance().getExternalContext()
					.getRequestParameterMap().get("categoryId")));
			if (selected == -1)
				selectedCategory = null;
			else
				selectedCategory = getBookService().getCategory(selected);
		} catch (NumberFormatException e) {
			// Illegal Data, ignore it
			selectedCategory = null;
		}
		return selectedCategory;
	}

	public List<Category> getCategories() {
		if (null == getSelectedCategory())
			return ((CategoryInfoBean) findBean("categoryInfoBean")).getRoots();
		if (selectedCategory.isLeaf())
			if (null == selectedCategory.getParent())
				return null;
			else
				return selectedCategory.getParent().getChildren();
		else
			return selectedCategory.getChildren();
	}

	public long getParentId() {
		if (getSelectedCategory() == null)
			return -1;
		if (selectedCategory.getParent() == null)
			return -1;
		return selectedCategory.getParent().getOid();
	}

	public boolean isDisplayHeader() {
		return getSelectedCategory() != null;
	}

	public Category getHeader() {
		if (!isDisplayHeader())
			return null;
		Category current = getSelectedCategory();
		if (current.isLeaf())
			return current.getParent() == null ? current : current.getParent();
		return current;
	}

	public long getHeaderLinkOid() {
		if (getHeader() == null || getHeader().getParent() == null)
			return -1;
		return getHeader().getParent().getOid();
	}

	private BookService bookService;

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

}
