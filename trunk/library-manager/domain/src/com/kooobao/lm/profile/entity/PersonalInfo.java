package com.kooobao.lm.profile.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.SimpleEntity;

@Entity
@Table(name = "lm_visitor_info")
public class PersonalInfo extends SimpleEntity {
	@Column
	private String gender;
	@Column(name = "born_year")
	private String bornYear;
	@Column
	private String education;
	@Column(name = "kid_count")
	private int kidCount;
	@Column(name = "first_child_year")
	private String olderChildBornYear;
	@Column(name = "last_child_year")
	private String youngerChildBornYear;
	@Column
	private int like;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBornYear() {
		return bornYear;
	}

	public void setBornYear(String bornYear) {
		this.bornYear = bornYear;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public int getKidCount() {
		return kidCount;
	}

	public void setKidCount(int kidCount) {
		this.kidCount = kidCount;
	}

	public String getOlderChildBornYear() {
		return olderChildBornYear;
	}

	public void setOlderChildBornYear(String olderChildBornYear) {
		this.olderChildBornYear = olderChildBornYear;
	}

	public String getYoungerChildBornYear() {
		return youngerChildBornYear;
	}

	public void setYoungerChildBornYear(String youngerChildBornYear) {
		this.youngerChildBornYear = youngerChildBornYear;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

}
