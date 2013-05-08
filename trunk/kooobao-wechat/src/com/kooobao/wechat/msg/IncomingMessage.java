package com.kooobao.wechat.msg;

import java.awt.geom.Point2D;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public abstract class IncomingMessage extends Message {

	private IncomingType type;

	private static Logger logger = LoggerFactory
			.getLogger(IncomingMessage.class);

	public IncomingType getType() {
		return type;
	}

	public void setType(IncomingType type) {
		this.type = type;
	}

	protected static String get(Document doc, String element) throws Exception {
		NodeList nl = doc.getElementsByTagName(element);
		if (null != nl && nl.getLength() != 0)
			return nl.item(0).getTextContent();
		if (logger.isWarnEnabled()) {
			logger.warn("Document doesnot contain element " + element);
			if (logger.isTraceEnabled()) {
				logger.trace("Here's the document's content");
				logger.trace(messagetostring(doc));
			}
		}
		return null;
	}

	public static IncomingMessage parse(InputStream is) throws Exception {
		IncomingMessage message = null;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document doc = builder.parse(is);

		String from = get(doc, "FromUserName");
		String to = get(doc, "ToUserName");
		String msgId = get(doc, "MsgId");
		String msgType = get(doc, "MsgType");
		Long timestamp = Long.parseLong(get(doc, "CreateTime"));
		IncomingType type = IncomingType.valueOf(msgType);
		switch (type) {
		case text:
			TextIncoming tm = new TextIncoming();
			tm.setContent(get(doc, "Content"));
			message = tm;
			break;
		case image:
			PictureIncoming pm = new PictureIncoming();
			pm.setUrl(get(doc, "PicUrl"));
			message = pm;
			break;
		case link:
			LinkIncoming lm = new LinkIncoming();
			lm.setDescription(get(doc, "Description"));
			lm.setTitle(get(doc, "Title"));
			lm.setUrl(get(doc, "Url"));
			message = lm;
			break;
		case location:
			LocationIncoming llm = new LocationIncoming();
			llm.setLocation(new Point2D.Double(Double.parseDouble(get(doc,
					"Location_X")), Double.parseDouble(get(doc, "Location_Y"))));
			llm.setScale(Integer.parseInt(get(doc, "Scale")));
			llm.setLabel(get(doc, "Label"));
			message = llm;
			break;
		case voice:
			VoiceIncoming vi = new VoiceIncoming();
			vi.setMediaId(get(doc, "MediaId"));
			vi.setFormat(get(doc, "Format"));
			message = vi;
			break;
		case event:
			EventIncoming ei = new EventIncoming();
			ei.setEventType(EventType.valueOf(get(doc, "Event")));
			ei.setEventKey(get(doc, "EventKey"));
			message = ei;
			break;
		default:
			UnknownIncoming ui = new UnknownIncoming();

			ui.setContent(messagetostring(doc));
			message = ui;
			break;
		}

		message.setFrom(from);
		message.setTo(to);
		message.setId(msgId);
		message.setType(type);
		message.setTimestamp(timestamp);

		return message;
	}

	protected static String messagetostring(Document doc) throws Exception {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(doc), new StreamResult(writer));
		String output = writer.getBuffer().toString().replaceAll("\n|\r", "");
		return output;
	}
}
