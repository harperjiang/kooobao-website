package com.kooobao.wechat.reply;

import com.kooobao.wechat.msg.IncomingMessage;
import com.kooobao.wechat.msg.IncomingType;
import com.kooobao.wechat.msg.OutgoingMessage;

public class DictionaryProcessor implements MessageProcessor {

	@Override
	public IncomingType[] acceptTypes() {
		return new IncomingType[] { IncomingType.text };
	}

	@Override
	public OutgoingMessage process(IncomingMessage incoming) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean accept(IncomingMessage incoming) {
		// TODO Auto-generated method stub
		return false;
	}

}
