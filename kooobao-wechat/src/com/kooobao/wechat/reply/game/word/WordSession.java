package com.kooobao.wechat.reply.game.word;

import com.kooobao.wechat.reply.AbstractSession;
import com.kooobao.wechat.reply.game.Game;

public final class WordSession extends AbstractSession {

	public WordSession() {
		// 5min
		super(300000);
	}

	public static final int STATE_LVLCHS = 0;

	public static final int STATE_TEST = 1;

	public static final int STATE_END = 2;

	private int level;

	private int state;

	private Game game;

	public Game getGame() {
		return game;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}