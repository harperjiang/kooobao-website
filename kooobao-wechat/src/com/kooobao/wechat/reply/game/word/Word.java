package com.kooobao.wechat.reply.game.word;

public class Word {

	private String english;

	private String chinese;

	public Word() {

	}

	public Word(String e, String c) {
		this.english = e;
		this.chinese = c;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getChinese() {
		return chinese;
	}

	public void setChinese(String chinese) {
		this.chinese = chinese;
	}

}