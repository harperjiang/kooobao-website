package com.kooobao.lm.book;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.kooobao.common.web.bean.AbstractBean;

@ManagedBean(name = "categoryBean")
@SessionScoped
public class CategoryBean extends AbstractBean {

	@Override
	public void onPageLoad() {
		try {
			long categoryOid = Long.parseLong(getParameter("category_id"));
			selectedCategory = getBookService().getCategory(categoryOid);
			categoryBooks = getBookService().getBooksInCategory(
					selectedCategory);
		} catch (NumberFormatException e) {
			FacesContext
					.getCurrentInstance()
					.getApplication()
					.getNavigationHandler()
					.handleNavigation(FacesContext.getCurrentInstance(), null,
							"not_found");
		}

	}

	private List<Book> categoryBooks;

	private Category selectedCategory;

	@ManagedProperty("#{bookService}")
	private BookService bookService;

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public List<Book> getCategoryBooks() {
		return categoryBooks;
	}

	public void setCategoryBooks(List<Book> categoryBooks) {
		this.categoryBooks = categoryBooks;
	}

	public Category getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(Category selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

}
