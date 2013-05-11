package com.kooobao.wechat.reply.game;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.kooobao.wechat.reply.game.word.Word;
import com.kooobao.wechat.reply.game.word.WordQuestion;

public class QuestionTest {

	@Test
	public void testIsCorrect() {
		WordQuestion question = new WordQuestion(new Word("1", "a"),
				new Word("2", "b"), new Word("3", "c"), new Word("4", "d"), 3);
		assertTrue(question.isCorrect("d"));
		assertTrue(question.isCorrect("D"));
		assertTrue(!question.isCorrect("c"));
		assertTrue(!question.isCorrect("C"));
		assertTrue(!question.isCorrect("b"));
		assertTrue(!question.isCorrect("B"));
		assertTrue(!question.isCorrect("a"));
		assertTrue(!question.isCorrect("A"));
	}

}
