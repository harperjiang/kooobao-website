package com.kooobao.common.web.bean;

public abstract class SelfRefreshBean extends AbstractBean implements
		JSFStartupAware {

	private long interval = 3600000;

	private RefreshThread thread;

	private volatile boolean stop;

	protected abstract void refresh();

	public void init() {
		this.thread = new RefreshThread();
		this.thread.start();
	}

	public void dispose() {
		this.stop = true;
	}

	class RefreshThread extends Thread {

		RefreshThread() {
			super();
			setDaemon(true);
			setName(SelfRefreshBean.this.getClass().getSimpleName()
					+ " Refresh Thread");
		}

		@Override
		public void run() {
			while (!stop) {
				refresh();
				try {
					Thread.sleep(interval);
				} catch (InterruptedException e) {
					return;
				}
			}
		}
	}
}
