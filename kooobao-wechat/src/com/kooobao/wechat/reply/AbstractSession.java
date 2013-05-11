package com.kooobao.wechat.reply;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class AbstractSession implements Session {

	private long lastUpdate;

	private long timeout;

	private boolean inuse;

	private ReentrantReadWriteLock lock;

	public AbstractSession(long timeout) {
		this.timeout = timeout;
		lock = new ReentrantReadWriteLock();
		update();
	}

	@Override
	public long getLastUpdate() {
		try {
			lock.readLock().lock();
			return lastUpdate;
		} finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public long getTimeout() {
		return timeout;
	}

	@Override
	public void mark(boolean inuse) {
		lock.writeLock().lock();
		this.inuse = inuse;
		lock.writeLock().unlock();
	}

	@Override
	public boolean inuse() {
		try {
			lock.readLock().lock();
			return inuse;
		} finally {
			lock.readLock().unlock();
		}
	}

	protected void update() {
		lock.writeLock().lock();
		this.lastUpdate = System.currentTimeMillis();
		lock.writeLock().unlock();
	}
}
