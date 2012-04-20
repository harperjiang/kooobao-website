package com.kooobao.cws.domain.article;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("RESOURCE")
public class Resource extends Article {

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

}
