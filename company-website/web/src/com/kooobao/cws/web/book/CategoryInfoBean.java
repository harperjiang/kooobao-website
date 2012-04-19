package com.kooobao.cws.web.book;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.common.web.bean.JSFStartupAware;
import com.kooobao.cws.domain.book.Category;
import com.kooobao.cws.service.book.BookService;

public class CategoryInfoBean extends AbstractBean implements JSFStartupAware {

	private List<Category> roots;

	private List<SelectItem> leaves;

	public CategoryInfoBean() {
	}

	public void init() {
		roots = getBookService().getRootCategories();
		leaves = new ArrayList<SelectItem>();
		for (Category category : roots) {
			leaves.add(new SelectItem(category, category.getLayeredName()));
		}
		for (int i = 0; i < leaves.size(); i++) {
			Category cat = (Category) leaves.get(i).getValue();
			if (!cat.isLeaf()) {
				Category current = (Category) leaves.remove(i).getValue();
				for (Category category : current.getChildren()) {
					leaves.add(new SelectItem(category, category
							.getLayeredName()));
				}
				i--;
			}
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	public List<Category> getRoots() {
		return roots;
	}

	public List<SelectItem> getLeaves() {
		return leaves;
	}

	private BookService bookService;

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

}
