package com.kooobao.cws.data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.springframework.util.CollectionUtils;

import com.kooobao.cws.domain.book.Category;

public class LoadCategory {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(
						LoadCategory.class
								.getResourceAsStream("/com/kooobao/cws/data/category.txt")));
		int oidCounter = 1;
		List<Category> categories = new ArrayList<Category>();
		Stack<Category> stack = new Stack<Category>();
		String line = null;
		while ((line = br.readLine()) != null) {
			Category category = new Category();
			category.setOid(oidCounter++);
			category.setCreateTime(new Date());
			category.setName(line.trim());
			int count = 0;
			while (line.charAt(count) == '\t')
				count++;
			if (count == 0)
				categories.add(category);
			while (count < stack.size()) {
				stack.pop();
			}
			if (stack.size() != 0)
				stack.peek().addChild(category);
			stack.push(category);
		}
		// Set Sequence
		for (int i = 0; i < categories.size(); i++) {
			categories.get(i).setSequence(i);
		}
		for (Category category : categories) {
			if (!CollectionUtils.isEmpty(category.getChildren())) {
				for (int i = 0; i < category.getChildren().size(); i++) {
					category.getChildren().get(i).setSequence(i);
				}
			}
		}
		br.close();

		EntityManager em = Persistence.createEntityManagerFactory(
				"company-website-domain").createEntityManager();
		em.getTransaction().begin();
		
		for (Category category : categories)
			em.persist(category);

		em.getTransaction().commit();
	}

}
