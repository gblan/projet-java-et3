package et3.grille.cases;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;

import et3.pions.Pion;

public class Case extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CaseEnum etatInitial;
	private CaseEnum etatActuel;
	private int x;
	private int y;
	private final int HEIGHT = 40;

	/* A FAIRE : Cases du HAUT,BAS... */

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

	public boolean intersect(Pion p) {

		Ellipse2D.Float cercleCase = new Ellipse2D.Float(x + (HEIGHT / 10), y
				+ (HEIGHT / 10) + (HEIGHT / 20), HEIGHT - (HEIGHT / 5), HEIGHT
				- (HEIGHT / 5));
		Ellipse2D.Float pion = new Ellipse2D.Float(p.getCenter_x(),
				p.getCenter_y(), p.getRayon(), p.getRayon());
		return cercleCase.intersects(pion.getCenterX(), pion.getCenterY(),
				pion.getWidth(), pion.getHeight());
	}

	public void paint(Graphics graphics) {

		// On dessine tout d'abord l'hexagone commun aux cases
		int r = HEIGHT / 2; // r = radius of inscribed circle
		int s = (int) (HEIGHT / 1.73205); // s = (h/2)/cos(30)= (h/2) /
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
			break;
		case CONTAMINEE:
			break;
		case SURVOLEE:
			break;
		case POTENTIELLE:
			break;
		case POTENTIELLESURVOLEE:
			graphics.setColor(Color.black);
			graphics.drawOval(x + (HEIGHT / 10), y + (HEIGHT / 10)
					+ (HEIGHT / 20), 2 * r - (HEIGHT / 5), 2 * r - (HEIGHT / 5));
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


}
