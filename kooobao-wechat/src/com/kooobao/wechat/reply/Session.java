package com.kooobao.wechat.reply;

public interface Session {

	public long getLastUpdate();

	public long getTimeout();

	public void mark(boolean inuse);

	public boolean inuse();
}
