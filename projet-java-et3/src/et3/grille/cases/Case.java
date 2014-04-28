package et3.grille.cases;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import et3.reserve.pions.Pion;

public class Case extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CaseEnum etatInitial;
	private CaseEnum etatActuel;
	private int x;
	private int y;

	public Case(CaseEnum etatInitial, CaseEnum etatActuel, int x, int y) {
		super();
		this.etatInitial = etatInitial;
		this.etatActuel = etatActuel;
		this.x = x;
		this.y = y;
	}

	public Case() {
	}

	public CaseEnum getEtatInitial() {
		return etatInitial;
	}

	public void setEtatInitial(CaseEnum etatInitial) {
		this.etatInitial = etatInitial;
	}

	public CaseEnum getEtatActuel() {
		return etatActuel;
	}

	public void setEtatActuel(CaseEnum etatActuel) {
		this.etatActuel = etatActuel;
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

	/**
	 * 
	 * @param x
	 * @param y
	 * @return position absolue de chaque case
	 */
	public static Case getPositions(int x, int y) {
		Case casePosition = new Case();
		if (y % 2 == 0) {
			// Y PAIR
			casePosition.setX(20 + 40 * x);
			casePosition.setY(80 + 35 * y);
		} else {
			// Y IMPAIR
			casePosition.setX(40 * x);
			casePosition.setY(80 + 35 * y);
		}

		return casePosition;
	}

	/**
	 * 
	 * @param p
	 * @return true or false si le pion est sur la case ou pas
	 */
	public boolean intersect(Pion p) {

		/*
		 * Calcul de la distance entre la case et le pion En x
		 */

		int centreCaseX = x + (HEIGHT / 10);
		int centrePionX = p.getX();
		int distanceX = Math.abs(centrePionX - centreCaseX);

		/* En y */

		int centreCaseY = y + (HEIGHT / 10) + (HEIGHT / 20);
		int centrePionY = p.getY();
		int distanceY = Math.abs(centrePionY - centreCaseY);

		/* Distance */

		int distance = (int) Math.sqrt(Math.pow(distanceX, 2)
				+ Math.pow(distanceY, 2));

		if (distance < p.getRayon() - (p.getRayon() / 20)) {
			return true;
		}

		return false;
	}

	/**
	 * 
	 * @param pointX
	 * @param pointY
	 * @return true or false si la case contient le pion en parametre
	 */
	public boolean contains(int pointX, int pointY) {
		Ellipse2D.Float cercle = new Ellipse2D.Float(x, y, HEIGHT, HEIGHT);
		return (cercle.contains(new Point2D.Float(pointX, pointY)));

	}
	
	public void paint(Graphics g) {
		
	}


	@Override
	public String toString() {
		String res = "";
		res += "Case : x : " + this.x + ", y : " + this.y + "\n";
		res += "\t etat Initial : " + this.etatInitial.toString()
				+ ", etat actuel : " + this.etatActuel.toString() + "\n";

		return res;
	}

}
