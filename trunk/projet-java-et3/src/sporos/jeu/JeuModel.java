package sporos.jeu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import sporos.deploiment.DeploimentContaminee;
import sporos.grille.Grille;
import sporos.grille.cases.CaseEnum;
import sporos.grille.cases.CaseModel;
import sporos.reserve.Reserve;
import sporos.reserve.pions.PionEnum;
import sporos.reserve.pions.PionModel;

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

	private void cleanGrille() {
		for (ArrayList<CaseModel> alCase : grille.getListCases()) {
			for (CaseModel caseJeu : alCase) {
				if (caseJeu.getEtatActuel().equals(caseJeu.getEtatInitial())) {
					caseJeu.setEtatActuel(caseJeu.getEtatInitial());
				}
			}
		}
	}

	private void cleanReserve() {
		for(PionModel p :reserve.getPions()){
			p.setX(p.getxInitial());
			p.setY(p.getyInitial());
		}
		
		pionsEnJeu.clear();
	}

	private void randomPlacePion() {
		for (PionModel pionReserve : reserve.getPions()) {

			boolean available = true;
			do {
				available = true;
				int y = (int) (Math.random() * 7);
				int x = (int) (Math.random() * 10);
				if (grille.getListCases().get(x).get(y).getEtatActuel()
						.equals(CaseEnum.DISPONIBLE)) {
					pionReserve.setPositionRelativeToGrille(x, y);

					List<PionModel> tmp = new ArrayList<PionModel>(
							this.getPionsEnJeu());
					tmp.add(pionReserve);
					this.setPionsEnJeu(tmp);
				} else {
					available = false;
				}
			} while (available == false);

		}
	}

	public boolean isCorrectGrid(long delai) {
		long t1 = System.currentTimeMillis();
		long t2 =0;
		do {
			t2 = System.currentTimeMillis();

			/* clean la grille */
			cleanGrille();

			/* on remet les pions a la reserve */
			cleanReserve();

			// placement aléatoire des pions
			randomPlacePion();

			// déployer contamination
			DeploimentContaminee dc = new DeploimentContaminee(grille,
					pionsEnJeu, new JeuView(null),5);
			dc.deploimentListPion();
			if(isFinish()){
				return true;
			}
		} while (!isFinish()
				&& ((t2 - t1) / 1000) < delai);
		return false;
	}

}
