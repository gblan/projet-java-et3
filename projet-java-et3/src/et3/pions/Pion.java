package et3.pions;

import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pion {

	private PionEnum typePion;
	private int x;
	private int y;
	private final int rayon = 16;

	public Pion(PionEnum typePion, int center_x, int center_y) {
		super();
		this.typePion = typePion;
		this.x = center_x;
		this.y = center_y;
	}

	/**
	 * 
	 * @return
	 */
	public PionEnum getTypePion() {
		return typePion;
	}

	/**
	 * 
	 * @param typePion
	 */
	public void setTypePion(PionEnum typePion) {
		this.typePion = typePion;
	}

	public int getCenter_x() {
		return x;
	}

	public void setCenter_x(int center_x) {
		this.x = center_x;
	}

	public int getCenter_y() {
		return y;
	}

	public void setCenter_y(int center_y) {
		this.y = center_y;
	}

	public int getRayon() {
		return rayon;
	}

	public boolean contains(int point_x, int point_y) {
		Ellipse2D.Float cercle = new Ellipse2D.Float(x, y, 2 * rayon, 2 * rayon);
		return (cercle.contains(new Point2D.Float(point_x, point_y)));

	}

	public void paint(Graphics graphics) {

		/*
		 * graphics.setColor(Color.black); graphics.drawOval(x, y, 2 * rayon, 2
		 * * rayon); graphics.setColor(new Color(255, 0, 255));
		 * graphics.fillOval(x, y, 2 * rayon, 2 * rayon);
		 */

		// Puis le cercle interieur en fonction de l'etat de la case
		switch (this.typePion) {
		case TYPE1:
			File file = new File("resources/Pion_1.png");
			try {
				BufferedImage img = ImageIO.read(file);
				graphics.drawImage(img, x, y, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case TYPE2:
			File file2 = new File("resources/Pion_2.png");
			try {
				BufferedImage img = ImageIO.read(file2);
				graphics.drawImage(img, x, y, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case TYPE3:
			File file3 = new File("resources/Pion_3.png");
			try {
				BufferedImage img = ImageIO.read(file3);
				graphics.drawImage(img, x, y, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case TYPE4:
			File file4 = new File("resources/Pion_4.png");
			try {
				BufferedImage img = ImageIO.read(file4);
				graphics.drawImage(img, x, y, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case TYPE5:
			File file5 = new File("resources/Pion_5.png");
			try {
				BufferedImage img = ImageIO.read(file5);
				graphics.drawImage(img, x, y, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case TYPE6:
			File file6 = new File("resources/Pion_6.png");
			try {
				BufferedImage img = ImageIO.read(file6);
				graphics.drawImage(img, x, y, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case TYPE7:
			File file7 = new File("resources/Pion_7.png");
			try {
				BufferedImage img = ImageIO.read(file7);
				graphics.drawImage(img, x, y, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

}
