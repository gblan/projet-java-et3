package et3.reserve.pions;

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
	private int xInitial;
	private int yInitial;
	private static final int RAYON = 21;

	public Pion(PionEnum typePion, int x, int y, int xInitial,
			int yInitial) {
		super();
		this.typePion = typePion;
		this.x = x;
		this.y = y;
		this.xInitial = xInitial;
		this.yInitial = yInitial;
	}

	public Pion() {
	}

	public PionEnum getTypePion() {
		return typePion;
	}

	public int getxInitial() {
		return xInitial;
	}

	public int getyInitial() {
		return yInitial;
	}

	public void setTypePion(PionEnum typePion) {
		this.typePion = typePion;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getRayon() {
		return RAYON;
	}

	/**
	 * 
	 * @param point_x
	 * @param point_y
	 * @return true or false si le point selectionné contient le Pion
	 */
	public boolean contains(int pointX, int pointY) {
		Ellipse2D.Float cercle = new Ellipse2D.Float(getX(), getY(), 32, 32);
		return (cercle.contains(new Point2D.Float(pointX, pointY)));

	}

	@Override
	public String toString() {
		String res = "";

		res += "Pion : x : " + this.x + ", y : " + this.y + "\n";
		res += "\t typePion : " + this.typePion.toString() + "\n";
		return res;
	}

}
