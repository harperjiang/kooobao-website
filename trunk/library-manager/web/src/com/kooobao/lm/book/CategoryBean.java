package com.kooobao.lm.book;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;

import com.kooobao.common.web.bean.PageSearchBean;
import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.book.entity.Category;

@ManagedBean(name = "categoryBean")
@SessionScoped
public class CategoryBean extends PageSearchBean {

	@Override
	public void onPageLoad() {
		try {
			String categoryOidStr = getParameter("category_id");
			if (!StringUtils.isEmpty(categoryOidStr)
					|| null != selectedCategory) {
				if (!StringUtils.isEmpty(categoryOidStr)) {
					long categoryOid = Long.parseLong(categoryOidStr);
					selectedCategory = getBookService()
							.getCategory(categoryOid);
					search();
				}
			} else {
				selectedCategory = getBookService().getRootCategories().get(0);
				search();
			}
		} catch (Exception e) {
			FacesContext
					.getCurrentInstance()
					.getApplication()
					.getNavigationHandler()
					.handleNavigation(FacesContext.getCurrentInstance(), null,
							"not_found");
		}
	}

	public String search() {
		PageSearchResult<Book> result = getBookService().getBooksInCategory(
				selectedCategory, getRecordStart(), getRecordStop());
		categoryBooks = result.getResult();
		setRecordCount(result.getCount());
		return "success";
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
