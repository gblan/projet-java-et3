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
	private static final int HEIGHT = 40;

	/* TODO : Cases du HAUT,BAS... */

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

	/**
	 * @param graphics
	 *            on affiche la case
	 */
	public void paint(Graphics g) {

		Graphics2D graphics = (Graphics2D) g;
		graphics.setStroke(new BasicStroke(2f));
		// On dessine tout d'abord l'hexagone commun aux cases
		int r = HEIGHT / 2;
		// r = radius of inscribed circle
		int s = (int) (HEIGHT / 1.73205);
		// s = (h/2)/cos(30)= (h/2) /
		// (sqrt(3)/2) = h / sqrt(3)
		int t = (int) (r / 1.73205);
		int[] cy = new int[] { y + t, y + s + t, y + s + t + t, y + s + t,
				y + t, y };
		// this is for the whole hexagon to be below and to
		// the right of this point
		int[] cx = new int[] { x, x, x + r, x + r + r, x + r + r, x + r };

		graphics.setColor(Color.black);
		graphics.drawPolygon(cx, cy, cx.length);
		graphics.setColor(Color.blue);
		graphics.fillPolygon(cx, cy, cx.length);

		// Puis le cercle interieur en fonction de l'etat de la case
		switch (this.etatActuel) {
		case DISPONIBLE:
			/*
			 * dessine le cercle a l'interieur de l'hexagone(besoin de faire des
			 * decalages en fonction de HEIGHT a case des casts precedants
			 */
			graphics.setColor(Color.black);
			graphics.drawOval(x + (HEIGHT / 10), y + (HEIGHT / 10)
					+ (HEIGHT / 20), 2 * r - (HEIGHT / 5), 2 * r - (HEIGHT / 5));
			graphics.setColor(new Color(0, 127, 255));
			graphics.fillOval(x + (HEIGHT / 10), y + (HEIGHT / 10)
					+ (HEIGHT / 20), 2 * r - (HEIGHT / 5), 2 * r - (HEIGHT / 5));
			break;
		case OCCUPEE:
			graphics.setColor(Color.black);
			graphics.drawOval(x + (HEIGHT / 10), y + (HEIGHT / 10)
					+ (HEIGHT / 20), 2 * r - (HEIGHT / 5), 2 * r - (HEIGHT / 5));
			graphics.setColor(new Color(0, 127, 255));
			graphics.fillOval(x + (HEIGHT / 10), y + (HEIGHT / 10)
					+ (HEIGHT / 20), 2 * r - (HEIGHT / 5), 2 * r - (HEIGHT / 5));

			break;
		case CONTAMINEE:
			graphics.setStroke(new BasicStroke(3f));
			graphics.setColor(new Color(255, 255, 255));
			graphics.drawOval(x + (HEIGHT / 10), y + (HEIGHT / 10)
					+ (HEIGHT / 20), 2 * r - (HEIGHT / 5), 2 * r - (HEIGHT / 5));
			graphics.setStroke(new BasicStroke(2f));
			graphics.setColor(new Color(128, 203, 96));
			graphics.fillOval(x + (HEIGHT / 10), y + (HEIGHT / 10)
					+ (HEIGHT / 20), 2 * r - (HEIGHT / 5), 2 * r - (HEIGHT / 5));
			break;
		case SURVOLEE:
			break;
		case POTENTIELLE:
			graphics.setStroke(new BasicStroke(3f));
			graphics.setColor(new Color(255, 255, 255));
			graphics.drawOval(x + (HEIGHT / 10), y + (HEIGHT / 10)
					+ (HEIGHT / 20), 2 * r - (HEIGHT / 5), 2 * r - (HEIGHT / 5));
			graphics.setStroke(new BasicStroke(2f));
			graphics.setColor(new Color(255, 203, 96));
			graphics.fillOval(x + (HEIGHT / 10), y + (HEIGHT / 10)
					+ (HEIGHT / 20), 2 * r - (HEIGHT / 5), 2 * r - (HEIGHT / 5));
			break;
		case POTENTIELLESURVOLEE:

			graphics.setStroke(new BasicStroke(3f));
			graphics.setColor(new Color(255, 0, 0));
			graphics.drawOval(x + (HEIGHT / 10), y + (HEIGHT / 10)
					+ (HEIGHT / 20), 2 * r - (HEIGHT / 5), 2 * r - (HEIGHT / 5));
			graphics.setStroke(new BasicStroke(2f));
			graphics.setColor(new Color(255, 203, 96));
			graphics.fillOval(x + (HEIGHT / 10), y + (HEIGHT / 10)
					+ (HEIGHT / 20), 2 * r - (HEIGHT / 5), 2 * r - (HEIGHT / 5));
			break;
		case DESACTIVEE:
			break;
		default:
			break;
		}
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
