package com.kooobao.cws.domain.article;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.kooobao.common.domain.dao.AbstractMemoryDao;

public class MemoryArticleDao extends AbstractMemoryDao<Article> implements
		ArticleDao {

	@Override
	public List<Article> findByKeyword(String keyword) {
		List<Article> result = new ArrayList<Article>();
		for (Article article : getStorage().values()) {
			if ((!StringUtils.isEmpty(article.getTitle()) && article.getTitle()
					.contains(keyword))
					|| (!StringUtils.isEmpty(article.getArticleAbstract()) && article
							.getArticleAbstract().contains(keyword)))
				result.add(article);
		}
		return result;
	}

	@Override
	public <T extends Article> List<T> getLatest(Class<T> articleClass,
			int limit) {
		Comparator<Article> dateComparator = new Comparator<Article>() {
			@Override
			public int compare(Article o1, Article o2) {
				return -o1.getCreateTime().compareTo(o2.getCreateTime());
			}
		};
		List<Article> vals = new ArrayList<Article>();
		for (Article article : getStorage().values()) {
			if (article.getClass() == articleClass)
				vals.add(article);
		}
		Collections.sort(vals, dateComparator);
		return (List<T>) vals.subList(0, limit);
	}
}
