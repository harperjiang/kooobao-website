package com.kooobao.wechat.msg;

import java.text.MessageFormat;

public class MusicOutgoing extends OutgoingMessage {

	private String title;
	private String description;
	private String url;
	private String hqUrl;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getHqUrl() {
		return hqUrl;
	}

	public void setHqUrl(String hqUrl) {
		this.hqUrl = hqUrl;
	}

	@Override
	protected String info() {
		return MessageFormat.format("<MsgType><![CDATA[music]]></MsgType>"
				+ "<Music>" + "<Title><![CDATA[{0}]]></Title>"
				+ "<Description><![CDATA[{1}]]></Description>"
				+ "<MusicUrl><![CDATA[{2}]]></MusicUrl>"
				+ "<HQMusicUrl><![CDATA[{3}]]></HQMusicUrl>" + "</Music>",
				title, description, url, hqUrl);
	}
}
