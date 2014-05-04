package sporos.menus;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class ImageImplement extends JPanel{
	private java.awt.Image img;
	private int x;
	private int y;

	public ImageImplement(java.awt.Image img,int x,int y) {
		super();
		this.img = img;
		this.x = x;
		this.y = y;
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, x, y, null);
	}

}
