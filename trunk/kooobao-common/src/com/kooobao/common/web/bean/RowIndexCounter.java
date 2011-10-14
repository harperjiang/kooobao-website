package com.kooobao.common.web.bean;

public class RowIndexCounter {

	int rowIndex = 0;

	public void reset() {
		rowIndex = 0;
	}

	public int getRowIndex() {
		return rowIndex;
	}

	public String getNext() {
		rowIndex++;
		return "";
	}
}
