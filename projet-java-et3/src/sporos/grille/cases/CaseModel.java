package sporos.grille.cases;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import sporos.grille.GrilleEnum;
import sporos.reserve.pions.PionModel;

public class CaseModel {

	private CaseEnum etatInitial;
	private CaseEnum etatActuel;
	private int x;
	private int y;
	private GrilleEnum taille;
	private int HEIGHT;
	private int DRAWOVAL1;
	private int DRAWOVAL2;
	private DirectionPropagationEnum directionPropagation;

	public CaseModel(CaseEnum etatInitial, CaseEnum etatActuel, int x, int y,
			GrilleEnum taille) {
		super();
		this.etatInitial = etatInitial;
		this.etatActuel = etatActuel;
		this.x = x;
		this.y = y;
		this.taille = taille;
		switch (this.taille) {
		case PETIT:
			this.HEIGHT = 60;
			this.DRAWOVAL1 = 15;
			this.DRAWOVAL2 = 30;
			break;
		case MOYEN:
			this.HEIGHT = 40;
			this.DRAWOVAL1 = 10;
			this.DRAWOVAL2 = 20;
			break;
		case GRAND:
			this.HEIGHT = 30;
			this.DRAWOVAL1 = 40;
			this.DRAWOVAL2 = 10;
			break;
		default:
			this.HEIGHT = 40;
			this.DRAWOVAL1 = 10;
			this.DRAWOVAL2 = 20;
			break;
		}

	}

	public int getHEIGHT() {
		return HEIGHT;
	}

	public void setHEIGHT(int hEIGHT) {
		HEIGHT = hEIGHT;
	}

	public int getDRAWOVAL1() {
		return DRAWOVAL1;
	}

	public void setDRAWOVAL1(int dRAWOVAL1) {
		DRAWOVAL1 = dRAWOVAL1;
	}

	public int getDRAWOVAL2() {
		return DRAWOVAL2;
	}

	public void setDRAWOVAL2(int dRAWOVAL2) {
		DRAWOVAL2 = dRAWOVAL2;
	}

	public CaseModel(GrilleEnum taille) {
		this.taille = taille;
		switch (this.taille) {
		case PETIT:
			this.HEIGHT = 50;
			this.DRAWOVAL1 = 12;
			this.DRAWOVAL2 = 11;
			break;
		case MOYEN:
			this.HEIGHT = 40;
			this.DRAWOVAL1 = 10;
			this.DRAWOVAL2 = 20;
			break;
		case GRAND:
			this.HEIGHT = 30;
			this.DRAWOVAL1 = 11;
			this.DRAWOVAL2 = 15;
			break;
		default:
			this.HEIGHT = 40;
			this.DRAWOVAL1 = 10;
			this.DRAWOVAL2 = 20;
			break;
		}
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

	public GrilleEnum getTaille() {
		return taille;
	}

	public void setTaille(GrilleEnum taille) {
		this.taille = taille;
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
	public CaseModel getPositions(int x, int y) {
		CaseModel casePosition = new CaseModel(taille);
		int ecartX;
		int posX;
		int ecartY;
		int posY;
		switch (this.taille) {
		case PETIT:
			ecartX = 25;
			posX = 50;
			ecartY = 120;
			posY = 43;
			break;
		case MOYEN:
			ecartX = 20;
			posX = 40;
			ecartY = 80;
			posY = 35;
			break;
		case GRAND:
			ecartX = 20;
			posX = 30;
			ecartY = 70;
			posY = 25;
			break;
		default:
			ecartX = 20;
			posX = 40;
			ecartY = 80;
			posY = 35;
			break;
		}
		if (y % 2 == 0) {
			// Y PAIR
			casePosition.setX(ecartX + posX * x);
			casePosition.setY(ecartY + posY * y);
		} else {
			// Y IMPAIR
			if (taille == GrilleEnum.GRAND) {
				casePosition.setX(5 + posX * x);
			} else {
				casePosition.setX(posX * x);
			}
			casePosition.setY(ecartY + posY * y);
		}

		return casePosition;
	}

	/**
	 * 
	 * @param p
	 * @return true or false si le pion est sur la case ou pas
	 */
	public boolean intersect(PionModel p) {

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

	@Override
	public String toString() {
		String res = "";
		res += "Case : x : " + this.x + ", y : " + this.y + "\n";
		res += "\t etat Initial : " + this.etatInitial.toString()
				+ ", etat actuel : " + this.etatActuel.toString() + "\n";

		return res;
	}

	public DirectionPropagationEnum getDirectionPropagation() {
		return directionPropagation;
	}

	public void setDirectionPropagation(
			DirectionPropagationEnum directionPropagation) {
		this.directionPropagation = directionPropagation;
	}

}
