package com.kooobao.lm.book;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.book.entity.Category;

public class DummyBookService implements BookService {

	private List<Book> books = initBooks();

	@Override
	public List<Book> findRecommend(List<Book> selected) {
		return selected;
	}

	@Override
	public List<String> getHotWords() {
		List<String> words = new ArrayList<String>();
		words.add("Good");
		words.add("Bad");
		return words;
	}

	@Override
	public Book getBook(long oid) {
		for (Book book : books) {
			if (book.getOid() == oid)
				return book;
		}
		return null;
	}

	@Override
	public PageSearchResult<Book> searchBooks(String keyword, int start,
			int stop) {
		PageSearchResult<Book> result = new PageSearchResult<Book>(5, books);
		return result;
	}

	private List<Book> initBooks() {
		ArrayList<Book> bks = new ArrayList<Book>();

		Book book = new Book();
		book.setOid(1);
		book.setName("这是第一本书(这是一个很长的标题，会导致排版问题么)");
		book.setBrief("这是第一本书的简介，有点长有点短，反正都是废话，长短也无所谓.这是第一本书的简介，有点长有点短，反正都是废话，长短也无所谓.这是第一本书的简介，有点长有点短，反正都是废话，长短也无所谓.这是第一本书的简介，有点长有点短，反正都是废话，长短也无所谓.这是第一本书的简介，有点长有点短，反正都是废话，长短也无所谓");
		book.setAuthor("大头");
		book.setPublisher("猪头出版社");
		book.setRating(4);
		book.setAbstract("尊敬的顾客，您好：5月11日发往陕西的订单，在配送途中运输车辆发生严重交通事故，不能按时为您送达，为此我们深感抱歉。具体情况我们的客服人员将在今日通过电话与您联系，由于此次事故给您带来的不便，我们深表歉意。感谢您长期以来对当当网的支持与信任。谢谢！");
		bks.add(book);

		book = new Book();
		book.setOid(2);
		book.setName("这是第二本书");
		book.setBrief("这是第二本书的简介，有点长有点短，反正都是废话，长短也无所谓.这是第一本书的简介，有点长有点短，反正都是废话，长短也无所谓.这是第一本书的简介，有点长有点短，反正都是废话，长短也无所谓.这是第一本书的简介，有点长有点短，反正都是废话，长短也无所谓.这是第一本书的简介，有点长有点短，反正都是废话，长短也无所谓");
		book.setAuthor("大头");
		book.setPublisher("猪头出版社");
		bks.add(book);

		book = new Book();
		book.setOid(3);
		book.setName("这是第二本书");
		book.setBrief("这是第二本书的简介，有点长有点短，反正都是废话，长短也无所谓.这是第一本书的简介，有点长有点短，反正都是废话，长短也无所谓.这是第一本书的简介，有点长有点短，反正都是废话，长短也无所谓.这是第一本书的简介，有点长有点短，反正都是废话，长短也无所谓.这是第一本书的简介，有点长有点短，反正都是废话，长短也无所谓");
		book.setAuthor("大头");
		book.setPublisher("猪头出版社");
		bks.add(book);

		book = new Book();
		book.setOid(4);
		book.setName("这是第二本书");
		book.setBrief("这是第二本书的简介，有点长有点短，反正都是废话，长短也无所谓.这是第一本书的简介，有点长有点短，反正都是废话，长短也无所谓.这是第一本书的简介，有点长有点短，反正都是废话，长短也无所谓.这是第一本书的简介，有点长有点短，反正都是废话，长短也无所谓.这是第一本书的简介，有点长有点短，反正都是废话，长短也无所谓");
		book.setAuthor("大头");
		book.setPublisher("猪头出版社");
		bks.add(book);

		return bks;
	}

	List<Category> categories = null;

	@Override
	public List<Category> getRootCategories() {
		if (null != categories)
			return categories;
		try {
			int oidCount = 0;
			categories = new ArrayList<Category>();

			BufferedReader br = new BufferedReader(
					new InputStreamReader(
							DummyBookService.class
									.getResourceAsStream("/com/kooobao/lm/book/category.txt")));
			String line = null;
			Stack<Category> stack = new Stack<Category>();
			while (null != (line = br.readLine())) {
				Category category = new Category();
				category.setOid(++oidCount);
				category.setName(line.trim());

				int curLevel = 0;
				while (true) {
					if (line.charAt(curLevel) == '\t')
						curLevel++;
					else
						break;
				}
				while (!stack.isEmpty() && stack.size() > curLevel) {
					stack.pop();
				}
				if (stack.isEmpty())
					categories.add(category);
				else {
					stack.peek().addChild(category);
				}
				stack.push(category);
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categories;
	}

	@Override
	public List<Book> getPopularBooks() {
		return books;
	}

	@Override
	public List<Book> getNewBooks() {
		return books;
	}

	@Override
	public List<Book> getEditorRecommendBooks() {
		return books;
	}

	@Override
	public List<Book> getOtherBorrowBooks() {
		return books;
	}

	@Override
	public PageSearchResult<Book> getBooksInCategory(Category selectedCategory,
			int start, int stop) {
		return new PageSearchResult<Book>(10, books);
	}

	@Override
	public List<Book> findRecommend(Book book) {
		return books;
	}

	@Override
	public Category getCategory(long categoryOid) {
		return getRootCategories().get(0);
	}

	@Override
	public Book save(Book book) {
		// TODO Auto-generated method stub
		return null;
	}
}
