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
		this.listeNiveau = new ArrayList<Jeu>();
		buildLevels();
	}

	private void buildLevels() {
		for (int i = 1; i < 100; i++) {
			String str = "level" + i + ".properties";
			File properties = new File(str);
			if (properties.isFile()) {
				Jeu jeu = new Jeu(Grille.buildGrid(str),
						Reserve.buildReserve(str));
				this.listeNiveau.add(jeu);
			}
		}
	}

	public ArrayList<Jeu> getListeNiveau() {
		return listeNiveau;
	}

	public void setListeNiveau(ArrayList<Jeu> listeNiveau) {
		this.listeNiveau = listeNiveau;
	}

	@Override
	public String toString() {
		String res = "";
		int i = 1;
		res += "Niveaux : \n";

		for (Jeu j : this.listeNiveau) {
			res += "\t Grille num " + i + ", Reserve : "
					+ j.getReserve().getPions().size()+"\n";
			i++;
		}
		return res;
	}
}
