package com.kooobao.wechat.reply.game;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class Game {

	private int current;

	private List<Question> questions;

	private int correct;

	public Game() {
		questions = new ArrayList<Question>();
		current = 0;
		correct = 0;
	}

	public String nextQuestion() {
		if (current >= questions.size())
			return null;
		return MessageFormat.format("第{0}/{1}题: {2}", current + 1,
				questions.size(), questions.get(current).toString());
	}

	public String answer(String content) {
		Question question = questions.get(current++);
		if (question.isCorrect(content)) {
			correct++;
			return "恭喜你答对了\n";
		} else {
			return MessageFormat.format("很遗憾, 正确答案是{0}\n",
					question.getCorrectAnswer());
		}
	}

	public String result() {
		return MessageFormat.format("本次游戏共{0}道题，您答对了{1}道题", questions.size(),
				correct);
	}

	public void addQuestion(Question question) {
		questions.add(question);
	}

}
