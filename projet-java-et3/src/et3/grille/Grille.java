package et3.grille;

import java.util.ArrayList;
import java.util.List;

import et3.grille.cases.CaseEnum;
import et3.grille.cases.CaseModel;
import et3.sauvegarde.PropertyAcces;

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
