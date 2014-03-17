package et3.grille;

import java.util.ArrayList;

import et3.grille.cases.Case;
import et3.grille.cases.CaseEnum;
import et3.sauvegarde.PropertyAcces;

public class Grille {

	private ArrayList<ArrayList<Case>> listCases;

	public Grille(ArrayList<ArrayList<Case>> listCases) {
		this.listCases = listCases;
	}

	public ArrayList<ArrayList<Case>> getListCases() {
		return listCases;
	}

	public void setListCases(ArrayList<ArrayList<Case>> listCases) {
		this.listCases = listCases;
	}

	/**
	 * 
	 * @param nom du fichier .properties
	 * @return Grille remplie
	 */
	public static Grille buildGrid(String filename){

		Grille grille = new Grille(new ArrayList<ArrayList<Case>>());
		ArrayList<Case> ligne = new ArrayList<Case>();
		Case c = new Case(CaseEnum.DESACTIVEE, CaseEnum.DESACTIVEE, 0, 0);
		
		String caseGrille = "";
		String typeCase;
		CaseEnum caseEnum = null;

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 7; j++) {
				caseGrille = String.valueOf(j) + "," + String.valueOf(i);
				c = Case.getPositions(j, i);
				typeCase = PropertyAcces.retrieveProperties(filename, caseGrille);
				if (typeCase.equals(CaseEnum.DESACTIVEE.toString())) {
					caseEnum = CaseEnum.DESACTIVEE;
				} else if (typeCase.equals(CaseEnum.DISPONIBLE.toString())) {
					caseEnum = CaseEnum.DISPONIBLE;
				}

				c.setEtatInitial(caseEnum);
				c.setEtatActuel(caseEnum);

				ligne.add(c);
			}
			grille.getListCases().add(ligne);
		}

		return grille;
	}


}
