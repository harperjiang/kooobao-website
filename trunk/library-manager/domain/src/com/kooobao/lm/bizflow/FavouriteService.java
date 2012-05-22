package com.kooobao.lm.bizflow;

import java.util.List;

import com.kooobao.lm.bizflow.entity.FavoriteRecord;
import com.kooobao.lm.profile.entity.Visitor;

public interface FavouriteService {
	public List<FavoriteRecord> searchFavoriteRecords(Visitor visitor);

	public void addFavorite(Visitor visitor, long bookOid);

	public void deleteFavorite(Visitor visitor, long bookOid);

}
