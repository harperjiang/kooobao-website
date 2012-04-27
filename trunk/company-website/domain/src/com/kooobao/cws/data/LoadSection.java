package com.kooobao.cws.data;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class LoadSection {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		EntityManager em = Persistence.createEntityManagerFactory(
				"company-website-domain").createEntityManager();
		em.getTransaction().begin();

		em.createNativeQuery("truncate table cws_article_section")
				.executeUpdate();

		BufferedReader br = new BufferedReader(
				new InputStreamReader(
						LoadSection.class
								.getResourceAsStream("/com/kooobao/cws/data/section.txt")));
		String line = null;
		int count = 1;
		while ((line = br.readLine()) != null) {
			String[] val = line.split("\\s+");

			em.createNativeQuery(
					"insert into cws_article_section (obj_id, type, name,create_time,obj_version) values(?,?,?,?,?)")
					.setParameter(1, count++).setParameter(2, val[0])
					.setParameter(3, val[1]).setParameter(4, new Date())
					.setParameter(5, 1).executeUpdate();
		}
		br.close();

		em.getTransaction().commit();
	}
}
