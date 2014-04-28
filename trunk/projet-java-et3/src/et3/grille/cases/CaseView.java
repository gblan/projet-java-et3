package et3.grille.cases;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class CaseView {

	private CaseModel caseModel;

	public CaseView(CaseModel caseModel) {
		super();
		this.caseModel = caseModel;	}

	public CaseModel getCaseModel() {
		return caseModel;
	}

	public void setCaseModel(CaseModel caseModel) {
		this.caseModel = caseModel;
	}

	private static final int HEIGHT = 40;

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
		int[] cy = new int[] { caseModel.getY() + t, caseModel.getY() + s + t,
				caseModel.getY() + s + t + t, caseModel.getY() + s + t,
				caseModel.getY() + t, caseModel.getY() };
		// this is for the whole hexagon to be below and to
		// the right of this point
		int[] cx = new int[] { caseModel.getX(), caseModel.getX(),
				caseModel.getX() + r, caseModel.getX() + r + r,
				caseModel.getX() + r + r, caseModel.getX() + r };

		graphics.setColor(Color.black);
		graphics.drawPolygon(cx, cy, cx.length);
		graphics.setColor(Color.blue);
		graphics.fillPolygon(cx, cy, cx.length);

		// Puis le cercle interieur en fonction de l'etat de la case
		switch (caseModel.getEtatActuel()) {
		case DISPONIBLE:
			/*
			 * dessine le cercle a l'interieur de l'hexagone(besoin de faire des
			 * decalages en fonction de HEIGHT a case des casts precedants
			 */
			graphics.setColor(Color.black);
			graphics.drawOval(caseModel.getX() + (HEIGHT / 10),
					caseModel.getY() + (HEIGHT / 10) + (HEIGHT / 20), 2 * r
							- (HEIGHT / 5), 2 * r - (HEIGHT / 5));
			graphics.setColor(new Color(0, 127, 255));
			graphics.fillOval(caseModel.getX() + (HEIGHT / 10),
					caseModel.getY() + (HEIGHT / 10) + (HEIGHT / 20), 2 * r
							- (HEIGHT / 5), 2 * r - (HEIGHT / 5));
			break;
		case OCCUPEE:
			graphics.setColor(Color.black);
			graphics.drawOval(caseModel.getX() + (HEIGHT / 10),
					caseModel.getY() + (HEIGHT / 10) + (HEIGHT / 20), 2 * r
							- (HEIGHT / 5), 2 * r - (HEIGHT / 5));
			graphics.setColor(new Color(0, 127, 255));
			graphics.fillOval(caseModel.getX() + (HEIGHT / 10),
					caseModel.getY() + (HEIGHT / 10) + (HEIGHT / 20), 2 * r
							- (HEIGHT / 5), 2 * r - (HEIGHT / 5));

			break;
		case CONTAMINEE:
			graphics.setStroke(new BasicStroke(3f));
			graphics.setColor(new Color(255, 255, 255));
			graphics.drawOval(caseModel.getX() + (HEIGHT / 10),
					caseModel.getY() + (HEIGHT / 10) + (HEIGHT / 20), 2 * r
							- (HEIGHT / 5), 2 * r - (HEIGHT / 5));
			graphics.setStroke(new BasicStroke(2f));
			graphics.setColor(new Color(128, 203, 96));
			graphics.fillOval(caseModel.getX() + (HEIGHT / 10),
					caseModel.getY() + (HEIGHT / 10) + (HEIGHT / 20), 2 * r
							- (HEIGHT / 5), 2 * r - (HEIGHT / 5));
			break;
		case SURVOLEE:
			break;
		case POTENTIELLE:
			graphics.setStroke(new BasicStroke(3f));
			graphics.setColor(new Color(255, 255, 255));
			graphics.drawOval(caseModel.getX() + (HEIGHT / 10),
					caseModel.getY() + (HEIGHT / 10) + (HEIGHT / 20), 2 * r
							- (HEIGHT / 5), 2 * r - (HEIGHT / 5));
			graphics.setStroke(new BasicStroke(2f));
			graphics.setColor(new Color(255, 203, 96));
			graphics.fillOval(caseModel.getX() + (HEIGHT / 10),
					caseModel.getY() + (HEIGHT / 10) + (HEIGHT / 20), 2 * r
							- (HEIGHT / 5), 2 * r - (HEIGHT / 5));
			break;
		case POTENTIELLESURVOLEE:

			graphics.setStroke(new BasicStroke(3f));
			graphics.setColor(new Color(255, 0, 0));
			graphics.drawOval(caseModel.getX() + (HEIGHT / 10),
					caseModel.getY() + (HEIGHT / 10) + (HEIGHT / 20), 2 * r
							- (HEIGHT / 5), 2 * r - (HEIGHT / 5));
			graphics.setStroke(new BasicStroke(2f));
			graphics.setColor(new Color(255, 203, 96));
			graphics.fillOval(caseModel.getX() + (HEIGHT / 10),
					caseModel.getY() + (HEIGHT / 10) + (HEIGHT / 20), 2 * r
							- (HEIGHT / 5), 2 * r - (HEIGHT / 5));
			break;
		case DESACTIVEE:
			break;
		default:
			break;
		}
	}

}
