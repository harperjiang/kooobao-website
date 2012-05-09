package com.kooobao.lm.bizflow;

import com.kooobao.common.domain.entity.VersionEntity;
import com.kooobao.lm.book.Book;
import com.kooobao.lm.profile.Visitor;

public class FavoriteRecord extends VersionEntity {

	private Visitor visitor;

	private Book favorite;

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	public Book getFavorite() {
		return favorite;
	}

	public void setFavorite(Book favorite) {
		this.favorite = favorite;
	}

}
