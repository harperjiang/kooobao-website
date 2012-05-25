package com.kooobao.lm.common;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.kooobao.common.web.bean.AbstractBean;

@ManagedBean(name = "envSettingBean")
@ApplicationScoped
public class EnvSettingBean extends AbstractBean {

	private String datePattern;

	public String getDatePattern() {
		return datePattern;
	}

	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}

	private String moneyUnit = "豆丁";

	public String getMoneyUnit() {
		return moneyUnit;
	}

	public void setMoneyUnit(String moneyUnit) {
		this.moneyUnit = moneyUnit;
	}

	private boolean rewriteUrl;

	public boolean isRewriteUrl() {
		return rewriteUrl;
	}

	public void setRewriteUrl(boolean rewriteUrl) {
		this.rewriteUrl = rewriteUrl;
	}

}
