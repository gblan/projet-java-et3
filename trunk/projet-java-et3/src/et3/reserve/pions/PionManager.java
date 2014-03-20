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
		contamination(pion);
	}

	public void deploiementHorizontal() {
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

	public void deploiementBiaisGauche() {

	}

	public void deploiementBiaisDroit() {

	}

	public void contamination(Pion p) {
		switch (p.getTypePion()) {
		case TYPE1:
			deploiementHorizontal();
			break;
		case TYPE2:
			deploiementBiaisDroit();
			break;
		case TYPE3:
			deploiementBiaisGauche();
			break;
		case TYPE4:
			deploiementBiaisGauche();
			deploiementHorizontal();
			break;
		case TYPE5:
			deploiementBiaisDroit();
			deploiementHorizontal();
			break;
		case TYPE6:
			deploiementBiaisDroit();
			deploiementBiaisGauche();
			break;
		case TYPE7:
			deploiementHorizontal();
			deploiementBiaisDroit();
			deploiementBiaisGauche();
			break;
		default:
			break;
		}

	}
}
