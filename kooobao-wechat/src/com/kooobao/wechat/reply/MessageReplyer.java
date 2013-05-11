package com.kooobao.wechat.reply;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import com.kooobao.common.util.collection.BinaryInserter;
import com.kooobao.wechat.msg.IncomingMessage;
import com.kooobao.wechat.msg.IncomingType;
import com.kooobao.wechat.msg.OutgoingMessage;
import com.kooobao.wechat.reply.game.GameMenuProcessor;
import com.kooobao.wechat.reply.game.word.WordGameProcessor;

public class MessageReplyer {

	private SessionProcessor session;

	public OutgoingMessage reply(IncomingMessage incoming) {
		OutgoingMessage reply = null;
		if (null != session) {
			reply = session.process(incoming);
			if (null == reply) {
				session = null;
			}
		}
		if (reply != null)
			return reply;
		MessageProcessor processor = lookup(incoming);
		if (null == processor)
			return null;
		if (processor instanceof SessionProcessor)
			session = (SessionProcessor) processor;
		return processor.process(incoming);
	}

	protected MessageProcessor lookup(IncomingMessage incoming) {
		List<ProcessorHolder> mps = roadmap.get(incoming.getType());
		if (!CollectionUtils.isEmpty(mps)) {
			for (ProcessorHolder mp : mps) {
				if (mp.getProcessor().accept(incoming)) {
					return mp.getProcessor();
				}
			}
		}
		return null;
	}

	private static Map<IncomingType, List<ProcessorHolder>> roadmap = new HashMap<IncomingType, List<ProcessorHolder>>();

	public static void install(MessageProcessor processor, int priority) {
		ProcessorHolder holder = new ProcessorHolder(processor, priority);
		for (IncomingType type : processor.acceptTypes()) {
			List<ProcessorHolder> holders = roadmap.get(type);
			if (null == holders) {
				holders = new ArrayList<ProcessorHolder>();
				roadmap.put(type, holders);
			}
			BinaryInserter.insert(holders, holder);
		}
	}

	{
		install(new WelcomeProcessor(), 0);
		install(new DictionaryProcessor(), 0);
		install(new WordGameProcessor(), 30);
		install(new GameMenuProcessor(), 50);
		install(new DefaultProcessor(), 100);
	}

	protected static final class ProcessorHolder implements
			Comparable<ProcessorHolder> {
		private MessageProcessor processor;

		private int priority;

		public ProcessorHolder(MessageProcessor processor, int priority) {
			this.processor = processor;
			this.priority = priority;
		}

		public MessageProcessor getProcessor() {
			return processor;
		}

		public int getPriority() {
			return priority;
		}

		@Override
		public int compareTo(ProcessorHolder o) {
			return Integer.valueOf(this.priority).compareTo(o.priority);
		}
	}
}
