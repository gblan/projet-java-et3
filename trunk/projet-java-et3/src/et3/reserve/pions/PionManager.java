package et3.reserve.pions;

import java.util.List;

import et3.grille.Grille;
import et3.grille.cases.CaseEnum;

/* Une méthode par type de déploiement */
/* chaque pion apelle 1 ou plusieurs méthodes de déploiement */
public class PionManager {

	private Grille grille;
	private List<Pion> listPions;
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
	public PionManager(Grille grille, List<Pion> listPions, int indiceCaseH,
			int indiceCaseV, boolean contaminationsPossibles) {
		super();
		this.grille = grille;
		this.listPions = listPions;
		this.indiceCaseH = indiceCaseH;
		this.indiceCaseV = indiceCaseV;
		this.contaminationsPossibles = contaminationsPossibles;
	}
	
	public List<Pion> getListPions() {
		return listPions;
	}

	public void contaminationListPion() {
		for (Pion p : getListPions()) {
			contaminationPion(p);
		}
	}

	/**
	 * 
	 * @param pion
	 * @return swich sur chaque type de pion et appelle les propagations
	 *         necessaires
	 */
	private void contaminationPion(Pion p) {
		switch (p.getTypePion()) {
		case TYPE1:
			contaminationHorizontal();
			break;
		case TYPE2:
			contaminationMontante();
			break;
		case TYPE3:
			contaminationDescendante();
			break;
		case TYPE4:
			contaminationDescendante();
			contaminationHorizontal();
			break;
		case TYPE5:
			contaminationMontante();
			contaminationHorizontal();
			break;
		case TYPE6:
			contaminationMontante();
			contaminationDescendante();
			break;
		case TYPE7:
			contaminationHorizontal();
			contaminationMontante();
			contaminationDescendante();
			break;
		default:
			break;
		}
	}

	/**
	 * @return propagation du pion vers la gauche puis vers la droite
	 */
	private void contaminationHorizontal() {
		contaminationGauche();
		contaminationDroite();
	}

	private void contaminationMontante() {
		contaminationBasGauche();
		contaminationHautDroite();
	}

	private void contaminationDescendante() {
		contaminationHautGauche();
		contaminationBasDroit();
	}

	private void contaminationGauche() {
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

	private void contaminationDroite() {
		/* Vers la droite */
		for (int j = indiceCaseV + 1; j < 7; j++) {
			if ((this.grille.getListCases().get(this.indiceCaseH).get(j)
					.getEtatActuel().equals(CaseEnum.DESACTIVEE))
					|| (this.grille.getListCases().get(this.indiceCaseH).get(j)
							.getEtatActuel().equals(CaseEnum.OCCUPEE))) {
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
	}

	/**
	 * @return propagation du pion vers le haut gauche puis vers le bas droite
	 */
	private void contaminationBasDroit() {
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
	}

	private void contaminationHautGauche() {
		int ajout = 0;
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
	private void contaminationHautDroite() {
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
	}

	private void contaminationBasGauche() {
		int ajout = 0;
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
