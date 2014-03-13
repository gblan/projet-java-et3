package et3.grille;

import java.util.ArrayList;

import et3.grille.cases.Case;
import et3.grille.cases.CaseEnum;
import et3.sauvegarde.Sauvegarde;

public class Grille {

	private ArrayList<ArrayList<Case>> grille;	

	public ArrayList<ArrayList<Case>> getGrille() {
		return grille;
	}

	public void setGrille(ArrayList<ArrayList<Case>> grille) {
		this.grille = grille;
	}	

	public Grille(ArrayList<ArrayList<Case>> grille) {
		super();
		this.grille = grille;
	}

	/**
	 * 
	 * @param x
	 *            position dans les properties
	 * @param y
	 *            position dans les properties
	 * @param posX
	 *            position exacte de la cellule
	 * @param posY
	 *            position exacte de la cellule
	 */
	public static Grille buildGrid(String filename) {
		
		Grille grille = new Grille(new ArrayList<ArrayList<Case>>());
		ArrayList<Case> ligne = new ArrayList<Case>();
		Case c = new Case(null, 0, 0);
		String caseGrille = "";
		String typeCase;
		CaseEnum caseEnum = null;

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 7; j++) {
				caseGrille = String.valueOf(j) + "," + String.valueOf(i);
				c = Case.getPositions(j, i);
				typeCase = Sauvegarde.retrieveProperties(filename, caseGrille);

				if (typeCase.equals(CaseEnum.DESACTIVEE.toString())) {
					caseEnum = CaseEnum.DESACTIVEE;
				} else if (typeCase.equals(CaseEnum.DISPONIBLE.toString())) {
					caseEnum = CaseEnum.DISPONIBLE;
				}

				c.setEtat(caseEnum);

				ligne.add(c);
			}
			grille.getGrille().add(ligne);
		}

		return grille;
	}

}
