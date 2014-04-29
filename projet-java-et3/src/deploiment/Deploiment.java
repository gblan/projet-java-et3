package deploiment;



import et3.grille.Grille;
import et3.reserve.pions.PionModel;

public abstract class Deploiment {
	
	private Grille grille;
	private int indiceCaseH;
	private int indiceCaseV;
	
	public Deploiment(Grille grille, 
			int indiceCaseH, int indiceCaseV) {
		super();
		this.grille = grille;
		this.indiceCaseH = indiceCaseH;
		this.indiceCaseV = indiceCaseV;
	}


	public Grille getGrille() {
		return grille;
	}


	public void setGrille(Grille grille) {
		this.grille = grille;
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

	/**
	 * 
	 * @param pion
	 * @return swich sur chaque type de pion et appelle les propagations
	 *         necessaires
	 */
	public void deploimentPion(PionModel p) {
		switch (p.getTypePion()) {
		case TYPE1:
			deploimentHorizontal();
			break;
		case TYPE2:
			deploimentMontante();
			break;
		case TYPE3:
			deploimentDescendante();
			break;
		case TYPE4:
			deploimentDescendante();
			deploimentHorizontal();
			break;
		case TYPE5:
			deploimentMontante();
			deploimentHorizontal();
			break;
		case TYPE6:
			deploimentMontante();
			deploimentDescendante();
			break;
		case TYPE7:
			deploimentHorizontal();
			deploimentMontante();
			deploimentDescendante();
			break;
		default:
			break;
		}
	}
	
	/**
	 * @return propagation du pion vers la gauche puis vers la droite
	 */
	private void deploimentHorizontal() {
		deploimentGauche();
		deploimentDroite();
	}

	private void deploimentMontante() {
		deploimentBasGauche();
		deploimentHautDroite();
	}

	private void deploimentDescendante() {
		deploimentHautGauche();
		deploimentBasDroit();
	}
	
	protected abstract void deploimentGauche();
	
	protected abstract void deploimentDroite();

	/**
	 * @return propagation du pion vers le haut gauche puis vers le bas droite
	 */
	protected abstract void deploimentBasDroit();

	protected abstract void deploimentHautGauche();

	/**
	 * @return propagation du pion vers le haut droit puis vers le bas gauche
	 */
	protected abstract void deploimentHautDroite();
	

	protected abstract void deploimentBasGauche();



}
