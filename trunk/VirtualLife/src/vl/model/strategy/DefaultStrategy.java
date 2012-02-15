package vl.model.strategy;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import vl.model.World;
import vl.model.WorldStrategy;

public class DefaultStrategy implements WorldStrategy {

	public boolean evolve(World world) {
		List<Point> toKill = new ArrayList<Point>();
		// Kill all lives being surrounded
		for (int i = 0; i < World.WORLD_SIZE; i++)
			for (int j = 0; j < World.WORLD_SIZE; j++) {
				if (8 == world.surround(i, j))
					toKill.add(new Point(i, j));
			}
		// Kill it
		for (Point point : toKill)
			world.kill(point.x, point.y);

		// Ask all lives to behave according to their own mind
		for (int i = 0; i < World.WORLD_SIZE; i++)
			for (int j = 0; j < World.WORLD_SIZE; j++) {
				if (world.exist(i, j))
					// Act
					world.get(i, j).act();
			}
		return true;

	}

}
