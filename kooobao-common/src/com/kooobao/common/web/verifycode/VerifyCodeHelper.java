package com.kooobao.common.web.verifycode;

import javax.servlet.http.HttpSession;

public class VerifyCodeHelper {

	public boolean verify(HttpSession session, String input) {
		Object key = session.getAttribute(VerifyKey.KEY);
		if (null == key || !(key instanceof VerifyKey))
			return false;
		VerifyKey vkey = (VerifyKey) key;
		return VerifyCodeManager.getInstance().verify(vkey, input);
	}
}
