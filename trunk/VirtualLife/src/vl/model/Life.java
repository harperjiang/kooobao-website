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
		if (world.environment[newx][newy] == null) {
			world.environment[newx][newy] = this;
			world.environment[x][y] = null;
			this.x = newx;
			this.y = newy;
		}
	}

	protected void split(int newx, int newy) {
		if (world.environment[newx][newy] == null) {
			Life newlife = new Life(world, newx, newy);
			world.environment[newx][newy] = newlife;
		}
	}

	public void move() {
		int loc = rand.nextInt() % 8;
		switch (loc) {
		case 0:
			if (x > 0 && y > 0)
				move(x - 1, y - 1);
			break;
		case 1:
			if (x > 0)
				move(x - 1, y);
			break;
		case 2:
			if (x > 0 && y < World.WORLD_SIZE - 1)
				move(x - 1, y + 1);
			break;
		case 3:
			if (y > 0)
				move(x, y - 1);
			break;
		case 4:
			if (y < World.WORLD_SIZE - 1)
				move(x, y + 1);
			break;
		case 5:
			if (x < World.WORLD_SIZE - 1 && y > 0)
				move(x + 1, y - 1);
			break;
		case 6:
			if (x < World.WORLD_SIZE - 1)
				move(x + 1, y);
			break;
		case 7:
			if (x < World.WORLD_SIZE - 1 && y < World.WORLD_SIZE - 1)
				move(x + 1, y + 1);
			break;
		}
	}

	public void split() {
		int loc = rand.nextInt() % 8;
		switch (loc) {
		case 0:
			if (x > 0 && y > 0)
				split(x - 1, y - 1);
			break;
		case 1:
			if (x > 0)
				split(x - 1, y);
			break;
		case 2:
			if (x > 0 && y < World.WORLD_SIZE - 1)
				split(x - 1, y + 1);
			break;
		case 3:
			if (y > 0)
				split(x, y - 1);
			break;
		case 4:
			if (y < World.WORLD_SIZE - 1)
				split(x, y + 1);
			break;
		case 5:
			if (x < World.WORLD_SIZE - 1 && y > 0)
				split(x + 1, y - 1);
			break;
		case 6:
			if (x < World.WORLD_SIZE - 1)
				split(x + 1, y);
			break;
		case 7:
			if (x < World.WORLD_SIZE - 1 && y < World.WORLD_SIZE - 1)
				split(x + 1, y + 1);
			break;
		}
	}

	public void act() {
		switch (rand.nextInt() % 2) {
		case 0:
			move();
			break;
		case 1:
			split();
			break;
		}
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
