package et3.reserve.pions;

import et3.grille.Grille;
import et3.grille.cases.CaseEnum;

/* Une méthode par type de déploiement */
/* chaque pion apelle 1 ou plusieurs méthodes de déploiement */
public class PionManager {

	private Grille grille;
	private Pion pion;

	public PionManager(Grille grille, Pion pion) {
		super();
		this.grille = grille;
		this.pion = pion;
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
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 7; j++) {
				if (this.grille.getListCases().get(i).get(j).intersect(pion)) {
					for (int k = 0; k < 7; k++) {
						this.grille.getListCases().get(i).get(k)
								.setEtatActuel(CaseEnum.CONTAMINEE);
					}
					return;
				}
			}
		}
	}

	public void contaminationBiaisGauche() {

	}

	public void contaminationBiaisDroit() {

	}

}
