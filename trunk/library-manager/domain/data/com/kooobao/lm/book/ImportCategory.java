package com.kooobao.lm.book;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.kooobao.lm.book.entity.Category;

public class ImportCategory {

	public static void main(String[] args) {
		List<Category> categories = new DummyBookService().getRootCategories();
		EntityManager em = Persistence.createEntityManagerFactory(
				"library-manager-domain").createEntityManager();
		em.getTransaction().begin();
		for (Category category : categories)
			em.persist(category);
		em.getTransaction().commit();
	}
}
