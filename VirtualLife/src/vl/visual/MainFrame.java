package vl.visual;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4309939080077861025L;

	public MainFrame() {
		setLayout(new BorderLayout());
		setSize(620, 635);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JScrollPane sp = new JScrollPane();
		WorldPanel worldPanel = new WorldPanel();
		sp.setViewportView(worldPanel);
		add(sp, BorderLayout.CENTER);
		doLayout();
		setVisible(true);
	}

	public static void main(String[] args) {
		new MainFrame();
	}
}
