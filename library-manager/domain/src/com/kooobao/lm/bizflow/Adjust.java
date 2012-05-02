package com.kooobao.lm.bizflow;

import com.kooobao.common.domain.entity.SimpleEntity;
import com.kooobao.lm.book.Book;

public class Adjust extends SimpleEntity {

	private Book book;

	private int adjustCount;

	private Operation operation;

}
