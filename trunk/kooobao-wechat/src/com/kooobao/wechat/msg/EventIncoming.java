package com.kooobao.wechat.msg;

public class EventIncoming extends IncomingMessage {

	private EventType eventType;

	private String eventKey;

	public EventType getEventType() {
		return eventType;
	}

	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

}
