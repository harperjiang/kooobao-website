package com.kooobao.wechat.reply;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReplyerPool {

	private static final long timeout = 300000;

	private Map<String, ReplyerHolder> replyers;

	private Map<MessageReplyer, ReplyerHolder> inuse;

	public ReplyerPool() {
		replyers = new ConcurrentHashMap<String, ReplyerHolder>();
		inuse = new ConcurrentHashMap<MessageReplyer, ReplyerHolder>();
		new DaemonThread().start();
	}

	public synchronized MessageReplyer acquire(String id) {
		if (StringUtils.isEmpty(id)) {
			return new MessageReplyer();
		}
		if (!replyers.containsKey(id)) {
			replyers.put(id, new ReplyerHolder(new MessageReplyer(), id));
		}
		ReplyerHolder holder = replyers.remove(id);
		inuse.put(holder.getReplyer(), holder);
		return holder.getReplyer();
	}

	public synchronized void release(MessageReplyer replyer) {
		ReplyerHolder holder = inuse.remove(replyer);
		if (null != holder)
			replyers.put(holder.getId(), holder);
	}

	protected synchronized void clear() {
		Iterator<Entry<String, ReplyerHolder>> iterator = replyers.entrySet()
				.iterator();
		while (iterator.hasNext()) {
			Entry<String, ReplyerHolder> next = iterator.next();
			if (next.getValue().isExpired())
				iterator.remove();
		}
	}

	protected class DaemonThread extends Thread {

		private Logger logger = LoggerFactory.getLogger(getClass());

		public DaemonThread() {
			super();
			setName("ReplyerPool-Daemon");
		}

		@Override
		public void run() {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				if (logger.isWarnEnabled()) {
					logger.warn("Daemon Thread was interrupted", e);
				}
			}
			clear();
		}
	}

	public static final class ReplyerHolder {
		MessageReplyer replyer;
		String id;
		long lastupdate;

		public ReplyerHolder(MessageReplyer replyer, String id) {
			this.replyer = replyer;
			this.id = id;
			this.lastupdate = System.currentTimeMillis();
		}

		public String getId() {
			return id;
		}

		public MessageReplyer getReplyer() {
			lastupdate = System.currentTimeMillis();
			return replyer;
		}

		public boolean isExpired() {
			return System.currentTimeMillis() >= lastupdate + timeout;
		}
	}
}
