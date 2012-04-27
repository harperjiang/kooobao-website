package com.kooobao.cws.domain.article;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("NEWS")
public class News extends Article {

	public static String getType() {
		return "NEWS";
	}

	public String getArticleType() {
		return getType();
	}
}
