package com.kooobao.wechat;

import org.junit.Test;

import com.kooobao.wechat.msg.TextOutgoing;
import com.kooobao.wechat.send.MessageSender;

public class MessageSenderTest extends MessageSender {

	@Test
	public void testLogin() throws Exception {
		new MessageSender().login("moodever@gmail.com", "debbie1984");
	}

	@Test
	public void testSend() throws Exception {
		MessageSender sender = new MessageSender();
		sender.login("moodever@gmail.com", "debbie1984");
		
		TextOutgoing to = new TextOutgoing();
		to.setContent("System Auto push");
		sender.send(to);
	}
}
