package sporos.grille;

import java.util.ArrayList;
import java.util.List;

import sporos.grille.cases.CaseEnum;
import sporos.grille.cases.CaseModel;
import sporos.utils.PropertyAcces;

public class Grille {

	private List<ArrayList<CaseModel>> listCases;

	public Grille(List<ArrayList<CaseModel>> arrayList) {
		this.listCases = arrayList;
	}

	public List<ArrayList<CaseModel>> getListCases() {
		return listCases;
	}

	/**
	 * 
	 * @param filename
	 *            .properties
	 * @return Grille remplie a partir du .properties
	 */
	public static Grille buildGrid(String filename) {

		Grille grille = new Grille(new ArrayList<ArrayList<CaseModel>>());
		ArrayList<CaseModel> ligne = null;
		CaseModel c = new CaseModel(CaseEnum.DESACTIVEE, CaseEnum.DESACTIVEE,
				0, 0);

		String caseGrille = "";
		String typeCase;
		CaseEnum caseEnum = null;

		for (int i = 0; i < 10; i++) {
			ligne = new ArrayList<CaseModel>();
			for (int j = 0; j < 7; j++) {
				caseGrille = j + "," + i;
				c = CaseModel.getPositions(j, i);
				typeCase = PropertyAcces.retrieveProperties(filename,
						caseGrille);
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

	public static Grille buildEmptyGrid() {

		Grille grille = new Grille(new ArrayList<ArrayList<CaseModel>>());
		ArrayList<CaseModel> ligne = null;
		CaseModel c = new CaseModel(CaseEnum.DESACTIVEE, CaseEnum.DESACTIVEE,
				0, 0);

		for (int i = 0; i < 10; i++) {
			ligne = new ArrayList<CaseModel>();
			for (int j = 0; j < 7; j++) {
				c = CaseModel.getPositions(j, i);
				c.setEtatInitial(CaseEnum.DESACTIVEE);
				c.setEtatActuel(CaseEnum.DESACTIVEE);
				ligne.add(c);
			}
			grille.getListCases().add(ligne);
		}

		return grille;
	}
	
	@Override
	public String toString() {
		String res = "";
		for (ArrayList<CaseModel> al : getListCases()) {
			for (CaseModel grille : al) {
				res += grille.toString() + "\n\n";
			}
		}

		return res;
	}

}
