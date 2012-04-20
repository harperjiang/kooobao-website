package com.kooobao.cws.domain.article;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("VIDEO")
public class Video extends Resource {

	public String getVideoId() {
		return getContent();
	}

	@Override
	public String getType() {
		return "VIDEO";
	}
}
