package com.kooobao.cws.domain.article;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("VIDEO")
public class Video extends Article {

	public String getVideoId() {
		return getContent();
	}

	public static String getType() {
		return "VIDEO";
	}

	public String getArticleType() {
		return getType();
	}
}
