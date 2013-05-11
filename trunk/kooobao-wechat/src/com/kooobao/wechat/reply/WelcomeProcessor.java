package com.kooobao.wechat.reply;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kooobao.common.util.ResourceLoader;
import com.kooobao.wechat.msg.EventIncoming;
import com.kooobao.wechat.msg.EventType;
import com.kooobao.wechat.msg.IncomingMessage;
import com.kooobao.wechat.msg.IncomingType;
import com.kooobao.wechat.msg.OutgoingMessage;
import com.kooobao.wechat.msg.TextOutgoing;

public class WelcomeProcessor implements MessageProcessor {

	final static String WELCOME_TEXT = "/com/kooobao/wechat/reply/WelcomeText";

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public IncomingType[] acceptTypes() {
		return new IncomingType[] { IncomingType.event };
	}

	@Override
	public OutgoingMessage process(IncomingMessage incoming) {
		try {
			EventIncoming ei = (EventIncoming) incoming;
			if (EventType.subscribe == ei.getEventType()) {
				return new TextOutgoing(incoming,
						ResourceLoader.loadAsString(WELCOME_TEXT));
			}
			return null;
		} catch (Exception e) {
			if (logger.isWarnEnabled()) {
				logger.warn("Failed to load menu text", e);
			}
			return null;
		}
	}

	@Override
	public boolean accept(IncomingMessage incoming) {
		return incoming instanceof EventIncoming
				&& ((EventIncoming) incoming).getEventType() == EventType.subscribe;
	}

}
