package com.kooobao.lm.article.dao;

import java.util.List;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.article.entity.News;

public class JpaNewsDao extends AbstractJpaDao<News> implements NewsDao {

	public List<News> findLatestNews(int limit) {
		return getEntityManager()
				.createQuery("select n from News n order by n.createTime desc",
						News.class).setMaxResults(limit).getResultList();
	}

	public PageSearchResult<News> search(String keyword, int from, int to) {
		String kw = "%" + keyword + "%";
		String queryBase = " from News n where n.title like :kw or n.content like :kw";
		String countQuery = "select count(n)" + queryBase;
		String itemQuery = "select n" + queryBase;
		Long count = getEntityManager().createQuery(countQuery, Long.class)
				.setParameter("kw", kw).getSingleResult();
		List<News> result = getEntityManager()
				.createQuery(itemQuery, News.class).setParameter("kw", kw)
				.setFirstResult(from).setMaxResults(to - from).getResultList();
		return new PageSearchResult<News>(count, result);
	}
}
