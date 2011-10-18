package com.kooobao.authcenter.service;

import org.apache.el.parser.Token;

public interface AuthenticateService {

	public Token login(String system, String user, String plainPass);

	public boolean validate(Token token);

}
