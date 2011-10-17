package com.kooobao.wsm.web.issue;

import com.kooobao.wsm.web.AbstractBean;

public class CreateIssueSuccessBean extends AbstractBean {

	private long issueOid;

	public long getIssueOid() {
		return issueOid;
	}

	public void setIssueOid(long issueOid) {
		this.issueOid = issueOid;
	}

	public String viewCreated() {
		ViewIssueBean viewIssue = findBean("viewIssue");
		viewIssue.setIssueOid(getIssueOid());
		return "success";
	}

	public String newIssue() {
		return "success";
	}
}
