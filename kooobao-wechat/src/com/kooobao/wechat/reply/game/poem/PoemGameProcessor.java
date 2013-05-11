package com.kooobao.wechat.reply.game.poem;

import com.kooobao.wechat.msg.IncomingMessage;
import com.kooobao.wechat.msg.IncomingType;
import com.kooobao.wechat.msg.OutgoingMessage;
import com.kooobao.wechat.msg.TextIncoming;
import com.kooobao.wechat.reply.AbstractSessionProcessor;

public class PoemGameProcessor extends AbstractSessionProcessor<PoemSession> {

	@Override
	public IncomingType[] acceptTypes() {
		return new IncomingType[] { IncomingType.text };
	}

	@Override
	public boolean accept(IncomingMessage incoming) {
		return incoming instanceof TextIncoming
				&& ((TextIncoming) incoming).getContent().equals("诗词");
	}

	@Override
	protected OutgoingMessage processWithSession(PoemSession session,
			IncomingMessage incoming) {
		TextIncoming text = (TextIncoming) incoming;
		if ("退出".equals(text.getContent()))
			return null;
		return null;
	}

	@Override
	protected PoemSession createSession() {
		return new PoemSession();
	}
}
