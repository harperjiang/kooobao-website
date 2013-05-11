package com.kooobao.wechat.reply.game.word;

import org.apache.commons.lang.StringUtils;

import com.kooobao.wechat.reply.game.Question;

public class WordQuestion implements Question {

	private Word[] words;

	private int choice;

	public WordQuestion(Word word1, Word word2, Word word3, Word word4,
			int choice) {
		words = new Word[4];
		words[0] = word1;
		words[1] = word2;
		words[2] = word3;
		words[3] = word4;

		this.choice = choice;
	}

	public boolean isCorrect(String content) {
		if (StringUtils.isEmpty(content))
			return false;
		String uppercase = String.valueOf((char) ('A' + choice));
		String lowercase = String.valueOf((char) ('a' + choice));
		return uppercase.equals(content) || lowercase.equals(content);
	}

	public String getCorrectAnswer() {
		return String.valueOf((char) ('A' + choice));
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(words[choice].getEnglish()).append(":\n\n");
		for (int i = 0; i < words.length; i++) {
			sb.append((char) ('A' + i)).append(":");
			sb.append(words[i].getChinese()).append("\n");
		}
		return sb.toString();
	}
}
