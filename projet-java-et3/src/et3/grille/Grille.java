package et3.grille;

import java.util.ArrayList;

import et3.grille.cases.Case;
import et3.grille.cases.CaseEnum;
import et3.sauvegarde.Sauvegarde;

public class Grille {

	public static ArrayList<ArrayList<Case>> buildGrid(String filename) {
		ArrayList<ArrayList<Case>> grille = new ArrayList<ArrayList<Case>>();
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
			grille.add(ligne);
		}

		return grille;
	}

}