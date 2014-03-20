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

	}

	public void contaminationBiaisDroit() {

	}

}
