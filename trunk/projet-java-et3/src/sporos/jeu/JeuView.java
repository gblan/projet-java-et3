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
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import sporos.grille.cases.CaseModel;
import sporos.grille.cases.CaseView;
import sporos.reserve.pions.PionModel;
import sporos.reserve.pions.PionView;

public class JeuView extends Component {

	private JeuModel jeu;
	private JButton buttonMenuContextuel;

	public JeuView(JeuModel jeu) {
		this.jeu = jeu;
		this.buttonMenuContextuel = new JButton();
	}
	
	public JeuModel getJeu() {
		return jeu;
	}
	
	public JButton getButtonMenuContextuel() {
		return buttonMenuContextuel;
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

		printMenuContextuel().paint(g);
		
		g.setStroke(new BasicStroke(2f));
		if (jeu.getPionSelectionne() != null) {
			PionView pionView = new PionView(jeu.getPionSelectionne());
			pionView.paint(graphics);
		}
	}

	public JButton printMenuContextuel() {
		JButton btnMenuContextuel = getButtonMenuContextuel();
		BufferedImage imgMenuContextuel = null;
		try {
			imgMenuContextuel = ImageIO.read(new File(
					"resources/icone_menu.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		btnMenuContextuel = new JButton(new ImageIcon(imgMenuContextuel));
		btnMenuContextuel.setBorder(BorderFactory.createEmptyBorder());
		btnMenuContextuel.setContentAreaFilled(false);
		btnMenuContextuel.setBounds(0, 0, 500, 50);
		return btnMenuContextuel;
	}


}
