package com.kooobao.authcenter.service;

public interface AuthenticateService {

	public Token login(String system, String user, String plainPass);

	public void logout(Token token);
	
	public boolean validate(Token token);

}
