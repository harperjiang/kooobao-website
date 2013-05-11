package com.kooobao.wechat.reply;

import java.util.Random;

import com.kooobao.wechat.msg.IncomingMessage;
import com.kooobao.wechat.msg.IncomingType;
import com.kooobao.wechat.msg.OutgoingMessage;
import com.kooobao.wechat.msg.TextOutgoing;

public class DefaultProcessor implements MessageProcessor {

	@Override
	public IncomingType[] acceptTypes() {
		return new IncomingType[] { IncomingType.image, IncomingType.link,
				IncomingType.location, IncomingType.text, IncomingType.voice };
	}

	static final String[] REPLY = { "嗯", "呵呵", "真的吗", "您说啥？酷堡没听懂啊" };

	@Override
	public OutgoingMessage process(IncomingMessage incoming) {
		int random = new Random(System.currentTimeMillis() * hashCode())
				.nextInt(REPLY.length);
		return new TextOutgoing(incoming, REPLY[random]);
	}

	@Override
	public boolean accept(IncomingMessage incoming) {
		return true;
	}

}
