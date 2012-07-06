package com.kooobao.wsm.web.issue;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.kooobao.wsm.domain.dao.IssueDao;
import com.kooobao.wsm.domain.entity.issue.Issue;
import com.kooobao.wsm.domain.entity.issue.TrackLog;
import com.kooobao.wsm.web.AbstractBean;

public class ViewIssueBean extends AbstractBean {

	private long issueOid;

	private Issue issue;

	private TrackLog track;

	public ViewIssueBean() {
		super();
		this.track = new TrackLog();
	}

	@Override
	public void onPageLoad() {
		reload();
	}

	public void reload() {
		if (getIssue() != null && this.getIssueOid() == getIssue().getOid())
			return;
		loadIssue();
	}

	protected void loadIssue() {
		this.issue = getIssueDao().find(getIssueOid());
	}

	public String saveTrack() {
		track.setLogTime(new Date());
		track.setInputBy(getCurrentUserId());
		issue.addTrackLog(track);
		issue = getIssueDao().store(issue);

		track = new TrackLog();
		return "success";
	}

	public String saveIssue() {
		issue = getIssueDao().store(issue);

		FacesContext.getCurrentInstance().addMessage("solution",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "已保存", "已保存"));
		return "success";
	}

	private IssueDao issueDao;

	public IssueDao getIssueDao() {
		return issueDao;
	}

	public void setIssueDao(IssueDao issueDao) {
		this.issueDao = issueDao;
	}

	public long getIssueOid() {
		return issueOid;
	}

	public void setIssueOid(long issueOid) {
		this.issueOid = issueOid;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public TrackLog getTrack() {
		return track;
	}

	public void setTrack(TrackLog track) {
		this.track = track;
	}

}
