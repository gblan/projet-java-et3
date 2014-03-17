package et3.menus;

import java.io.File;
import java.util.ArrayList;

import et3.grille.Grille;
import et3.jeu.Jeu;
import et3.reserve.Reserve;

public class Niveaux {

	private ArrayList<Jeu> listeNiveau;

	/**
	 * @return Constructeur de niveaux
	 */
	public Niveaux() {
		for (int i = 1; i < 100; i++) {
			String str = "level"+i+".properties";
			File properties = new File(str);
			if(properties.isFile()){
				System.out.println("Bite");
				Jeu jeu = new Jeu(Grille.buildGrid(str),Reserve.buildReserve(str));
				System.out.println(jeu.getGrille().getListCases().get(i).get(i).toString());
				listeNiveau.add(jeu);
			}
		}
	}

	public ArrayList<Jeu> getListeNiveau() {
		return listeNiveau;
	}

	public void setListeNiveau(ArrayList<Jeu> listeNiveau) {
		this.listeNiveau = listeNiveau;
	}
}
