package vl.visual;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import vl.model.Life;
import vl.model.World;
import vl.model.WorldChangeEvent;
import vl.model.WorldChangeListener;

public class WorldPanel extends JPanel implements WorldChangeListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5036102795658856782L;

	private World world;

	static int PANEL_WIDTH = 900;

	public WorldPanel() {
		super();
		setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_WIDTH));
		world = new World();
		world.addChangeListener(this);
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Color oldColor = g2d.getColor();
		g2d.setColor(Color.WHITE);
		// g2d.setStroke(new BasicStroke(3f));
		g2d.fillRect(0, 0, PANEL_WIDTH, PANEL_WIDTH);
		g2d.setColor(Color.BLACK);
		g.drawRect(0, 0, PANEL_WIDTH, PANEL_WIDTH);
		// g2d.setStroke(new BasicStroke(1f));
		int step = PANEL_WIDTH / World.WORLD_SIZE;
		for (int i = 0; i < PANEL_WIDTH; i += step) {
			g2d.drawLine(i, 0, i, PANEL_WIDTH);
			g2d.drawLine(0, i, PANEL_WIDTH, i);
		}
		for (Life life : world.getLives()) {
			paintLife(g2d, life.getX(), life.getY());
		}
		g2d.setColor(oldColor);
	}

	protected void paintLife(Graphics2D g, int x, int y) {
		int step = PANEL_WIDTH / World.WORLD_SIZE;
		g.fillRect(x * step, y * step, step, step);
	}

	public void worldChanged(WorldChangeEvent event) {
		repaint();
	}

}
