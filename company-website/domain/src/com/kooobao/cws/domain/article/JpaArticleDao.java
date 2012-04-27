package com.kooobao.cws.domain.article;

import java.util.List;

import javax.persistence.TypedQuery;

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
						"select a from Article a where upper(a.name) like :keyword or upper(a.articleAbstract) like :keyword",
						Article.class)
				.setParameter("keyword", "%" + keyword.toUpperCase() + "%")
				.getResultList();
	}

	@Override
	public <T extends Article> List<T> getLatest(Class<T> articleClass,
			int limit) {
		try {
			String type = (String) articleClass.getDeclaredMethod("getType",
					null).invoke(null, null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		TypedQuery<T> query = getEntityManager()
				.createQuery(
						"select a from Article a where a.type = :type order by a.createTime desc",
						articleClass).setParameter("type", "type");
		if (limit != BookDao.UNLIMITED)
			query.setMaxResults(limit);
		return query.getResultList();
	}
}
