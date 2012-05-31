package com.kooobao.lm.article.dao;

import java.util.List;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.lm.article.entity.News;

public class JpaNewsDao extends AbstractJpaDao<News> implements NewsDao {

	public List<News> findLatestNews(int limit) {
		return getEntityManager()
				.createQuery("select n from News n order by n.createTime desc",
						News.class).setMaxResults(limit).getResultList();
	}

}
