package com.kooobao.wechat.msg;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TextOutgoingTest extends TextOutgoing {

	@Test
	public void testToString() {
		TextOutgoing tgo = new TextOutgoing();
		tgo.setFrom("Abc");
		tgo.setTo("Bda");
		tgo.setTimestamp(12344244);
		tgo.setFuncFlag(0);
		tgo.setContent("This is a text");
		
		assertEquals("<xml><ToUserName><![CDATA[Bda]]></ToUserName><FromUserName><![CDATA[Abc]]></FromUserName><CreateTime>12344244</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[This is a text]]></Content><FuncFlag>0</FuncFlag></xml>",tgo.toString());
	}

}
