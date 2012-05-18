package com.kooobao.lm.bizflow;

import javax.servlet.http.HttpServletRequest;

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
			getTransactionService().addFavorite(visitor, bookOid);
			return true;
		} catch (Exception e) {
			// TODO log
			e.printStackTrace();
			return false;
		}
	}

	private ProfileService profileService;

	private TransactionService transactionService;

	public ProfileService getProfileService() {
		return profileService;
	}

	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}

	public TransactionService getTransactionService() {
		return transactionService;
	}

	public void setTransactionService(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

}
