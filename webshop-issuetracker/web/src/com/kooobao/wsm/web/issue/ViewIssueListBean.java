package com.kooobao.wsm.web.issue;

import java.util.List;

import javax.faces.component.UIData;

import com.kooobao.wsm.domain.dao.IssueDao;
import com.kooobao.wsm.domain.dao.IssueDao.FindIssueBean;
import com.kooobao.wsm.domain.entity.issue.Issue;
import com.kooobao.wsm.web.AbstractBean;
import com.kooobao.wsm.web.Utilities;

public class ViewIssueListBean extends AbstractBean {

	@Override
	public void onPageLoad() {
		reload();
	}

	public String reload() {
		// Update Data with new search criteria
		if (null != getFindBean().getToDate())
			getFindBean()
					.setToDate(Utilities.dayEnd(getFindBean().getToDate()));
		setIssues(getIssueDao().findIssues(getFindBean()));
		return "success";
	}

	/**
	 * Reset the query status
	 */
	public void reset() {
		setFindBean(new FindIssueBean());
		setIssues(null);
	}

	public String view() {
		UIData dataTable = (UIData) getComponent("dataTable");
		Issue select = (Issue) dataTable.getRowData();
		ViewIssueBean viewIssueBean = findBean("viewIssue");
		viewIssueBean.setIssueOid(select.getOid());
		return "success";
	}

	private IssueDao issueDao;

	public IssueDao getIssueDao() {
		return issueDao;
	}

	public void setIssueDao(IssueDao issueDao) {
		this.issueDao = issueDao;
	}

	private FindIssueBean findBean;

	private List<? extends Issue> issues;

	public List<? extends Issue> getIssues() {
		return issues;
	}

	public void setIssues(List<? extends Issue> issues) {
		this.issues = issues;
	}

	public FindIssueBean getFindBean() {
		if (null == findBean)
			findBean = new FindIssueBean();
		return findBean;
	}

	public void setFindBean(FindIssueBean findBean) {
		this.findBean = findBean;
	}

}
