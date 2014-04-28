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
import et3.reserve.Reserve;
import et3.reserve.pions.Pion;

public class JeuView extends Component {
	
	private JeuModel jeu;
	
	public JeuView(JeuModel jeu) {
		this.jeu=jeu;
		// TODO Auto-generated constructor stub
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
		for (ArrayList<Case> alCase : jeu.getGrille().getListCases()) {
			for (Case grilleJeu : alCase) {
				grilleJeu.paint(graphics);
			}
		}
		for (Pion alPion : jeu.getReserve().getPions()) {
			alPion.paint(graphics);
		}
		jeu.getReserve().paint(g);
		File file = new File("resources/icone_menu.png");
		try {
			BufferedImage img = ImageIO.read(file);
			graphics.drawImage(img, 230, 10, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.setStroke(new BasicStroke(2f));
		if (jeu.getPionSelectionne() != null) {
			jeu.getPionSelectionne().paint(graphics);
		}
	}
}
