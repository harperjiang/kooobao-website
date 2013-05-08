package com.kooobao.wechat.msg;

import java.text.MessageFormat;

public abstract class OutgoingMessage extends Message {

	private int funcFlag;

	public int getFuncFlag() {
		return funcFlag;
	}

	public void setFuncFlag(int funcFlag) {
		this.funcFlag = funcFlag;
	}

	public OutgoingMessage() {
		setTimestamp(System.currentTimeMillis() / 1000);
	}

	public OutgoingMessage(IncomingMessage incoming) {
		this();
		setFrom(incoming.getTo());
		setTo(incoming.getFrom());
		setFuncFlag(0);
	}

	protected abstract String info();

	public String toString() {
		return MessageFormat.format("<xml>"
				+ "<ToUserName><![CDATA[{0}]]></ToUserName>"
				+ "<FromUserName><![CDATA[{1}]]></FromUserName>"
				+ "<CreateTime>{2}</CreateTime>" + "{3}"
				+ "<FuncFlag>{4}</FuncFlag>" + "</xml>", getTo(), getFrom(),
				Long.toString(getTimestamp()), info(), funcFlag);
	}

}
