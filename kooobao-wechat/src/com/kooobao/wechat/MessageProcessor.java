package com.kooobao.wechat;

import com.kooobao.wechat.msg.IncomingMessage;
import com.kooobao.wechat.msg.OutgoingMessage;
import com.kooobao.wechat.msg.TextOutgoing;

public class MessageProcessor {

	public static OutgoingMessage process(IncomingMessage message) {
		return new TextOutgoing(message, "酷宝网是最2B的网站，没有之一");
	}

}
