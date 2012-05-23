package com.kooobao.lm.bizflow.dao;

import java.util.List;

import javax.persistence.NoResultException;

import com.kooobao.common.domain.dao.AbstractJpaDao;
import com.kooobao.lm.bizflow.entity.FavoriteRecord;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.profile.entity.Visitor;

public class JpaFavouriteDao extends AbstractJpaDao<FavoriteRecord> implements
		FavouriteDao {

	public FavoriteRecord find(Visitor visitor, Book book) {
		try {
			return getEntityManager()
					.createQuery(
							"select fr from FavoriteRecord fr where fr.visitor = :visitor and fr.favorite = :book",
							FavoriteRecord.class)
					.setParameter("visitor", visitor)
					.setParameter("book", book).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<FavoriteRecord> find(Visitor visitor) {
		return getEntityManager()
				.createQuery(
						"select fr from FavoriteRecord fr where fr.visitor = :visitor order by fr.createTime",
						FavoriteRecord.class).setParameter("visitor", visitor)
				.getResultList();
	}

}
