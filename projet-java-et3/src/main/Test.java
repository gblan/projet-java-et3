package main;

import et3.menus.Niveaux;

public class Test {

	public static void main(String[] args) {
		// String typeCase;
		// String caseGrille ="";
		// Case c = new Case(null, 0, 0);
		// CaseEnum caseEnum = null;
		//
		//
		// for (int i = 0; i < 10; i++) {
		// for (int j = 0; j < 7; j++) {
		// caseGrille = String.valueOf(j)+ "," + String.valueOf(i);
		// c = Case.getPositions(j, i);
		// typeCase = Sauvegarde.retrieveProperties("level1.properties",
		// caseGrille);
		//
		// if (typeCase.equals(CaseEnum.DESACTIVEE.toString())) {
		// caseEnum = CaseEnum.DESACTIVEE;
		// } else if (typeCase.equals(CaseEnum.DISPONIBLE.toString())) {
		// caseEnum = CaseEnum.DISPONIBLE;
		// }
		//
		// c.setEtat(caseEnum);
		// System.out.println(c.getEtat());
		// }
		// }

		// Reserve reserve = new Reserve();
		//
		// reserve = Reserve.buildReserve("level1.properties");
		//
		// System.out.println(reserve.getPions().get(2).getCenter_x());

		// Menu menu = new Menu("Sporos");
		Niveaux level = new Niveaux("");
		// System.out.println(level.getListeNiveau().toString());
		System.out.println(level.toString());
	}
}
