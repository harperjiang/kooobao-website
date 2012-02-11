package com.kooobao.fr.domain.dao;

import java.util.List;

import com.kooobao.common.domain.dao.Dao;
import com.kooobao.fr.domain.entity.Actor;
import com.kooobao.fr.domain.entity.Role;

public interface ActorDao extends Dao<Actor> {
	
	public List<Actor> getActors(Role type);
	
	public Actor getActor(String id);
}
