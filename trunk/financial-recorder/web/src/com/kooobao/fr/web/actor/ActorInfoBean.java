package com.kooobao.fr.web.actor;

import javax.persistence.NoResultException;

import com.kooobao.authcenter.web.bean.LoginBean;
import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.fr.domain.entity.Actor;
import com.kooobao.fr.domain.entity.Role;
import com.kooobao.fr.service.ActorService;

public class ActorInfoBean extends AbstractBean {

	private Actor actor;

	public ActorInfoBean() {

	}

	protected Actor getActor() {
		LoginBean loginBean = findBean("loginBean");
		if (null == loginBean || !loginBean.isLoggedIn())
			throw new IllegalStateException("Not logged in");
		if (null == actor) {
			String currentUserId = loginBean.getUserId();
			ActorService actorService = findBean("actorService");
			try {
				actor = actorService.getActor(currentUserId);
			} catch (NoResultException e) {
				throw new IllegalStateException("No such actor:"
						+ currentUserId);
			}
		} else {
			if (actor.getId().equals(loginBean.getUserId())) {
				actor = null;
				return getActor();
			}
		}
		return actor;
	}

	public boolean isManager() {
		return getActor().getRoles().contains(Role.MANAGER.name());
	}

	public boolean isOperator() {
		return getActor().getRoles().contains(Role.OPERATOR.name());
	}

	public boolean isTeller() {
		return getActor().getRoles().contains(Role.TELLER.name());
	}
}
