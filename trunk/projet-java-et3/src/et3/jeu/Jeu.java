package et3.jeu;

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import et3.grille.Grille;
import et3.pions.Pion;

public class Jeu extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * En attendant les .properties pour Tester l'affichage A MODIFIER
	 */
	private Grille grille;
	private ArrayList<Pion> reserve;
	private Pion pionSelectionne;

	/*
	 * FIN A MODIFIER
	 */

	public Jeu(Grille grille, ArrayList<Pion> reserve) {
		super();
		this.grille = grille;
		this.reserve = reserve;
	}

	public ArrayList<Pion> getReserve() {
		return reserve;
	}

	public void setReserve(ArrayList<Pion> reserve) {
		this.reserve = reserve;
	}

	public Pion getPionSelectionne() {
		return pionSelectionne;
	}

	public void setPionSelectionne(Pion pionSelectionne) {
		this.pionSelectionne = pionSelectionne;
	}

	public Grille getGrille() {
		return grille;
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}

	public void paint(Graphics graphics) {

		Graphics2D g = (Graphics2D) graphics;
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		for (int i = 0; i < this.grille.getListCases().size(); i++) {
			for (int j = 0; j < grille.getListCases().get(i).size(); j++) {
				grille.getListCases().get(i).get(j).paint(graphics);
			}
		}
		for (int i = 0; i < this.reserve.size(); i++) {
			reserve.get(i).paint(graphics);
		}
		g.setStroke(new BasicStroke(2f));
		if (this.pionSelectionne != null) {
			this.pionSelectionne.paint(graphics);
		}
	}

}
