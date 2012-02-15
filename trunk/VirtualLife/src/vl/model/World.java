package vl.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.event.EventListenerList;

import vl.model.strategy.AliveStrategy;

public class World {

	protected Life[][] environment;

	private Thread daemonThread;

	public static final int WORLD_SIZE = 180;

	static final int SEED_SIZE = 5000;

	private WorldStrategy strategy = new AliveStrategy();

	public World() {
		proxy = new EventListenerList();
		environment = new Life[WORLD_SIZE][WORLD_SIZE];

		init();
		daemonThread = new Thread() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
					tick();
				}
			}
		};
		daemonThread.setDaemon(true);
		daemonThread.start();
	}

	public void init() {
		Random rand = new Random();
		for (int i = 0; i < SEED_SIZE; i++) {
			int x = Math.abs(rand.nextInt()) % WORLD_SIZE;
			int y = Math.abs(rand.nextInt()) % WORLD_SIZE;
			environment[x][y] = new Life(this, x, y);
		}
	}

	public void tick() {
		if (strategy.evolve(this))
			worldChanged(new WorldChangeEvent(this));
	}

	public void kill(int i, int j) {
		environment[i][j] = null;
	}

	public void born(int x, int y) {
		if (null == environment[x][y])
			environment[x][y] = new Life(this, x, y);
	}

	public int surround(int i, int j) {
		int val = 0;
		if (exist(limit(i - 1), limit(j - 1)))
			val++;
		if (exist(limit(i - 1), limit(j)))
			val++;
		if (exist(limit(i - 1), limit(j + 1)))
			val++;
		if (exist(limit(i), limit(j - 1)))
			val++;
		if (exist(limit(i), limit(j + 1)))
			val++;
		if (exist(limit(i + 1), limit(j - 1)))
			val++;
		if (exist(limit(i + 1), limit(j)))
			val++;
		if (exist(limit(i + 1), limit(j + 1)))
			val++;
		return val;
	}

	public boolean alone(int i, int j) {
		return 0 == surround(i, j);
	}

	static final int limit(int x) {
		while (x < 0)
			x += World.WORLD_SIZE;
		while (x >= World.WORLD_SIZE)
			x -= World.WORLD_SIZE;
		return x;
	}

	public boolean exist(int x, int y) {
		return environment[x][y] != null;
	}

	public Life get(int i, int j) {
		return environment[i][j];
	}

	private EventListenerList proxy;

	protected void worldChanged(WorldChangeEvent event) {
		for (WorldChangeListener listener : proxy
				.getListeners(WorldChangeListener.class)) {
			listener.worldChanged(event);
		}
	}

	public void addChangeListener(WorldChangeListener listener) {
		proxy.add(WorldChangeListener.class, listener);
	}

	public void removeChangeListener(WorldChangeListener listener) {
		proxy.remove(WorldChangeListener.class, listener);
	}

	public Iterable<Life> getLives() {
		List<Life> lives = new ArrayList<Life>();
		for (Life[] lifeRow : environment) {
			for (Life item : lifeRow) {
				if (null != item)
					lives.add(item);
			}
		}
		return lives;
	}

}
