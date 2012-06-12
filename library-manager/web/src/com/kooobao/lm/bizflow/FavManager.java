package com.kooobao.lm.bizflow;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;

import com.kooobao.authcenter.listener.LoginAuthorizer;
import com.kooobao.authcenter.service.Token;
import com.kooobao.lm.profile.ProfileService;
import com.kooobao.lm.profile.entity.Visitor;

public class FavManager {

	public boolean addToFav(long bookOid, HttpServletRequest request) {
		Token token = LoginAuthorizer.getToken(request.getSession(), "lm");
		if (null == token)
			return false;
		try {
			Visitor visitor = getProfileService().getVisitor(token.getUserId());
			getFavouriteService().addFavorite(visitor, bookOid);
			return true;
		} catch (Exception e) {
			LoggerFactory.getLogger(getClass()).warn("Failed to add to favorite", e);
			return false;
		}
	}

	private ProfileService profileService;

	private FavouriteService favouriteService;

	public ProfileService getProfileService() {
		return profileService;
	}

	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}

	public FavouriteService getFavouriteService() {
		return favouriteService;
	}

	public void setFavouriteService(FavouriteService favouriteService) {
		this.favouriteService = favouriteService;
	}

}
