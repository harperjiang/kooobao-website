package com.kooobao.authcenter.service;

import java.util.UUID;

public class DummyAuthenticateService implements AuthenticateService {

	public Token login(String system, String user, String plainPass) {
		return new Token(system, user, UUID.randomUUID().toString());
	}

	public boolean validate(Token token) {
		return true;
	}

}
