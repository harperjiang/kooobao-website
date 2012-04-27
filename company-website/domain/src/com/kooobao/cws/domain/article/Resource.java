package com.kooobao.cws.domain.article;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("RESOURCE")
public class Resource extends Article {

	public static String getType() {
		return "RESOURCE";
	}
	public String getArticleType() {
		return getType();
	}
}
