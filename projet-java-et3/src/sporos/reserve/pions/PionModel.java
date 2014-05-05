package sporos.reserve.pions;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import sporos.grille.GrilleEnum;

public class PionModel {

	private PionEnum typePion;
	private int x;
	private int y;
	private int xInitial;
	private int yInitial;
	private int RAYON;
	private int indiceCaseH = -1;
	private int indiceCaseV = -1;
	private int caseCouranteDeplacementGauche;
	private int caseCouranteDeplacementDroite;
	private int caseCouranteDeplacementBasDroite;
	private int ajoutBasDroite;
	private int caseCouranteDeplacementBasGauche;
	private int ajoutBasGauche;
	private int caseCouranteDeplacementHautDroite;
	private int ajoutHautDroite;
	private int caseCouranteDeplacementHautGauche;
	private int ajoutHautGauche;
	private GrilleEnum taille;

	public PionModel(PionEnum typePion, int x, int y, int xInitial,
			int yInitial, GrilleEnum taille) {
		super();
		this.typePion = typePion;
		this.x = x;
		this.y = y;
		this.xInitial = xInitial;
		this.yInitial = yInitial;
		this.taille = taille;
		switch (taille) {
		case PETIT:
			this.RAYON = 31;
			break;
		case MOYEN:
			this.RAYON = 21;

			break;
		case GRAND:
			this.RAYON = 13;
			break;
		}
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

	public GrilleEnum getTaille() {
		return taille;
	}

	public void setTaille(GrilleEnum taille) {
		this.taille = taille;
	}

	public int getCaseCouranteDeplacementGauche() {
		return caseCouranteDeplacementGauche;
	}

	public void setCaseCouranteDeplacementGauche(
			int caseCouranteDeplacementGauche) {
		this.caseCouranteDeplacementGauche = caseCouranteDeplacementGauche;
	}

	public int getCaseCouranteDeplacementDroite() {
		return caseCouranteDeplacementDroite;
	}

	public void setCaseCouranteDeplacementDroite(
			int caseCouranteDeplacementDroite) {
		this.caseCouranteDeplacementDroite = caseCouranteDeplacementDroite;
	}

	public int getCaseCouranteDeplacementBasDroite() {
		return caseCouranteDeplacementBasDroite;
	}

	public void setCaseCouranteDeplacementBasDroite(
			int caseCouranteDeplacementBasDroite) {
		this.caseCouranteDeplacementBasDroite = caseCouranteDeplacementBasDroite;
	}

	public int getAjoutBasDroite() {
		return ajoutBasDroite;
	}

	public void setAjoutBasDroite(int ajoutBasDroite) {
		this.ajoutBasDroite = ajoutBasDroite;
	}

	public int getCaseCouranteDeplacementBasGauche() {
		return caseCouranteDeplacementBasGauche;
	}

	public void setCaseCouranteDeplacementBasGauche(
			int caseCouranteDeplacementBasGauche) {
		this.caseCouranteDeplacementBasGauche = caseCouranteDeplacementBasGauche;
	}

	public int getAjoutBasGauche() {
		return ajoutBasGauche;
	}

	public void setAjoutBasGauche(int ajoutBasGauche) {
		this.ajoutBasGauche = ajoutBasGauche;
	}

	public int getCaseCouranteDeplacementHautDroite() {
		return caseCouranteDeplacementHautDroite;
	}

	public void setCaseCouranteDeplacementHautDroite(
			int caseCouranteDeplacementHautDroite) {
		this.caseCouranteDeplacementHautDroite = caseCouranteDeplacementHautDroite;
	}

	public int getAjoutHautDroite() {
		return ajoutHautDroite;
	}

	public void setAjoutHautDroite(int ajoutHautDroite) {
		this.ajoutHautDroite = ajoutHautDroite;
	}

	public int getCaseCouranteDeplacementHautGauche() {
		return caseCouranteDeplacementHautGauche;
	}

	public void setCaseCouranteDeplacementHautGauche(
			int caseCouranteDeplacementHautGauche) {
		this.caseCouranteDeplacementHautGauche = caseCouranteDeplacementHautGauche;
	}

	public int getAjoutHautGauche() {
		return ajoutHautGauche;
	}

	public void setAjoutHautGauche(int ajoutHautGauche) {
		this.ajoutHautGauche = ajoutHautGauche;
	}

	public void setPositionRelativeToGrille(int x, int y) {
		this.setIndiceCaseH(x);
		this.setIndiceCaseV(y);
		if (y % 2 == 0) {
			// Y PAIR
			this.setX(20 + 40 * x);
			this.setY(80 + 35 * y);
		} else {
			// Y IMPAIR
			this.setX(40 * x);
			this.setY(80 + 35 * y);
		}
	}

	/**
	 * 
	 * @param point_x
	 * @param point_y
	 * @return true or false si le point selectionné contient le Pion
	 */
	public boolean contains(int pointX, int pointY) {
		int size = 0;
		switch (taille) {
		case PETIT:
			size = 42;
			break;
		case MOYEN:
			size = 32;
			break;
		case GRAND:
			size = 25;
			break;
		}
		Ellipse2D.Float cercle = new Ellipse2D.Float(getX(), getY(), size, size);
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
