package et3.reserve.pions;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class PionModel {

	private PionEnum typePion;
	private int x;
	private int y;
	private int xInitial;
	private int yInitial;
	private static final int RAYON = 21;
	private int indiceCaseH = -1;
	private int indiceCaseV = -1;

	public PionModel(PionEnum typePion, int x, int y, int xInitial, int yInitial) {
		super();
		this.typePion = typePion;
		this.x = x;
		this.y = y;
		this.xInitial = xInitial;
		this.yInitial = yInitial;
	}

	public PionModel() {
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

	public int getIndiceCaseH() {
		return indiceCaseH;
	}

	public void setIndiceCaseH(int indiceCaseH) {
		this.indiceCaseH = indiceCaseH;
	}

	public int getIndiceCaseV() {
		return indiceCaseV;
	}

	public void setIndiceCaseV(int indiceCaseV) {
		this.indiceCaseV = indiceCaseV;
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
