package com.kooobao.wechat.reply;

import com.kooobao.wechat.msg.IncomingMessage;
import com.kooobao.wechat.msg.IncomingType;
import com.kooobao.wechat.msg.OutgoingMessage;

public interface MessageProcessor {

	public IncomingType[] acceptTypes();

	public boolean accept(IncomingMessage incoming);

	public OutgoingMessage process(IncomingMessage incoming);

}
