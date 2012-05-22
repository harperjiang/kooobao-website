package com.kooobao.lm.bizflow.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.VersionEntity;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.profile.entity.Visitor;

@Entity
@Table(name = "lm_tran_fav_rec")
public class FavoriteRecord extends VersionEntity {

	@OneToOne
	@JoinColumn(name = "visitor")
	private Visitor visitor;

	@OneToOne
	@JoinColumn(name = "fav_book")
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
