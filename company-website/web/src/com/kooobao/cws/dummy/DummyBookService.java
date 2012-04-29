package com.kooobao.cws.dummy;

import java.util.ArrayList;
import java.util.List;

import com.kooobao.cws.domain.book.Book;
import com.kooobao.cws.domain.book.Category;
import com.kooobao.cws.service.book.BookService;

public class DummyBookService implements BookService {

	private List<Book> books;

	private List<Category> rootCategories;

	public DummyBookService() {
		super();
		init();
	}

	void init() {
		List<Category> categories = new ArrayList<Category>();
		rootCategories = categories;

		Category category = new Category();
		category.setOid(1);
		category.setName("Pre-K教育");
		categories.add(category);

		Category sub1 = new Category();
		sub1.setOid(100);
		sub1.setName("Houghton Mifflin 幼教書籍1");
		category.addChild(sub1);
		Category sub2 = new Category();
		sub2.setOid(101);
		sub2.setName("Houghton Mifflin 幼教書籍2");
		category.addChild(sub2);

		category = new Category();
		category.setOid(2);
		category.setName("Kindergarten");
		categories.add(category);

		category = new Category();
		category.setOid(3);
		category.setName("小学教育");
		categories.add(category);

		category = new Category();
		category.setOid(4);
		category.setName("课外读物");
		categories.add(category);

		category = new Category();
		category.setOid(5);
		category.setName("拼读练习");
		categories.add(category);

		category = new Category();
		category.setOid(6);
		category.setName("童谣歌曲");
		categories.add(category);

		category = new Category();
		category.setOid(7);
		category.setName("成人教育");
		categories.add(category);

		category = new Category();
		category.setOid(8);
		category.setName("教师资源");
		categories.add(category);

		category = new Category();
		category.setOid(9);
		category.setName("教具及配套设施");
		categories.add(category);

		books = new ArrayList<Book>();
		Book book = new Book();
		book.setOid(1);
		book.setPictureUrl("http://img.kooobao.cn/storage/sample.gif");
		book.setName("这是一本书");
		book.setCategory(sub1);
		book.setBrief("这是关于这本书的一个Brief，大家随便看看就可以了，不要太在意");
		book.setContent("<b>一二三四五六七</b>");
		books.add(book);

		book = new Book();
		book.setOid(2);
		book.setPictureUrl("http://img.kooobao.cn/storage/sample.gif");
		book.setName("这是另一本书");
		book.setCategory(sub2);
		book.setBrief("这是关于另一本书的一个Brief，大家随便看看就可以了，不要太在意。如果太长了怎么办呢？太长了就得戒掉一块");
		book.setContent("<b>气流无私三而已</b>");
		books.add(book);
	}

	@Override
	public List<Book> getLatestBooks(int limit) {
		return books;
	}

	@Override
	public List<Book> getHotBooks(int limit) {
		return getLatestBooks(limit);
	}

	@Override
	public Book saveBook(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeBook(Book book) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Book> findBooks(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> getRootCategories() {
		return rootCategories;
	}

	@Override
	public Category getCategory(long oid) {
		Category result = null;
		for (Category cate : getRootCategories()) {
			if ((result = find(cate, oid)) != null)
				return result;
		}
		return null;
	}

	protected Category find(Category root, long oid) {
		if (root.getOid() == oid)
			return root;
		if (root.isLeaf())
			return null;
		for (Category child : root.getChildren()) {
			Category result = find(child, oid);
			if (result != null)
				return result;
		}
		return null;
	}

	@Override
	public List<Book> getBooksUnderCategory(Category category) {
		if (category.isLeaf()) {
			List<Book> bs = new ArrayList<Book>();
			for (Book book : books) {
				if (book.getCategory().equals(category))
					bs.add(book);
			}
			return bs;
		}
		return null;
	}

	@Override
	public Book getBook(long bookOid) {
		for (Book book : books) {
			if (book.getOid() == bookOid)
				return book;
		}
		return null;
	}

	@Override
	public Book getFirstBookUnderCategory(Category category) {
		if (null == category)
			return books.get(0);
		for (Book book : books) {
			if (under(book.getCategory(), category))
				return book;
		}
		return null;
	}

	protected boolean under(Category me, Category parent) {
		if (me.equals(parent))
			return true;
		if (me.getParent() == null)
			return false;
		return under(me.getParent(), parent);
	}
}
