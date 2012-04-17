package com.kooobao.common.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TaskQueue<TASK> {

	private BlockingQueue<TASK> queue;

	private Executor<TASK> executor;

	public TaskQueue(int capacity, Executor<TASK> run) {
		queue = new LinkedBlockingQueue<TASK>(capacity);
		this.executor = run;
		new DaemonThread().start();
	}

	public void add(TASK task) {
		try {
			queue.put(task);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public static interface Executor<TASK> {
		public void execute(TASK task);
	}

	class DaemonThread extends Thread {

		private Log log = LogFactory.getLog(DaemonThread.class);

		DaemonThread() {
			super();
			setDaemon(true);
		}

		public void run() {
			while (true) {
				try {
					TASK task = queue.take();
					executor.execute(task);
				} catch (Exception e) {
					log.warn("Exception while executing Task", e);
				}
			}
		}
	}
}
