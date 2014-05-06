package sporos.grille.cases;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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

	/**
	 * @param graphics
	 *            on affiche la case
	 */
	public void paint(Graphics g) {

		Graphics2D graphics = (Graphics2D) g;
		graphics.setStroke(new BasicStroke(2f));
		// On dessine tout d'abord l'hexagone commun aux cases
		int r = caseModel.getHEIGHT() / 2;
		// r = radius of inscribed circle
		int s = (int) (caseModel.getHEIGHT() / 1.73205);
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

//		graphics.setColor(Color.black);
//		graphics.drawPolygon(cx, cy, cx.length);
//		graphics.setColor(Color.blue);
//		graphics.fillPolygon(cx, cy, cx.length);

		// Puis le cercle interieur en fonction de l'etat de la case
		switch (caseModel.getEtatActuel()) {
		case DISPONIBLE:
			/*
			 * dessine le cercle a l'interieur de l'hexagone(besoin de faire des
			 * decalages en fonction de caseModel.getHEIGHT() a case des casts
			 * precedants
			 */
			File file = new File("resources/CaseDesactive.png");
			try {
				BufferedImage img = ImageIO.read(file);
//				Image imgScaled = img.getScaledInstance(
//							2 * this.pionModel.getRayon() + 1,
//							2 * this.pionModel.getRayon() + 1,
//							BufferedImage.SCALE_FAST);
					graphics.drawImage(img,caseModel.getX(),
							caseModel.getY(), null);



			} catch (IOException e) {
				e.printStackTrace();
			}
			
			graphics.setColor(Color.black);
			graphics.drawOval(
					caseModel.getX()
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL1())+3,
					caseModel.getY()
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL1())
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL2())+3,
					2 * r - (caseModel.getHEIGHT() / 5),
					2 * r - (caseModel.getHEIGHT() / 5));
			graphics.setColor(new Color(49, 140, 231));
			graphics.fillOval(
					caseModel.getX()
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL1())+1,
					caseModel.getY()
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL1())
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL2())+1,
					2 * r - (caseModel.getHEIGHT() / 5),
					2 * r - (caseModel.getHEIGHT() / 5));
			break;
		case OCCUPEE:
			File file2 = new File("resources/CaseDesactive.png");
			try {
				BufferedImage img = ImageIO.read(file2);
//				Image imgScaled = img.getScaledInstance(
//							2 * this.pionModel.getRayon() + 1,
//							2 * this.pionModel.getRayon() + 1,
//							BufferedImage.SCALE_FAST);
					graphics.drawImage(img,caseModel.getX(),
							caseModel.getY(), null);



			} catch (IOException e) {
				e.printStackTrace();
			}
			graphics.setColor(Color.black);
			graphics.drawOval(
					caseModel.getX()
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL1())+2,
					caseModel.getY()
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL1())
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL2())+2,
					2 * r - (caseModel.getHEIGHT() / 5),
					2 * r - (caseModel.getHEIGHT() / 5));
			graphics.setColor(new Color(0, 127, 255));
			graphics.fillOval(
					caseModel.getX()
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL1())+2,
					caseModel.getY()
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL1())
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL2())+2,
					2 * r - (caseModel.getHEIGHT() / 5),
					2 * r - (caseModel.getHEIGHT() / 5));

			break;
		case CONTAMINEE:
			File file5 = new File("resources/CaseDesactive.png");
			try {
				BufferedImage img = ImageIO.read(file5);
//				Image imgScaled = img.getScaledInstance(
//							2 * this.pionModel.getRayon() + 1,
//							2 * this.pionModel.getRayon() + 1,
//							BufferedImage.SCALE_FAST);
					graphics.drawImage(img,caseModel.getX(),
							caseModel.getY(), null);



			} catch (IOException e) {
				e.printStackTrace();
			}
			graphics.setStroke(new BasicStroke(2f));
			graphics.setColor(new Color(255, 255, 255));
			graphics.drawOval(
					caseModel.getX()
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL1())+3,
					caseModel.getY()
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL1())
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL2())+3,
					2 * r - (caseModel.getHEIGHT() / 5),
					2 * r - (caseModel.getHEIGHT() / 5));
			graphics.setStroke(new BasicStroke(2f));
			graphics.setColor(new Color(121, 28, 248));
			graphics.fillOval(
					caseModel.getX()
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL1())+1,
					caseModel.getY()
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL1())
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL2())+1,
					2 * r - (caseModel.getHEIGHT() / 5),
					2 * r - (caseModel.getHEIGHT() / 5));
//			try {
//				graphics.drawImage(
//						ImageIO.read(new File("resources/virus.png")),
//						caseModel.getX() - 3, caseModel.getY(), 45, 42, this);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			 
			break;
		case SURVOLEE:
			break;
		case POTENTIELLE:
			File file3 = new File("resources/CaseDesactive.png");
			try {
				BufferedImage img = ImageIO.read(file3);
//				Image imgScaled = img.getScaledInstance(
//							2 * this.pionModel.getRayon() + 1,
//							2 * this.pionModel.getRayon() + 1,
//							BufferedImage.SCALE_FAST);
					graphics.drawImage(img,caseModel.getX(),
							caseModel.getY(), null);



			} catch (IOException e) {
				e.printStackTrace();
			}

			graphics.setStroke(new BasicStroke(3f));
			graphics.setColor(new Color(255, 255, 255));
			graphics.drawOval(
					caseModel.getX()
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL1())+2,
					caseModel.getY()
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL1())
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL2())+2,
					2 * r - (caseModel.getHEIGHT() / 5),
					2 * r - (caseModel.getHEIGHT() / 5));
			graphics.setStroke(new BasicStroke(2f));
			graphics.setColor(new Color(255, 203, 96));
			graphics.fillOval(
					caseModel.getX()
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL1())+2,
					caseModel.getY()
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL1())
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL2())+2,
					2 * r - (caseModel.getHEIGHT() / 5),
					2 * r - (caseModel.getHEIGHT() / 5));

			/* Direction de propagation */

			float dash1[] = { 4f };
			graphics.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f));
			graphics.setColor(new Color(20, 20, 20));

			switch (caseModel.getDirectionPropagation()) {
			case DESCENDANT:
				graphics.drawLine(caseModel.getX() + 10, caseModel.getY() + 5,
						caseModel.getX() + 65, caseModel.getY() + 100);
				break;
			case MONTANT:
				graphics.drawLine(caseModel.getX(), caseModel.getY() + 57,
						caseModel.getX() + 33, caseModel.getY());
				break;
			case HORIZONTAL:
				graphics.drawLine(caseModel.getX(), caseModel.getY() + 22,
						caseModel.getX() + 40, caseModel.getY() + 22);
				break;
			}
			break;
		case POTENTIELLESURVOLEE:
			File file4 = new File("resources/CaseDesactive.png");
			try {
				BufferedImage img = ImageIO.read(file4);
//				Image imgScaled = img.getScaledInstance(
//							2 * this.pionModel.getRayon() + 1,
//							2 * this.pionModel.getRayon() + 1,
//							BufferedImage.SCALE_FAST);
					graphics.drawImage(img,caseModel.getX(),
							caseModel.getY(), null);



			} catch (IOException e) {
				e.printStackTrace();
			}

			graphics.setStroke(new BasicStroke(3f));
			graphics.setColor(new Color(255, 0, 0));
			graphics.drawOval(
					caseModel.getX()
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL1())+2,
					caseModel.getY()
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL1())
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL2())+2,
					2 * r - (caseModel.getHEIGHT() / 5),
					2 * r - (caseModel.getHEIGHT() / 5));
			graphics.setStroke(new BasicStroke(2f));
			graphics.setColor(new Color(255, 203, 96));
			graphics.fillOval(
					caseModel.getX()
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL1())+2,
					caseModel.getY()
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL1())
							+ (caseModel.getHEIGHT() / caseModel.getDRAWOVAL2())+2,
					2 * r - (caseModel.getHEIGHT() / 5),
					2 * r - (caseModel.getHEIGHT() / 5));
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
