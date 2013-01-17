package com.kooobao.ecom.user;

import java.util.UUID;

import com.kooobao.authcenter.service.AuthenticateService;
import com.kooobao.authcenter.service.Token;

public class DummyAuthenticateService implements AuthenticateService {

	@Override
	public Token login(String system, String user, String plainPass) {
		if ("failtest".equals(user))
			return null;
		Token token = new Token("ecom", "user", UUID.randomUUID().toString());
		return token;
	}

	@Override
	public void logout(Token token) {

	}

	@Override
	public boolean validate(Token token) {
		return true;
	}

}
