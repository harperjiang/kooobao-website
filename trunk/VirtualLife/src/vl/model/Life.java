package vl.model;

import java.util.Random;

public class Life {

	private int x;
	private int y;
	private World world;
	private Random rand;

	public Life(World w, int x, int y) {
		this.world = w;
		this.x = x;
		this.y = y;
		rand = new Random(System.currentTimeMillis() + x * 100000 + y * 1000);
	}

	protected void move(int newx, int newy) {
		if (newx < 0)
			newx = World.WORLD_SIZE + newx;
		if (newy < 0)
			newy = World.WORLD_SIZE + newy;
		if (newx >= World.WORLD_SIZE)
			newx -= World.WORLD_SIZE;
		if (newy >= World.WORLD_SIZE)
			newy -= World.WORLD_SIZE;
		if (world.environment[newx][newy] == null) {
			world.environment[newx][newy] = this;
			world.environment[x][y] = null;
			this.x = newx;
			this.y = newy;
		}
	}

	protected void split(int newx, int newy) {
		newx = World.limit(newx);
		newy = World.limit(newy);
		if (world.environment[newx][newy] == null) {
			Life newlife = new Life(world, newx, newy);
			world.environment[newx][newy] = newlife;
		}
	}

	public void move() {
		int loc = rand.nextInt() % 8;
		switch (loc) {
		case 0:
			move(x - 1, y - 1);
			break;
		case 1:
			move(x - 1, y);
			break;
		case 2:
			move(x - 1, y + 1);
			break;
		case 3:
			move(x, y - 1);
			break;
		case 4:
			move(x, y + 1);
			break;
		case 5:
			move(x + 1, y - 1);
			break;
		case 6:
			move(x + 1, y);
			break;
		case 7:
			move(x + 1, y + 1);
			break;
		}
	}

	public void split() {
		int loc = rand.nextInt() % 8;
		switch (loc) {
		case 0:
			split(x - 1, y - 1);
			break;
		case 1:
			split(x - 1, y);
			break;
		case 2:
			split(x - 1, y + 1);
			break;
		case 3:
			split(x, y - 1);
			break;
		case 4:
			split(x, y + 1);
			break;
		case 5:
			split(x + 1, y - 1);
			break;
		case 6:
			split(x + 1, y);
			break;
		case 7:
			split(x + 1, y + 1);
			break;
		}
	}

	public void act() {
		if (alone())
			split();
		else
			move();
		// switch (rand.nextInt() % 2) {
		// case 0:
		// move();
		// break;
		// case 1:
		// split();
		// break;
		// }
	}

	protected boolean alone() {
		return getWorld().alone(getX(), getY());
	}

	public int getX() {
		return x;
	}

	protected void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	protected void setY(int y) {
		this.y = y;
	}

	public World getWorld() {
		return world;
	}

}
