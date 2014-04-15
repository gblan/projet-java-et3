package et3.reserve.pions;

import java.util.ArrayList;

import et3.grille.Grille;
import et3.grille.cases.CaseEnum;

/* Une méthode par type de déploiement */
/* chaque pion apelle 1 ou plusieurs méthodes de déploiement */
public class PionManager {

	private Grille grille;
	private ArrayList<Pion> listPions;
	private int indiceCaseH;
	private int indiceCaseV;
	private boolean contaminationsPossibles;

	/**
	 * 
	 * @param grille
	 * @param listPions
	 * @param indiceCaseH
	 * @param indiceCaseV
	 * @param contaminationsPossibles
	 * @return Constructor PionManager
	 */
	public PionManager(Grille grille, ArrayList<Pion> listPions, int indiceCaseH,
			int indiceCaseV, boolean contaminationsPossibles) {
		super();
		this.grille = grille;
		this.listPions = listPions;
		this.indiceCaseH = indiceCaseH;
		this.indiceCaseV = indiceCaseV;
		this.contaminationsPossibles = contaminationsPossibles;
		contaminationListPion(listPions);
	}

	public void contaminationListPion(ArrayList<Pion> listPions){
		for(Pion p : listPions){
			contaminationPion(p);
		}
	}
	
	/**
	 * 
	 * @param pion
	 * @return swich sur chaque type de pion et appelle les propagations
	 *         necessaires
	 */
	public void contaminationPion(Pion p) {
		switch (p.getTypePion()) {
		case TYPE1:
			contaminationHorizontal();
			break;
		case TYPE2:
			contaminationBiaisDroit();
			break;
		case TYPE3:
			contaminationBiaisGauche();
			break;
		case TYPE4:
			contaminationBiaisGauche();
			contaminationHorizontal();
			break;
		case TYPE5:
			contaminationBiaisDroit();
			contaminationHorizontal();
			break;
		case TYPE6:
			contaminationBiaisDroit();
			contaminationBiaisGauche();
			break;
		case TYPE7:
			contaminationHorizontal();
			contaminationBiaisDroit();
			contaminationBiaisGauche();
			break;
		default:
			break;
		}
	}

	/**
	 * @return propagation du pion vers la gauche puis vers la droite
	 */
	public void contaminationHorizontal() {

		/* Vers la droite */
		for (int j = indiceCaseV+1; j < 7; j++) {
			if ((this.grille.getListCases().get(this.indiceCaseH).get(j)
					.getEtatActuel().equals(CaseEnum.DESACTIVEE))
					|| (this.grille.getListCases().get(this.indiceCaseH)
							.get(j).getEtatActuel().equals(CaseEnum.OCCUPEE))) {
				break;
			} else if (this.grille.getListCases().get(this.indiceCaseH).get(j)
					.getEtatActuel().equals(CaseEnum.DISPONIBLE)
					|| this.grille.getListCases().get(this.indiceCaseH).get(j)
							.getEtatActuel().equals(CaseEnum.CONTAMINEE)) {
				if (this.contaminationsPossibles) {
					/* SURVOL */
					this.grille.getListCases().get(this.indiceCaseH).get(j)
							.setEtatActuel(CaseEnum.POTENTIELLE);

				} else {

					this.grille.getListCases().get(this.indiceCaseH).get(j)
							.setEtatActuel(CaseEnum.CONTAMINEE);
				}

			}
		}

		/* Vers la gauche */
		for (int i = indiceCaseV - 1; i >= 0; i--) {
			if ((this.grille.getListCases().get(this.indiceCaseH).get(i)
					.getEtatActuel().equals(CaseEnum.DESACTIVEE))
					|| (this.grille.getListCases().get(this.indiceCaseH).get(i)
							.getEtatActuel().equals(CaseEnum.OCCUPEE))) {
				break;
			} else if (this.grille.getListCases().get(this.indiceCaseH).get(i)
					.getEtatActuel().equals(CaseEnum.DISPONIBLE)
					|| this.grille.getListCases().get(this.indiceCaseH).get(i)
							.getEtatActuel().equals(CaseEnum.CONTAMINEE)) {
				if (this.contaminationsPossibles) {
					/* Pour le survol */
					this.grille.getListCases().get(this.indiceCaseH).get(i)
							.setEtatActuel(CaseEnum.POTENTIELLE);
				} else {
					this.grille.getListCases().get(this.indiceCaseH).get(i)
							.setEtatActuel(CaseEnum.CONTAMINEE);
				}

			}
		}

	}

	/**
	 * @return propagation du pion vers le haut gauche puis vers le bas droite
	 */
	public void contaminationBiaisGauche() {
		int ajout = 0;

		/* vers le bas droit */
		for (int j = indiceCaseH + 1; j < 10; j++) {
			try {
				if (j % 2 == 1) {
					ajout++;
				}
				if ((this.grille.getListCases().get(j)
						.get(this.indiceCaseV + ajout).getEtatActuel()
						.equals(CaseEnum.DESACTIVEE))
						|| (this.grille.getListCases().get(j)
								.get(this.indiceCaseV + ajout).getEtatActuel()
								.equals(CaseEnum.OCCUPEE))) {
					break;
				} else if (this.grille.getListCases().get(j)
						.get(this.indiceCaseV + ajout).getEtatActuel()
						.equals(CaseEnum.DISPONIBLE)
						|| this.grille.getListCases().get(j)
								.get(this.indiceCaseV + ajout).getEtatActuel()
								.equals(CaseEnum.POTENTIELLE)) {
					if (this.contaminationsPossibles) {
						this.grille.getListCases().get(j)
								.get(this.indiceCaseV + ajout)
								.setEtatActuel(CaseEnum.POTENTIELLE);
					} else {
						this.grille.getListCases().get(j)
								.get(this.indiceCaseV + ajout)
								.setEtatActuel(CaseEnum.CONTAMINEE);
					}

				}
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}

		ajout = 0;
		/* vers le haut gauche */
		for (int j = indiceCaseH - 1; j >= 0; j--) {
			try {
				if (j % 2 == 0) {
					ajout++;
				}
				if ((this.grille.getListCases().get(j)
						.get(this.indiceCaseV - ajout).getEtatActuel()
						.equals(CaseEnum.DESACTIVEE))
						|| (this.grille.getListCases().get(j)
								.get(this.indiceCaseV - ajout).getEtatActuel()
								.equals(CaseEnum.OCCUPEE))) {
					break;
				} else if (this.grille.getListCases().get(j)
						.get(this.indiceCaseV - ajout).getEtatActuel()
						.equals(CaseEnum.DISPONIBLE)
						|| this.grille.getListCases().get(j)
								.get(this.indiceCaseV - ajout).getEtatActuel()
								.equals(CaseEnum.POTENTIELLE)) {

					this.grille.getListCases().get(j)
							.get(this.indiceCaseV - ajout)
							.setEtatActuel(CaseEnum.CONTAMINEE);
					if (this.contaminationsPossibles) {
						this.grille.getListCases().get(j)
								.get(this.indiceCaseV - ajout)
								.setEtatActuel(CaseEnum.POTENTIELLE);
					} else {
						this.grille.getListCases().get(j)
								.get(this.indiceCaseV - ajout)
								.setEtatActuel(CaseEnum.CONTAMINEE);
					}
				}
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}

	}

	/**
	 * @return propagation du pion vers le haut droit puis vers le bas gauche
	 */
	public void contaminationBiaisDroit() {
		int ajout = 0;

		/* vers le haut droit */
		for (int j = indiceCaseH - 1; j >= 0; j--) {
			try {
				if (j % 2 == 1) {
					ajout--;
				}
				if ((this.grille.getListCases().get(j)
						.get(this.indiceCaseV - ajout).getEtatActuel()
						.equals(CaseEnum.DESACTIVEE))
						|| (this.grille.getListCases().get(j)
								.get(this.indiceCaseV - ajout).getEtatActuel()
								.equals(CaseEnum.OCCUPEE))) {
					break;
				} else if (this.grille.getListCases().get(j)
						.get(this.indiceCaseV - ajout).getEtatActuel()
						.equals(CaseEnum.DISPONIBLE)
						|| this.grille.getListCases().get(j)
								.get(this.indiceCaseV - ajout).getEtatActuel()
								.equals(CaseEnum.POTENTIELLE)) {

					if (this.contaminationsPossibles) {
						this.grille.getListCases().get(j)
								.get(this.indiceCaseV - ajout)
								.setEtatActuel(CaseEnum.POTENTIELLE);
					} else {
						this.grille.getListCases().get(j)
								.get(this.indiceCaseV - ajout)
								.setEtatActuel(CaseEnum.CONTAMINEE);
					}
				}
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}

		ajout = 0;
		/* bas gauche */
		for (int j = indiceCaseH + 1; j < 10; j++) {
			try {
				if (j % 2 == 0) {
					ajout++;
				}
				if ((this.grille.getListCases().get(j)
						.get(this.indiceCaseV - ajout).getEtatActuel()
						.equals(CaseEnum.DESACTIVEE))
						|| (this.grille.getListCases().get(j)
								.get(this.indiceCaseV - ajout).getEtatActuel()
								.equals(CaseEnum.OCCUPEE))) {
					break;
				} else if (this.grille.getListCases().get(j)
						.get(this.indiceCaseV - ajout).getEtatActuel()
						.equals(CaseEnum.DISPONIBLE)
						|| this.grille.getListCases().get(j)
								.get(this.indiceCaseV - ajout).getEtatActuel()
								.equals(CaseEnum.POTENTIELLE)) {

					if (this.contaminationsPossibles) {
						this.grille.getListCases().get(j)
								.get(this.indiceCaseV - ajout)
								.setEtatActuel(CaseEnum.POTENTIELLE);
					} else {
						this.grille.getListCases().get(j)
								.get(this.indiceCaseV - ajout)
								.setEtatActuel(CaseEnum.CONTAMINEE);
					}
				}
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}

	}

}
