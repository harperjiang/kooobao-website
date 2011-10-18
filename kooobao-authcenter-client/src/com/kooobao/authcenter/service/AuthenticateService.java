package com.kooobao.authcenter.service;

public interface AuthenticateService {

	public Token login(String system, String user, String plainPass);

	public boolean validate(Token token);

}
