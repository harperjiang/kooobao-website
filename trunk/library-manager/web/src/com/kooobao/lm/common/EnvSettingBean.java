package com.kooobao.lm.common;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.kooobao.common.web.bean.AbstractBean;

@ManagedBean(name = "envSettingBean")
@ApplicationScoped
public class EnvSettingBean extends AbstractBean {

	private String datePattern = "yyyy-MM-dd";

	public String getDatePattern() {
		return datePattern;
	}

	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}

	private String timeZone = "Asia/Shanghai";

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
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

	private String websiteLoc;

	public String getWebsiteLoc() {
		return websiteLoc;
	}

	public void setWebsiteLoc(String websiteLoc) {
		this.websiteLoc = websiteLoc;
	}

	private int baseBorrowDay = 14;

	public int getBaseBorrowDay() {
		return baseBorrowDay;
	}

	public void setBaseBorrowDay(int baseBorrowDay) {
		this.baseBorrowDay = baseBorrowDay;
	}

}
