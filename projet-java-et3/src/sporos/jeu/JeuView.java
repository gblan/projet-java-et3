package sporos.jeu;

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import sporos.grille.cases.CaseModel;
import sporos.grille.cases.CaseView;
import sporos.reserve.pions.PionModel;
import sporos.reserve.pions.PionView;

public class JeuView extends Component {

	private JeuModel jeu;

	public JeuView(JeuModel jeu) {
		this.jeu = jeu;
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
		for (ArrayList<CaseModel> alCase : jeu.getGrille().getListCases()) {
			for (CaseModel cases : alCase) {
				CaseView caseView = new CaseView(cases);
				caseView.paint(graphics);
			}
		}
		for (PionModel pion : jeu.getReserve().getPions()) {
			PionView pionView = new PionView(pion);
			pionView.paint(graphics);
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
			PionView pionView = new PionView(jeu.getPionSelectionne());
			pionView.paint(graphics);
		}
	}
}
