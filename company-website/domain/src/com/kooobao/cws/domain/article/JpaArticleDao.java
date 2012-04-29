package com.kooobao.cws.domain.article;

import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.cws.domain.book.BookDao;

public class JpaArticleDao extends AbstractJpaDao<Article> implements
		ArticleDao {

	@Override
	public List<Article> findByKeyword(String keyword) {
		Validate.isTrue(!StringUtils.isEmpty(keyword));
		return getEntityManager()
				.createQuery(
						"select a from Article a where upper(a.title) like :keyword or upper(a.articleAbstract) like :keyword",
						Article.class)
				.setParameter("keyword", "%" + keyword.toUpperCase() + "%")
				.getResultList();
	}

	@Override
	public <T extends Article> List<T> getLatest(Class<T> articleClass,
			int limit) {
		String type =null;
		try {
			type = (String) articleClass.getDeclaredMethod("getType",
					null).invoke(null, null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		Query query = getEntityManager()
				.createNativeQuery(
						"select * from cws_article a where a.type = ? order by a.create_time desc",
						articleClass).setParameter(1, type);
		if (limit != BookDao.UNLIMITED)
			query.setMaxResults(limit);
		return query.getResultList();
	}
}
