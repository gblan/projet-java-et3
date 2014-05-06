package sporos.grille;

import java.util.ArrayList;
import java.util.List;

import sporos.grille.cases.CaseEnum;
import sporos.grille.cases.CaseModel;
import sporos.utils.PropertyAcces;

public class Grille {

	private List<ArrayList<CaseModel>> listCases;
	private GrilleEnum taille;
	private static int nbColonne;
	private static int nbLigne;

	public Grille(List<ArrayList<CaseModel>> arrayList, GrilleEnum taille) {
		this.listCases = arrayList;
		this.taille = taille;
		switch (taille) {
		case PETIT:
			nbColonne = 5;
			nbLigne = 7;
			break;
		case MOYEN:
			nbColonne = 7;
			nbLigne = 10;
			break;
		case GRAND:
			nbColonne = 9;
			nbLigne = 13;
			break;
		}
	}

	public List<ArrayList<CaseModel>> getListCases() {
		return listCases;
	}

	public GrilleEnum getTaille() {
		return taille;
	}

	public void setTaille(GrilleEnum taille) {
		this.taille = taille;
	}

	public static int getNbColonne() {
		return nbColonne;
	}

	public static void setNbColonne(int nbColonne) {
		Grille.nbColonne = nbColonne;
	}

	public static int getNbLigne() {
		return nbLigne;
	}

	public static void setNbLigne(int nbLigne) {
		Grille.nbLigne = nbLigne;
	}

	/**
	 * 
	 * @param filename
	 *            .properties
	 * @return Grille remplie a partir du .properties
	 */
	public static Grille buildGrid(String filename, GrilleEnum tailleGrille) {

		Grille grille = new Grille(new ArrayList<ArrayList<CaseModel>>(),
				tailleGrille);
		ArrayList<CaseModel> ligne = null;
		CaseModel c = new CaseModel(CaseEnum.DESACTIVEE, CaseEnum.DESACTIVEE,
				0, 0, tailleGrille);

		String caseGrille = "";
		String typeCase;
		CaseEnum caseEnum = null;
		System.out.println(tailleGrille.toString());
		for (int i = 0; i < nbLigne; i++) {
			ligne = new ArrayList<CaseModel>();
			for (int j = 0; j < nbColonne; j++) {
				caseGrille = j + "," + i;
				c = c.getPositions(j, i);
				
				typeCase = PropertyAcces.retrieveProperties(filename,
						caseGrille);
				if (typeCase.equals(CaseEnum.DESACTIVEE.toString())) {
					caseEnum = CaseEnum.DESACTIVEE;
				} else if (typeCase.equals(CaseEnum.DISPONIBLE.toString())) {
					caseEnum = CaseEnum.DISPONIBLE;
				} else {
					caseEnum = CaseEnum.DESACTIVEE;
				}
				c.setEtatInitial(caseEnum);
				c.setEtatActuel(caseEnum);

				ligne.add(c);
			}
			grille.getListCases().add(ligne);
		}

		return grille;
	}

	public static Grille buildEmptyGrid(GrilleEnum tailleGrille) {

		Grille grille = new Grille(new ArrayList<ArrayList<CaseModel>>(),
				tailleGrille);
		ArrayList<CaseModel> ligne = null;
		CaseModel c = new CaseModel(CaseEnum.DESACTIVEE, CaseEnum.DESACTIVEE,
				0, 0, tailleGrille);

		for (int i = 0; i < nbLigne; i++) {
			ligne = new ArrayList<CaseModel>();
			for (int j = 0; j < nbColonne; j++) {
				c = c.getPositions(j, i);
				c.setEtatInitial(CaseEnum.DESACTIVEE);
				c.setEtatActuel(CaseEnum.VIDE);
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
