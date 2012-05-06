package com.kooobao.lm.bizflow;

import java.util.Date;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang.StringUtils;

import com.kooobao.lm.book.Book;
import com.kooobao.lm.book.BookService;

public class CartManager {

	static final long EXPIRE_THRESHOLD = 3600000;

	private ReentrantLock lock = new ReentrantLock();

	private Queue<Cart> cartExpireQueue;

	private Map<String, Cart> carts;

	public CartManager() {
		carts = new ConcurrentHashMap<String, Cart>();
		cartExpireQueue = new ConcurrentLinkedQueue<Cart>();
		new CartDaemonThread().start();
	}

	public String addToCart(String cartId, long oid) {
		if (0 == oid)
			return null;
		try {
			Book book = getBookService().getBook(oid);
			lock.lock();
			if (StringUtils.isEmpty(cartId) || !carts.containsKey(cartId)) {
				// New comer, construct a cart
				String newId = UUID.randomUUID().toString();
				Cart cart = new Cart();
				cart.add(book);
				cart.setUuid(newId);
				cart.setUpdateTime(new Date());
				carts.put(newId, cart);
				cartExpireQueue.offer(cart);
				return newId;
			} else {
				Cart cart = carts.get(cartId);
				cart.add(book);
				cart.setUpdateTime(new Date());
				cartExpireQueue.remove(cart);
				cartExpireQueue.offer(cart);
				return cartId;
			}
		} finally {
			lock.unlock();
		}
	}

	public void removeFromCart(String cartId, long oid) {
		try {
			lock.lock();
		} finally {
			lock.unlock();
		}
	}

	public void clearExpired() {
		try {
			lock.lock();
			long threshold = System.currentTimeMillis() - EXPIRE_THRESHOLD;
			while (cartExpireQueue.peek().getUpdateTime().getTime() < threshold) {
				Cart expired = cartExpireQueue.poll();
				carts.remove(expired.getUuid());
			}
		} finally {
			lock.unlock();
		}
	}

	public class CartDaemonThread extends Thread {

		static final long INTERVAL = 3600000;

		public CartDaemonThread() {
			setDaemon(true);
		}

		public void run() {
			while (true) {
				try {
					Thread.sleep(INTERVAL);
					clearExpired();
				} catch (InterruptedException e) {
					// Unlikely this will be useful.
					clearExpired();
				}
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

	public Cart getCart(String cartId) {
		return carts.get(cartId);
	}
}
