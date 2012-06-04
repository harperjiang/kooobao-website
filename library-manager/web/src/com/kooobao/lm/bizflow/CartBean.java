package com.kooobao.lm.bizflow;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Cookie;

import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;

import com.kooobao.common.spring.ApplicationContextHolder;
import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.lm.book.BookService;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.profile.LoginBean;

@SessionScoped
@ManagedBean(name = "cartBean")
public class CartBean extends AbstractBean {

	public void onPageLoad() {
		Cart cart = getCart();

		if (null != cart)
			books = cart.getBooks();
		else {
			books = new ArrayList<Book>();
		}
		getSum();
	}

	private void getSum() {
		BigDecimal sumw = BigDecimal.ZERO;
		BigDecimal sump = BigDecimal.ZERO;
		for (Book book : books) {
			sumw = sumw.add(new BigDecimal(book.getNetWeight()));
			sump = sump.add(book.getListPrice());
		}
		sumw = sumw.setScale(2, BigDecimal.ROUND_HALF_UP);
		sump = sump.setScale(2, BigDecimal.ROUND_HALF_UP);
		setSumPrice(sump);
		setSumWeight(sumw);
	}

	private BigDecimal sumWeight;

	private BigDecimal sumPrice;

	public BigDecimal getSumWeight() {
		return sumWeight;
	}

	public void setSumWeight(BigDecimal sumWeight) {
		this.sumWeight = sumWeight;
	}

	public BigDecimal getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(BigDecimal sumPrice) {
		this.sumPrice = sumPrice;
	}

	protected Cart getCart() {// Get Cart Id from cookie
		Cookie cookie = getCookie("lm_cart_id");
		if (null != cookie) {
			String cartId = cookie.getValue();
			CartManager cm = ApplicationContextHolder.getInstance()
					.getApplicationContext().getBean(CartManager.class);
			Cart cart = cm.getCart(cartId);
			return cart;
		}
		return null;
	}

	public String confirm() {
		if (CollectionUtils.isEmpty(getBooks())) {
			addMessage(FacesMessage.SEVERITY_WARN, "您的购物车是空的");
			return "failed";
		}
		LoginBean loginBean = findBean("loginBean");
		if (!loginBean.isLoggedIn()) {
			addMessage(FacesMessage.SEVERITY_WARN, "请先登录");
			return "failed";
		} else {
			((BorrowConfirmBean) findBean("borrowConfirmBean"))
					.setBorrowed(getBooks());

			return "success";
		}
	}

	public String delete() {
		try {
			long bookId = Long.parseLong(getParameter("book_id"));
			Book book = getBookService().getBook(bookId);
			getCart().remove(book);
			return "success";
		} catch (Exception e) {
			LogFactory.getLog(CartBean.class).error(
					"Exception when executing action", e);
			return "failed";
		}
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
