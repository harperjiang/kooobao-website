package com.kooobao.cws.domain.article;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("NEWS")
public class News extends Article {

	@Override
	public String getType() {
		return "NEWS";
	}

}
