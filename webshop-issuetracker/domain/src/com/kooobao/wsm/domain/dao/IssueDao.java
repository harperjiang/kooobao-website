package com.kooobao.wsm.domain.dao;

import java.util.Date;
import java.util.List;

import com.kooobao.common.domain.dao.Dao;
import com.kooobao.wsm.domain.entity.issue.Issue;
import com.kooobao.wsm.domain.entity.issue.TroubleCase;

public interface IssueDao extends Dao<Issue> {

	public static class FindIssueBean {

		private Class<? extends Issue> category = TroubleCase.class;

		private String creator = null;

		private String follower = null;

		private String[] status = null;

		private Date fromDate = null;

		private Date toDate = null;

		private int lastingDays = -1;

		public FindIssueBean() {
			super();
		}

		public FindIssueBean(Class<? extends Issue> category, String creator,
				String follower, String[] status, int lastingDays) {
			super();
			this.category = category;
			this.creator = creator;
			this.follower = follower;
			this.status = status;
			this.lastingDays = lastingDays;
		}

		public FindIssueBean(Class<? extends Issue> category, String creator,
				String follower, String status, int lastingDays) {
			this(category, creator, follower, new String[] { status },
					lastingDays);
		}

		public Class<? extends Issue> getCategory() {
			return category;
		}

		public void setCategory(Class<? extends Issue> category) {
			this.category = category;
		}

		public String getCreator() {
			return creator;
		}

		public void setCreator(String creator) {
			this.creator = creator;
		}

		public String getFollower() {
			return follower;
		}

		public void setFollower(String follower) {
			this.follower = follower;
		}

		public String[] getStatus() {
			return status;
		}

		public void setStatus(String[] status) {
			this.status = status;
		}

		public int getLastingDays() {
			return lastingDays;
		}

		public void setLastingDays(int lastingDay) {
			this.lastingDays = lastingDay;
		}

		public Date getFromDate() {
			return fromDate;
		}

		public void setFromDate(Date fromDate) {
			this.fromDate = fromDate;
		}

		public Date getToDate() {
			return toDate;
		}

		public void setToDate(Date toDate) {
			this.toDate = toDate;
		}

	}

	public List<? extends Issue> findIssues(FindIssueBean bean);
}
