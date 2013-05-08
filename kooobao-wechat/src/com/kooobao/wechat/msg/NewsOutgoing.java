package com.kooobao.wechat.msg;

import java.text.MessageFormat;
import java.util.List;

public class NewsOutgoing extends OutgoingMessage {

	private List<NewsItem> items;

	public static final class NewsItem {
		private String title;
		private String description;
		private String picUrl;
		private String url;

		public String toString() {
			return MessageFormat.format("<item>"
					+ "<Title><![CDATA[{0}]]></Title>"
					+ "<Description><![CDATA[{1}]]></Description>"
					+ "<PicUrl><![CDATA[{2}]]></PicUrl>"
					+ "<Url><![CDATA[{3}]]></Url></item>", title, description,
					picUrl, url);
		}

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

		public String getPicUrl() {
			return picUrl;
		}

		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
	}

	public List<NewsItem> getItems() {
		return items;
	}

	public void setItems(List<NewsItem> items) {
		this.items = items;
	}

	@Override
	protected String info() {
		StringBuilder sb = new StringBuilder();
		sb.append("<MsgType><![CDATA[news]]></MsgType>");
		sb.append(MessageFormat.format("<ArticleCount>{0}</ArticleCount>",
				items.size()));
		sb.append("<Articles>");
		for (NewsItem item : items) {
			sb.append(item.toString());
		}
		sb.append("</Articles>");
		return sb.toString();
	}
}
