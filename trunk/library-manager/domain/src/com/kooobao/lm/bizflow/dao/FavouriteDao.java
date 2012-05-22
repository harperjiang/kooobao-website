package com.kooobao.lm.bizflow.dao;

import java.util.List;

import com.kooobao.common.domain.dao.Dao;
import com.kooobao.lm.bizflow.entity.FavoriteRecord;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.profile.entity.Visitor;

public interface FavouriteDao extends Dao<FavoriteRecord> {

	FavoriteRecord find(Visitor visitor, Book book);

	List<FavoriteRecord> find(Visitor visitor);

}
