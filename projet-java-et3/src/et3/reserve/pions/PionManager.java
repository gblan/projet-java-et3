package et3.reserve.pions;

public class PionManager {

	/* Une méthode par type de déploiement */
	/* chaque pion apelle 1 ou plusieurs méthodes de déploiement */

	public void deploiementHorizontal() {

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
