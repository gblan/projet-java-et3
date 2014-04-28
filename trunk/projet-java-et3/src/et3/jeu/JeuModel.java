package et3.jeu;

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import et3.grille.Grille;
import et3.grille.cases.CaseModel;
import et3.grille.cases.CaseEnum;
import et3.reserve.Reserve;
import et3.reserve.pions.PionModel;

public class JeuModel  {

	private static final long serialVersionUID = 1L;

	private Grille grille;
	private Reserve reserve;
	private PionModel pionSelectionne;
	private int indiceCaseH;
	private int indiceCaseV;
	private List<PionModel> pionsEnJeu;

	/*
	 * TODO FIN A MODIFIER
	 */

	public JeuModel(Grille grille, Reserve reserve) {
		super();
		this.grille = grille;
		this.reserve = reserve;
		this.setPionsEnJeu(new ArrayList<PionModel>());
	}

	public Reserve getReserve() {
		return reserve;
	}

	public void setReserve(Reserve reserve) {
		this.reserve = reserve;
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

	public PionModel getPionSelectionne() {
		return pionSelectionne;
	}

	public void setPionSelectionne(PionModel pionSelectionne) {
		this.pionSelectionne = pionSelectionne;
	}

	public Grille getGrille() {
		return grille;
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}

	public List<PionModel> getPionsEnJeu() {
		return pionsEnJeu;
	}
	
	public void setPionsEnJeu(List<PionModel> pionsEnJeu) {
		this.pionsEnJeu = pionsEnJeu;
	}
	



	public boolean isFinish() {
		for (ArrayList<CaseModel> alCase : grille.getListCases()) {
			for (CaseModel grilleJeu : alCase) {
				if (!grilleJeu.getEtatActuel().equals(CaseEnum.OCCUPEE)
						&& !grilleJeu.getEtatActuel().equals(
								CaseEnum.CONTAMINEE)
						&& !grilleJeu.getEtatActuel().equals(
								CaseEnum.DESACTIVEE)) {
					return false;
				}
			}
		}
		return true;
	}

}
