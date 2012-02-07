package com.kooobao.fr.web.dummy;

import java.util.ArrayList;
import java.util.List;

import com.kooobao.fr.domain.entity.Actor;
import com.kooobao.fr.domain.entity.Role;
import com.kooobao.fr.service.ActorService;

public class DummyActorService implements ActorService {

	public Actor getActor(String id) {
		Actor actor = new Actor();
		actor.setId(id);
		List<String> list = new ArrayList<String>();
		list.add(Role.MANAGER.name());
		actor.setRoles(list);
		return actor;
	}

}
