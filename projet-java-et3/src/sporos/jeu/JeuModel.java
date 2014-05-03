package sporos.jeu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import sporos.grille.Grille;
import sporos.grille.cases.CaseEnum;
import sporos.grille.cases.CaseModel;
import sporos.main.Principale;
import sporos.menus.MenuPrincipal;
import sporos.reserve.Reserve;
import sporos.reserve.pions.PionModel;
import sporos.utils.Bruitages;
import sporos.utils.PropertyAcces;

public class JeuModel {

	private static final long serialVersionUID = 1L;

	private int idJeu;
	private Grille grille;
	private Reserve reserve;
	private PionModel pionSelectionne;
	private List<PionModel> pionsRelache;
	private int indiceCaseH;
	private int indiceCaseV;
	private List<PionModel> pionsEnJeu;

	

	/*
	 * TODO FIN A MODIFIER
	 */

	public JeuModel(int idJeu, Grille grille, Reserve reserve) {
		super();
		this.idJeu = idJeu;
		this.grille = grille;
		this.reserve = reserve;
		this.pionsEnJeu = new ArrayList<PionModel>();
		this.pionsRelache = new ArrayList<PionModel>();
		
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

	
	public List<PionModel> getPionRelache() {
		return pionsRelache;
	}

	public void setPionRelache(List<PionModel> pionRelache) {
		this.pionsRelache = pionRelache;
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

	public int getIdJeu() {
		return idJeu;
	}

	public void setIdJeu(int idJeu) {
		this.idJeu = idJeu;
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
