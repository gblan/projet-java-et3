package sporos.grille.cases;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class CaseView implements ImageObserver {

	private CaseModel caseModel;

	public CaseView(CaseModel caseModel) {
		super();
		this.caseModel = caseModel;
	}

	public CaseModel getCaseModel() {
		return caseModel;
	}

	public void setCaseModel(CaseModel caseModel) {
		this.caseModel = caseModel;
	}

	private static final int HEIGHT = 40;
	private static final int DRAWOVAL1 = 10;
	private static final int DRAWOVAL2 = 20;
	
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
			graphics.drawOval(caseModel.getX() + (HEIGHT / DRAWOVAL1),
					caseModel.getY() + (HEIGHT / DRAWOVAL1) + (HEIGHT / DRAWOVAL2), 2 * r
							- (HEIGHT / 5), 2 * r - (HEIGHT / 5));
			graphics.setColor(new Color(0, 127, 255));
			graphics.fillOval(caseModel.getX() + (HEIGHT / DRAWOVAL1),
					caseModel.getY() + (HEIGHT / DRAWOVAL1) + (HEIGHT / DRAWOVAL2), 2 * r
							- (HEIGHT / 5), 2 * r - (HEIGHT / 5));
			break;
		case OCCUPEE:
			graphics.setColor(Color.black);
			graphics.drawOval(caseModel.getX() + (HEIGHT / DRAWOVAL1),
					caseModel.getY() + (HEIGHT / DRAWOVAL1) + (HEIGHT / DRAWOVAL2), 2 * r
							- (HEIGHT / 5), 2 * r - (HEIGHT / 5));
			graphics.setColor(new Color(0, 127, 255));
			graphics.fillOval(caseModel.getX() + (HEIGHT / DRAWOVAL1),
					caseModel.getY() + (HEIGHT / DRAWOVAL1) + (HEIGHT / DRAWOVAL2), 2 * r
							- (HEIGHT / 5), 2 * r - (HEIGHT / 5));

			break;
		case CONTAMINEE:
			graphics.setStroke(new BasicStroke(3f));
			graphics.setColor(new Color(255, 255, 255));
			graphics.drawOval(caseModel.getX() + (HEIGHT / DRAWOVAL1),
					caseModel.getY() + (HEIGHT / DRAWOVAL1) + (HEIGHT / DRAWOVAL2), 2 * r
							- (HEIGHT / 5), 2 * r - (HEIGHT / 5));
			graphics.setStroke(new BasicStroke(2f));
			graphics.setColor(new Color(128, 203, 96));
			try {
				graphics.drawImage(ImageIO.read(new File("resources/virus.png")), caseModel.getX()-2, caseModel.getY(), 45, 42, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
//			graphics.fillOval(caseModel.getX() + (HEIGHT / DRAWOVAL1),
//					caseModel.getY() + (HEIGHT / DRAWOVAL1) + (HEIGHT / DRAWOVAL2), 2 * r
//							- (HEIGHT / 5), 2 * r - (HEIGHT / 5));
			break;
		case SURVOLEE:
			break;
		case POTENTIELLE:

			
			graphics.setStroke(new BasicStroke(3f));
			graphics.setColor(new Color(255, 255, 255));
			graphics.drawOval(caseModel.getX() + (HEIGHT / DRAWOVAL1),
					caseModel.getY() + (HEIGHT / DRAWOVAL1) + (HEIGHT / DRAWOVAL2), 2 * r
							- (HEIGHT / 5), 2 * r - (HEIGHT / 5));
			graphics.setStroke(new BasicStroke(2f));
			graphics.setColor(new Color(255, 203, 96));
			graphics.fillOval(caseModel.getX() + (HEIGHT / DRAWOVAL1),
					caseModel.getY() + (HEIGHT / DRAWOVAL1) + (HEIGHT / DRAWOVAL2), 2 * r
							- (HEIGHT / 5), 2 * r - (HEIGHT / 5));
			
			/* Direction de propagation */

	        float dash1[] = {4f};
			graphics.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f));
			graphics.setColor(new Color(20, 20, 20));
			
			if (caseModel.getDirectionPropagation().equals(
					DirectionPropagationEnum.DESCENDANT)) {
				graphics.drawLine(caseModel.getX() + 10, caseModel.getY() + 5,caseModel.getX() + 65, caseModel.getY() + 100);
				
			} else if (caseModel.getDirectionPropagation().equals(
					DirectionPropagationEnum.MONTANT)) {
				graphics.drawLine(caseModel.getX(), caseModel.getY()+57, caseModel.getX()+33, caseModel.getY());
				
			} else if (caseModel.getDirectionPropagation().equals(
					DirectionPropagationEnum.HORIZONTAL)) {
				graphics.drawLine(caseModel.getX(), caseModel.getY()+22, caseModel.getX()+40, caseModel.getY()+22);
				
			}			
			break;
		case POTENTIELLESURVOLEE:

			graphics.setStroke(new BasicStroke(3f));
			graphics.setColor(new Color(255, 0, 0));
			graphics.drawOval(caseModel.getX() + (HEIGHT / DRAWOVAL1),
					caseModel.getY() + (HEIGHT / DRAWOVAL1) + (HEIGHT / DRAWOVAL2), 2 * r
							- (HEIGHT / 5), 2 * r - (HEIGHT / 5));
			graphics.setStroke(new BasicStroke(2f));
			graphics.setColor(new Color(255, 203, 96));
			graphics.fillOval(caseModel.getX() + (HEIGHT / DRAWOVAL1),
					caseModel.getY() + (HEIGHT / DRAWOVAL1) + (HEIGHT / DRAWOVAL2), 2 * r
							- (HEIGHT / 5), 2 * r - (HEIGHT / 5));
			break;
		case DESACTIVEE:
			break;
		default:
			break;
		}
	}

	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}

}
