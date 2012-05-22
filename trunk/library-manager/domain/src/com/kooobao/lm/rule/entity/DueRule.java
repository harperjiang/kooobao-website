package com.kooobao.lm.rule.entity;

import java.util.Date;

import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.profile.entity.Visitor;

public class DueRule {

	static final long DAY = 86400000l;

	public Date getDueDate(Visitor v, Book book, Date borrowDate) {
		// 2 Weeks by default, additional 2 days for each level
		return new Date(borrowDate.getTime() + (v.getLevel() * 2 + 14) * DAY);
	}

}
