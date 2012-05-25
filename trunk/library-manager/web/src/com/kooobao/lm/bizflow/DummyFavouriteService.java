package com.kooobao.lm.bizflow;

import java.util.List;

import com.kooobao.lm.bizflow.entity.FavoriteRecord;
import com.kooobao.lm.profile.entity.Visitor;

public class DummyFavouriteService implements FavouriteService {

	@Override
	public List<FavoriteRecord> searchFavoriteRecords(Visitor visitor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFavorite(Visitor visitor, long bookOid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteFavorite(Visitor visitor, long bookOid) {
		// TODO Auto-generated method stub

	}

}
