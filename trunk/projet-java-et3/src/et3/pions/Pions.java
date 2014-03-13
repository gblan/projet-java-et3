package et3.pions;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Pions {

	private PionsEnum typePion;
	private int x;
	private int y;
	private final int rayon = 16;

	public Pions(PionsEnum typePion, int center_x, int center_y) {
		super();
		this.typePion = typePion;
		this.x = center_x;
		this.y = center_y;
	}

	public PionsEnum getTypePion() {
		return typePion;
	}

	public void setTypePion(PionsEnum typePion) {
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

		graphics.setColor(Color.black);
		graphics.drawOval(x, y, 2 * rayon, 2 * rayon);
		graphics.setColor(new Color(255, 0, 255));
		graphics.fillOval(x, y, 2 * rayon, 2 * rayon);
	}

}
