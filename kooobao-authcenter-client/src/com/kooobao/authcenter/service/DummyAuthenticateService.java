package com.kooobao.authcenter.service;

public class DummyAuthenticateService implements AuthenticateService {

	public Token login(String user, String plainPass) {
		return new Token();
	}

	public boolean validate(Token token) {
		return true;
	}

}
