package com.kooobao.wechat.msg;

import java.text.MessageFormat;

public class TextOutgoing extends OutgoingMessage {

	private String content;

	public TextOutgoing() {
		super();
	}

	public TextOutgoing(IncomingMessage message, String content) {
		super(message);
		setContent(content);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	protected String info() {
		return MessageFormat.format("<MsgType><![CDATA[{0}]]></MsgType>"
				+ "<Content><![CDATA[{1}]]></Content>", "text", getContent());
	}

}
