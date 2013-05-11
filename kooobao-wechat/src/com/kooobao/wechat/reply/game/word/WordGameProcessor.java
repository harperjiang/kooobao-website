package com.kooobao.wechat.reply.game.word;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

import com.kooobao.common.util.ResourceLoader;
import com.kooobao.wechat.msg.IncomingMessage;
import com.kooobao.wechat.msg.IncomingType;
import com.kooobao.wechat.msg.OutgoingMessage;
import com.kooobao.wechat.msg.TextIncoming;
import com.kooobao.wechat.msg.TextOutgoing;
import com.kooobao.wechat.reply.AbstractSessionProcessor;
import com.kooobao.wechat.reply.game.Game;

public class WordGameProcessor extends AbstractSessionProcessor<WordSession> {

	private String chooseLevel;

	public WordGameProcessor() {
		try {
			chooseLevel = ResourceLoader
					.loadAsString("/com/kooobao/wechat/reply/game/word/Level");
		} catch (Exception e1) {
			logger.warn("Exception when loading resource", e1);
		}
	}

	@Override
	protected OutgoingMessage processWithSession(WordSession session,
			IncomingMessage incoming) {
		TextIncoming text = (TextIncoming) incoming;
		if ("退出".equals(text.getContent()))
			return null;
		switch (session.getState()) {
		case WordSession.STATE_LVLCHS:
			try {
				session.setLevel(Integer.parseInt(text.getContent()) - 1);
				session.setState(WordSession.STATE_TEST);
				session.setGame(createGame(session.getLevel()));

				return new TextOutgoing(incoming, session.getGame()
						.nextQuestion());
			} catch (Exception e) {
				return new TextOutgoing(incoming, "欢迎进入酷堡背单词, 请选择难度级别。\n"
						+ chooseLevel);
			}
		case WordSession.STATE_TEST:
			StringBuilder sb = new StringBuilder();
			sb.append(session.getGame().answer(text.getContent()));

			String nextq = session.getGame().nextQuestion();
			if (StringUtils.isEmpty(nextq)) {
				sb.append(session.getGame().result()).append("\n");
				sb.append("输入“重来”重新开始一局游戏，\n输入其他内容退出");
				session.setState(WordSession.STATE_END);
			} else {
				sb.append(nextq);
			}
			return new TextOutgoing(incoming, sb.toString());
		case WordSession.STATE_END:
			if ("重来".equals(text.getContent())) {
				return new TextOutgoing(incoming, chooseLevel);
			}
			return null;
		default:
			return null;
		}
	}

	@Override
	public IncomingType[] acceptTypes() {
		return new IncomingType[] { IncomingType.text };
	}

	@Override
	public boolean accept(IncomingMessage incoming) {
		return incoming instanceof TextIncoming
				&& ((TextIncoming) incoming).getContent().contains("单词");
	}

	@Override
	protected WordSession createSession() {
		return new WordSession();
	}

	private static int GAME_SIZE = 10;

	private static Game createGame(int level) {
		Game game = new Game();
		List<Word> words = wordList.get(level);
		for (int i = 0; i < GAME_SIZE; i++)
			game.addQuestion(new WordQuestion(words.get(random.nextInt(words
					.size())), words.get(random.nextInt(words.size())), words
					.get(random.nextInt(words.size())), words.get(random
					.nextInt(words.size())), random.nextInt(4)));
		return game;
	}

	protected static Random random = new Random(System.currentTimeMillis());

	private static List<List<Word>> wordList;

	private static List<String> wordLevel;

	{
		readList();
	}

	private static void readList() {
		wordLevel = new ArrayList<String>();
		wordList = new ArrayList<List<Word>>();

		try {
			String levelstr = ResourceLoader
					.loadAsString("com/kooobao/wechat/reply/game/word/Level");
			String[] levels = levelstr.split("\n");
			for (String level : levels) {
				wordLevel.add(level.split("\t")[1]);
			}
			for (int i = 0; i < wordLevel.size(); i++) {
				wordList.add(new ArrayList<Word>());
				BufferedReader br = new BufferedReader(
						new InputStreamReader(
								Thread.currentThread()
										.getContextClassLoader()
										.getResourceAsStream(
												"com/kooobao/wechat/reply/game/word/GRE")));
				String line = null;
				while ((line = br.readLine()) != null) {
					Word word = new Word();
					String[] content = line.split("\t");
					word.setEnglish(content[0]);
					word.setChinese(content[1]);
					wordList.get(i).add(word);
				}
				br.close();
			}
		} catch (Exception e) {
			LoggerFactory.getLogger(WordGameProcessor.class).error(
					"Exception while reading word list");
		}
	}
}
