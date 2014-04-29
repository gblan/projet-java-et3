package deploiment;



import et3.grille.Grille;
import et3.reserve.pions.PionModel;

public abstract class Deploiment {
	
	private Grille grille;
	
	public Deploiment(Grille grille) {
		super();
		this.grille = grille;
	}


	public Grille getGrille() {
		return grille;
	}


	public void setGrille(Grille grille) {
		this.grille = grille;
	}




	/**
	 * 
	 * @param pion
	 * @return swich sur chaque type de pion et appelle les propagations
	 *         necessaires
	 */
	public void deploimentPion(PionModel p) {
		switch (p.getTypePion()) {
		case TYPE1:
			deploimentHorizontal(p);
			break;
		case TYPE2:
			deploimentMontante(p);
			break;
		case TYPE3:
			deploimentDescendante(p);
			break;
		case TYPE4:
			deploimentDescendante(p);
			deploimentHorizontal(p);
			break;
		case TYPE5:
			deploimentMontante(p);
			deploimentHorizontal(p);
			break;
		case TYPE6:
			deploimentMontante(p);
			deploimentDescendante(p);
			break;
		case TYPE7:
			deploimentHorizontal(p);
			deploimentMontante(p);
			deploimentDescendante(p);
			break;
		default:
			break;
		}
	}
	
	/**
	 * @return propagation du pion vers la gauche puis vers la droite
	 */
	private void deploimentHorizontal(PionModel p) {
		deploimentGauche(p);
		deploimentDroite(p);
	}

	private void deploimentMontante(PionModel p) {
		deploimentBasGauche(p);
		deploimentHautDroite(p);
	}

	private void deploimentDescendante(PionModel p) {
		deploimentHautGauche(p);
		deploimentBasDroit(p);
	}
	
	protected abstract void deploimentGauche(PionModel p);
	
	protected abstract void deploimentDroite(PionModel p);

	/**
	 * @return propagation du pion vers le haut gauche puis vers le bas droite
	 */
	protected abstract void deploimentBasDroit(PionModel p);

	protected abstract void deploimentHautGauche(PionModel p);

	/**
	 * @return propagation du pion vers le haut droit puis vers le bas gauche
	 */
	protected abstract void deploimentHautDroite(PionModel p);
	

	protected abstract void deploimentBasGauche(PionModel p);



}
