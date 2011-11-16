package com.kooobao.wsm.web.issue;

import com.kooobao.wsm.domain.dao.IssueDao;
import com.kooobao.wsm.domain.dao.IssueDao.FindIssueBean;
import com.kooobao.wsm.domain.entity.issue.TroubleCase;
import com.kooobao.wsm.web.AbstractBean;
import com.kooobao.wsm.web.Utilities;

public class IssueSummaryBean extends AbstractBean {

	static final String[] UNSOLVED_STATUS = new String[] {
			TroubleCase.Status.NEW.name(),
			TroubleCase.Status.WAIT_CUSTOMER.name(),
			TroubleCase.Status.WAIT_SUPPORT.name() };

	public int getMyIssueCount() {
		return getIssueDao().findIssues(
				new FindIssueBean(TroubleCase.class, null, getCurrentUserId(),
						UNSOLVED_STATUS, -1)).size();
	}

	public int getUnsolvedIssueCount() {
		return getIssueDao().findIssues(
				new FindIssueBean(TroubleCase.class, null, null,
						UNSOLVED_STATUS, -1)).size();
	}

	public int getNewIssueCount() {
		return getIssueDao().findIssues(
				new FindIssueBean(TroubleCase.class, null, null,
						new String[] { TroubleCase.Status.NEW.name() }, -1))
				.size();
	}

	public int getOver3DayIssueCount() {
		return getIssueDao().findIssues(
				new FindIssueBean(TroubleCase.class, null, null,
						UNSOLVED_STATUS, 3)).size();
	}

	public int getOver7DayIssueCount() {
		return getIssueDao().findIssues(
				new FindIssueBean(TroubleCase.class, null, null,
						UNSOLVED_STATUS, 7)).size();
	}

	
	public int getDay7IssueCount() {
		FindIssueBean find = new FindIssueBean(TroubleCase.class, null, null,
				(String[]) null, -1);
		find.setFromDate(Utilities.offset(-7));
		find.setToDate(Utilities.dayEnd());
		return getIssueDao().findIssues(find).size();
	}
	
	public int getMonthIssueCount() {
		FindIssueBean find = new FindIssueBean(TroubleCase.class, null, null,
				(String[]) null, -1);
		find.setFromDate(Utilities.offset(-30));
		find.setToDate(Utilities.dayEnd());
		return getIssueDao().findIssues(find).size();
	}

	public String viewMyIssue() {
		ViewIssueListBean viewIssueListBean = findBean("viewIssueList");
		viewIssueListBean.reset();
		viewIssueListBean.getFindBean().setFollower(getCurrentUserId());
		viewIssueListBean.getFindBean().setStatus(UNSOLVED_STATUS);
		return "success";
	}

	public String viewNewIssue() {
		ViewIssueListBean viewIssueListBean = findBean("viewIssueList");
		viewIssueListBean.reset();
		viewIssueListBean.getFindBean().setFollower(null);
		viewIssueListBean.getFindBean().setStatus(
				new String[] { TroubleCase.Status.NEW.name() });
		return "success";
	}

	public String viewUnsolvedIssue() {
		ViewIssueListBean viewIssueListBean = findBean("viewIssueList");
		viewIssueListBean.reset();
		viewIssueListBean.getFindBean().setStatus(UNSOLVED_STATUS);
		return "success";
	}

	public String viewOver3DayIssue() {
		ViewIssueListBean viewIssueListBean = findBean("viewIssueList");
		viewIssueListBean.reset();
		viewIssueListBean.getFindBean().setStatus(UNSOLVED_STATUS);
		viewIssueListBean.getFindBean().setLastingDays(3);
		return "success";
	}

	public String viewOver7DayIssue() {
		ViewIssueListBean viewIssueListBean = findBean("viewIssueList");
		viewIssueListBean.reset();
		viewIssueListBean.getFindBean().setStatus(UNSOLVED_STATUS);
		viewIssueListBean.getFindBean().setLastingDays(7);
		return "success";
	}

	public String view7DayIssue() {
		ViewIssueListBean viewIssueListBean = findBean("viewIssueList");
		viewIssueListBean.reset();
		viewIssueListBean.getFindBean().setFromDate(Utilities.offset(-7));
		viewIssueListBean.getFindBean().setToDate(Utilities.dayEnd());
		return "success";
	}

	public String viewMonthIssue() {
		ViewIssueListBean viewIssueListBean = findBean("viewIssueList");
		viewIssueListBean.reset();
		viewIssueListBean.getFindBean().setFromDate(Utilities.offset(-30));
		viewIssueListBean.getFindBean().setToDate(Utilities.dayEnd());
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
