package com.kooobao.cws.web.book;

import java.util.List;

import javax.faces.context.FacesContext;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.common.web.bean.JSFLifecycleAware;
import com.kooobao.cws.domain.book.Book;
import com.kooobao.cws.domain.book.Category;
import com.kooobao.cws.service.book.BookService;

public class BookPageBean extends AbstractBean implements JSFLifecycleAware {

	private Category selectedCategory;

	public Category getSelectedCategory() {
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

	private Book currentBook;

	public Book getCurrentBook() {
		return currentBook;
	}

	private List<Book> books;

	public List<Book> getBooks() {
		return books;
	}

	public void onPageLoad() {
		boolean bookMode = FacesContext.getCurrentInstance()
				.getExternalContext().getRequestParameterMap()
				.containsKey("bookId");
		if (!bookMode) {
			// Read Category Id
			try {
				long categoryId = Long.parseLong(String.valueOf(FacesContext
						.getCurrentInstance().getExternalContext()
						.getRequestParameterMap().get("categoryId")));
				if (categoryId == -1) {
					selectedCategory = null;
				} else {
					selectedCategory = getBookService().getCategory(categoryId);
					if (selectedCategory.isLeaf()) {
						books = getBookService().getBooksUnderCategory(
								selectedCategory);
					}
					currentBook = getBookService().getFirstBookUnderCategory(
							selectedCategory);
				}
			} catch (NumberFormatException e) {
				// Illegal Data, ignore it
				selectedCategory = null;
			}
			currentBook = getBookService().getFirstBookUnderCategory(
					selectedCategory);
		} else {
			// Read Book Id
			try {
				long bookId = Long.parseLong(String.valueOf(FacesContext
						.getCurrentInstance().getExternalContext()
						.getRequestParameterMap().get("bookId")));
				if (bookId == -1) {
					selectedCategory = null;
					currentBook = getBookService().getFirstBookUnderCategory(
							selectedCategory);
				} else {
					currentBook = getBookService().getBook(bookId);
					selectedCategory = currentBook.getCategory();
					if (selectedCategory.isLeaf()) {
						books = getBookService().getBooksUnderCategory(
								selectedCategory);
					}
				}
			} catch (NumberFormatException e) {
				// Illegal Data, ignore it
				selectedCategory = null;
				currentBook = getBookService().getFirstBookUnderCategory(
						selectedCategory);
			}
		}
	}

	private BookService bookService;

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

}
