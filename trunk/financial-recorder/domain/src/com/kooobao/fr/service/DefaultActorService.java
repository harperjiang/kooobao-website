package com.kooobao.fr.service;

import com.kooobao.fr.domain.dao.ActorDao;
import com.kooobao.fr.domain.entity.Actor;

public class DefaultActorService implements ActorService {

	public Actor getActor(String id) {
		return getActorDao().getActor(id);
	}

	private ActorDao actorDao;

	public ActorDao getActorDao() {
		return actorDao;
	}

	public void setActorDao(ActorDao actorDao) {
		this.actorDao = actorDao;
	}
	
}
