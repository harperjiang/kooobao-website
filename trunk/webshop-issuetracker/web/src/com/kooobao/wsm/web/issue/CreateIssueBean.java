package com.kooobao.wsm.web.issue;

import com.kooobao.wsm.domain.dao.IssueDao;
import com.kooobao.wsm.domain.entity.issue.Issue;
import com.kooobao.wsm.domain.entity.issue.TroubleCase;
import com.kooobao.wsm.web.AbstractBean;

public class CreateIssueBean extends AbstractBean {

	private Issue issue;

	public CreateIssueBean() {
		super();
		this.issue = new TroubleCase();
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public String save() {
		getIssue().setCreatorId(getCurrentUserId());
		setIssue(getIssueDao().store(getIssue()));
		CreateIssueSuccessBean cisb = findBean("createIssueSuccess");
		cisb.setIssueOid(getIssue().getOid());
		return "success";
	}

	private IssueDao issueDao;

	public IssueDao getIssueDao() {
		return issueDao;
	}

	public void setIssueDao(IssueDao issueDao) {
		this.issueDao = issueDao;
	}

}
