package com.kooobao.cws.web.common;

import com.kooobao.common.web.bean.AbstractBean;

public class EnvConfigBean extends AbstractBean {

	private boolean rewriteUrl = true;

	public boolean isRewriteUrl() {
		return rewriteUrl;
	}

	public void setRewriteUrl(boolean rewriteUrl) {
		this.rewriteUrl = rewriteUrl;
	}
	
	
}
