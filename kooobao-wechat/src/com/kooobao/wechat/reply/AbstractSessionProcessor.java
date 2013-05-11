package com.kooobao.wechat.reply;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kooobao.wechat.msg.IncomingMessage;
import com.kooobao.wechat.msg.OutgoingMessage;

public abstract class AbstractSessionProcessor<T extends AbstractSession>
		implements SessionProcessor {

	private Map<String, T> sessions;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	public AbstractSessionProcessor() {
		sessions = new ConcurrentHashMap<String, T>();
		lock = new ReentrantReadWriteLock();
	}

	private ReentrantReadWriteLock lock;

	/**
	 * Clear out-of-date sessions
	 */
	protected void clear() {
		lock.writeLock().lock();
		List<String> keytoremove = new ArrayList<String>();
		for (Entry<String, T> entry : sessions.entrySet()) {
			if (!entry.getValue().inuse()
					&& entry.getValue().getLastUpdate()
							+ entry.getValue().getTimeout() > System
								.currentTimeMillis()) {
				keytoremove.add(entry.getKey());
			}
		}
		for (String key : keytoremove) {
			sessions.remove(key);
		}
		lock.writeLock().unlock();
	}

	@Override
	public OutgoingMessage process(IncomingMessage incoming) {
		String fromId = incoming.getFrom();
		lock.readLock().lock();
		T session = sessions.get(fromId);
		if (null != session)
			session.mark(true);
		lock.readLock().unlock();
		if (null == session) {
			lock.writeLock().lock();
			if (!sessions.containsKey(fromId)) {
				session = createSession();
				sessions.put(fromId, session);
				session.mark(true);
			}
			lock.writeLock().unlock();
		}
		try {
			return processWithSession(session, incoming);
		} finally {
			session.mark(false);
		}
	}

	protected String getClassName() {
		return getClass().getSimpleName();
	}

	protected abstract T createSession();

	protected abstract OutgoingMessage processWithSession(T session,
			IncomingMessage incoming);

	protected final class SessionCleanThread extends Thread {

		private Logger logger = LoggerFactory.getLogger(getClass());

		public SessionCleanThread() {
			super();
			setName(getClassName() + "-DaemonThread");
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
}
