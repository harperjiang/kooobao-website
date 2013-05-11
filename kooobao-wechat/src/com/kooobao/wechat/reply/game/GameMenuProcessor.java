package com.kooobao.wechat.reply.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kooobao.common.util.ResourceLoader;
import com.kooobao.wechat.msg.IncomingMessage;
import com.kooobao.wechat.msg.IncomingType;
import com.kooobao.wechat.msg.OutgoingMessage;
import com.kooobao.wechat.msg.TextOutgoing;
import com.kooobao.wechat.reply.MessageProcessor;

public class GameMenuProcessor implements MessageProcessor {

	protected static final String MENU_TEXT = "/com/kooobao/wechat/reply/game/GameMenuText";

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public IncomingType[] acceptTypes() {
		return new IncomingType[] { IncomingType.image, IncomingType.link,
				IncomingType.location, IncomingType.text, IncomingType.voice };
	}

	@Override
	public OutgoingMessage process(IncomingMessage incoming) {
		try {
			return new TextOutgoing(incoming,
					ResourceLoader.loadAsString(MENU_TEXT));
			
			
		} catch (Exception e) {
			if (logger.isWarnEnabled()) {
				logger.warn("Failed to load menu text", e);
			}
			return null;
		}
	}

	@Override
	public boolean accept(IncomingMessage incoming) {
		return true;
	}

}
