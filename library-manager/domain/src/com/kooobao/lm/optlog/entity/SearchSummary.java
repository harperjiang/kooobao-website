package com.kooobao.lm.optlog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.SimpleEntity;

@Entity
@Table(name="lm_optlog_searchsum")
public class SearchSummary extends SimpleEntity {

	@Column(name="keyword")
	private String keyword;
	
	@Column(name="search_count")
	private int searchCount;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getSearchCount() {
		return searchCount;
	}

	public void setSearchCount(int searchCount) {
		this.searchCount = searchCount;
	}
}
