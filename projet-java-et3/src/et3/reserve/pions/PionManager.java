package et3.reserve.pions;

import et3.grille.Grille;
import et3.grille.cases.CaseEnum;

/* Une méthode par type de déploiement */
/* chaque pion apelle 1 ou plusieurs méthodes de déploiement */
public class PionManager {

	private Grille grille;
	private Pion pion;
	private int indiceCaseH;
	private int indiceCaseV;

	public PionManager(Grille grille, Pion pion, int indiceCaseH,
			int indiceCaseV) {
		super();
		this.grille = grille;
		this.pion = pion;
		this.indiceCaseH = indiceCaseH;
		this.indiceCaseV = indiceCaseV;
		contaminationPion(pion);
	}

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

	public void contaminationHorizontal() {

		/* Vers la droite */
		for (int j = indiceCaseV; j < 7; j++) {
			if (this.grille.getListCases().get(this.indiceCaseH).get(j)
					.getEtatActuel().equals(CaseEnum.DESACTIVEE)) {
				break;
			} else if (this.grille.getListCases().get(this.indiceCaseH).get(j)
					.getEtatActuel().equals(CaseEnum.DISPONIBLE)) {
				this.grille.getListCases().get(this.indiceCaseH).get(j)
						.setEtatActuel(CaseEnum.CONTAMINEE);
			}
		}

		/* Vers la gauche */
		for (int i = indiceCaseV - 1; i >= 0; i--) {
			if (this.grille.getListCases().get(this.indiceCaseH).get(i)
					.getEtatActuel().equals(CaseEnum.DESACTIVEE)) {
				break;
			} else if (this.grille.getListCases().get(this.indiceCaseH).get(i)
					.getEtatActuel().equals(CaseEnum.DISPONIBLE)) {
				this.grille.getListCases().get(this.indiceCaseH).get(i)
						.setEtatActuel(CaseEnum.CONTAMINEE);
			}
		}
	}

	public void contaminationBiaisGauche() {
		int i = 0;
		int ajout = 0;

		/* vers le bas droit */
		for (int j = indiceCaseH + 1; j < 10; j++) {
			try {
				if (j % 2 == 1) {
					ajout++;
				}
				if (this.grille.getListCases().get(j)
						.get(this.indiceCaseV + ajout).getEtatActuel()
						.equals(CaseEnum.DESACTIVEE)) {
					break;
				} else if (this.grille.getListCases().get(j)
						.get(this.indiceCaseV + ajout).getEtatActuel()
						.equals(CaseEnum.DISPONIBLE)) {

					this.grille.getListCases().get(j)
							.get(this.indiceCaseV + ajout)
							.setEtatActuel(CaseEnum.CONTAMINEE);
				}
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}

		ajout = 0;
		/* TODO vers le haut gauche */
		for (int j = indiceCaseH - 1; j >= 0; j--) {
			try {
				if (j % 2 == 0) {
					ajout++;
				}
				if (this.grille.getListCases().get(j)
						.get(this.indiceCaseV - ajout).getEtatActuel()
						.equals(CaseEnum.DESACTIVEE)) {
					break;
				} else if (this.grille.getListCases().get(j)
						.get(this.indiceCaseV - ajout).getEtatActuel()
						.equals(CaseEnum.DISPONIBLE)) {

					this.grille.getListCases().get(j)
							.get(this.indiceCaseV - ajout)
							.setEtatActuel(CaseEnum.CONTAMINEE);
				}
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}

	}

	public void contaminationBiaisDroit() {

		int ajout = 0;
		/* vers le haut droit */
		for (int j = indiceCaseH - 1; j >= 0; j--) {
			try {
				if (j % 2 == 1) {
					ajout--;
				}
				if (this.grille.getListCases().get(j)
						.get(this.indiceCaseV - ajout).getEtatActuel()
						.equals(CaseEnum.DESACTIVEE)) {
					break;
				} else if (this.grille.getListCases().get(j)
						.get(this.indiceCaseV - ajout).getEtatActuel()
						.equals(CaseEnum.DISPONIBLE)) {

					this.grille.getListCases().get(j)
							.get(this.indiceCaseV - ajout)
							.setEtatActuel(CaseEnum.CONTAMINEE);
				}
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}

		ajout = 0;
		/* TODO bas gauche */
		for (int j = indiceCaseH + 1; j < 10; j++) {
			try {
				if (j % 2 == 0) {
					ajout++;
				}
				if (this.grille.getListCases().get(j)
						.get(this.indiceCaseV - ajout).getEtatActuel()
						.equals(CaseEnum.DESACTIVEE)) {
					break;
				} else if (this.grille.getListCases().get(j)
						.get(this.indiceCaseV - ajout).getEtatActuel()
						.equals(CaseEnum.DISPONIBLE)) {

					this.grille.getListCases().get(j)
							.get(this.indiceCaseV - ajout)
							.setEtatActuel(CaseEnum.CONTAMINEE);
				}
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}

	}

}
