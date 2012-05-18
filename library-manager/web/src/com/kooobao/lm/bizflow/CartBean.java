package com.kooobao.lm.bizflow;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Cookie;

import org.springframework.util.CollectionUtils;

import com.kooobao.common.spring.ApplicationContextHolder;
import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.lm.book.BookService;
import com.kooobao.lm.book.entity.Book;

@SessionScoped
@ManagedBean(name = "cartBean")
public class CartBean extends AbstractBean {

	public void onPageLoad() {
		// Get Cart Id from cookie
		Cookie cookie = getCookie("lm_cart_id");
		if (null != cookie) {
			String cartId = cookie.getValue();
			CartManager cm = ApplicationContextHolder.getInstance()
					.getApplicationContext().getBean(CartManager.class);
			Cart cart = cm.getCart(cartId);
			if (null != cart)
				books = cart.getBooks();
		} else {
			books = new ArrayList<Book>();
		}
	}

	public String confirm() {
		((BorrowConfirmBean) findBean("borrowConfirmBean"))
				.setBorrowed(getBooks());
		return "success";
	}

	private List<Book> books;

	public List<Book> getBooks() {
		return books;
	}

	private List<Book> recommend;

	public List<Book> getRecommend() {
		if (null == recommend) {
			recommend = getBookService().findRecommend(getBooks());
		}
		return recommend;
	}

	@ManagedProperty(value = "#{bookService}")
	private BookService bookService;

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public int getSize() {
		if (CollectionUtils.isEmpty(getBooks()))
			return 0;
		return getBooks().size();
	}

}
