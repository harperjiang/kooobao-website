package com.kooobao.wechat.reply.game;

public interface Question {

	public boolean isCorrect(String content);

	public String getCorrectAnswer();
}
