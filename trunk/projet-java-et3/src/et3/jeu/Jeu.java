package et3.jeu;

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import et3.grille.Grille;
import et3.grille.cases.Case;
import et3.grille.cases.CaseEnum;
import et3.reserve.Reserve;
import et3.reserve.pions.Pion;
import et3.reserve.pions.PionEnum;

public class Jeu extends Component {

	private static final long serialVersionUID = 1L;

	private Grille grille;
	private Reserve reserve;
	private Pion pionSelectionne;
	private int indiceCaseH;
	private int indiceCaseV;
	private ArrayList<Pion> pionsEnJeu;

	/*
	 * TODO FIN A MODIFIER
	 */

	public Jeu(Grille grille, Reserve reserve) {
		super();
		this.grille = grille;
		this.reserve = reserve;
		this.setPionsEnJeu(new ArrayList<Pion>());
	}

	public Reserve getReserve() {
		return reserve;
	}

	public void setReserve(Reserve reserve) {
		this.reserve = reserve;
	}

	public int getIndiceCaseH() {
		return indiceCaseH;
	}

	public void setIndiceCaseH(int indiceCaseH) {
		this.indiceCaseH = indiceCaseH;
	}

	public int getIndiceCaseV() {
		return indiceCaseV;
	}

	public void setIndiceCaseV(int indiceCaseV) {
		this.indiceCaseV = indiceCaseV;
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

	public ArrayList<Pion> getPionsEnJeu() {
		return pionsEnJeu;
	}

	public void setPionsEnJeu(ArrayList<Pion> pionsEnJeu) {
		this.pionsEnJeu = pionsEnJeu;
	}

	/**
	 * @param graphics
	 *            on affiche le jeu en faisant appel à l'affichage de chaque
	 *            case et de chaque pion
	 */
	public void paint(Graphics graphics) {

		Graphics2D g = (Graphics2D) graphics;
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		for (int i = 0; i < this.grille.getListCases().size(); i++) {
			for (int j = 0; j < grille.getListCases().get(i).size(); j++) {
				grille.getListCases().get(i).get(j).paint(graphics);
			}
		}
		for (int i = 0; i < this.reserve.getPions().size(); i++) {
			reserve.getPions().get(i).paint(graphics);
		}
		reserve.paint(g);
		File file = new File("resources/icone_menu.png");
		try {
			BufferedImage img = ImageIO.read(file);
			graphics.drawImage(img, 230, 10, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.setStroke(new BasicStroke(2f));
		if (this.pionSelectionne != null) {
			this.pionSelectionne.paint(graphics);
		}
	}

	public boolean isFinish() {
		for (int i = 0; i < this.grille.getListCases().size(); i++) {
			for (int j = 0; j < grille.getListCases().get(i).size(); j++) {
				if (grille.getListCases().get(i).get(j).getEtatActuel()
						.equals(CaseEnum.DISPONIBLE)) {
					return false;
				}
			}
		}
		return true;
	}

}
