package vl.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.event.EventListenerList;

public class World {

	protected Life[][] environment;

	private Thread daemonThread;

	public static final int WORLD_SIZE = 45;

	static final int SEED_SIZE = 10;

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
		List<Point> toKill = new ArrayList<Point>();
		// Kill all lives being surrounded
		for (int i = 0; i < WORLD_SIZE; i++)
			for (int j = 0; j < WORLD_SIZE; j++) {
				if (surround(i, j))
					toKill.add(new Point(i, j));
			}
		// Kill it
		for (Point point : toKill)
			environment[point.x][point.y] = null;

		// Ask all lives to behave according to their own mind
		for (int i = 0; i < WORLD_SIZE; i++)
			for (int j = 0; j < WORLD_SIZE; j++) {
				if (environment[i][j] != null)
					// Act
					environment[i][j].act();
			}
		worldChanged(new WorldChangeEvent(this));
	}

	protected boolean surround(int i, int j) {
		return exist(limit(i - 1), limit(j - 1))
				&& exist(limit(i - 1), limit(j))
				&& exist(limit(i - 1), limit(j + 1))
				&& exist(limit(i), limit(j - 1)) && exist(i, j)
				&& exist(limit(i), limit(j + 1))
				&& exist(limit(i + 1), limit(j - 1))
				&& exist(limit(i + 1), limit(j))
				&& exist(limit(i + 1), limit(j + 1));
	}

	static final int limit(int x) {
		while (x < 0)
			x += World.WORLD_SIZE;
		while (x >= World.WORLD_SIZE)
			x -= World.WORLD_SIZE;
		return x;
	}

	protected boolean exist(int x, int y) {
		return environment[x][y] != null;
	}

	protected boolean alone(int i, int j) {
		return exist(i, j)
				&& !(exist(limit(i - 1), limit(j - 1))
						|| exist(limit(i - 1), limit(j))
						|| exist(limit(i - 1), limit(j + 1))
						|| exist(limit(i), limit(j - 1))
						|| exist(limit(i), limit(j + 1))
						|| exist(limit(i + 1), limit(j - 1))
						|| exist(limit(i + 1), limit(j)) || exist(limit(i + 1),
							limit(j + 1)));
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
