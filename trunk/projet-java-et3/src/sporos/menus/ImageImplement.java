package sporos.menus;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ImageImplement extends JPanel{
	private java.awt.Image img;

	public ImageImplement(java.awt.Image img) {
		super();
		this.img = img;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

}
