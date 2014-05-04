package sporos.jeu;

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import sporos.grille.cases.CaseModel;
import sporos.grille.cases.CaseView;
import sporos.menus.ImageImplement;
import sporos.menus.MenuContextuel;
import sporos.reserve.pions.PionModel;
import sporos.reserve.pions.PionView;

public class JeuView extends Component {

	private JeuModel jeu;
	private JButton btnMenuContextuel;

	public JeuView(JeuModel jeu) {
		this.jeu = jeu;
		this.btnMenuContextuel = MenuContextuel.createButtonContextuel();
	}
	
	public JeuModel getJeu() {
		return jeu;
	}

	
	public void setJeu(JeuModel jeu) {
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

		getBtnMenuContextuel().paint(g);
		
		g.setStroke(new BasicStroke(2f));
		if (jeu.getPionSelectionne() != null) {
			PionView pionView = new PionView(jeu.getPionSelectionne());
			pionView.paint(graphics);
		}
		if (jeu.getPionRelache() != null){
			for (PionModel pion : jeu.getPionRelache()) {
				
				PionView pionView = new PionView(pion);
				pionView.paint(graphics);
			}
		}
	}
	
	public void buildMenuContextuel(){
		JDialog dialog = new JDialog();
	    JOptionPane optionPane = new JOptionPane();
	    optionPane.setMessage("");
	    optionPane.setMessageType(JOptionPane.PLAIN_MESSAGE);

	    JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(4,1));
	    String[] buttonTxt = {"Rejouer","Quitter Niveau","Quitter Jeu"};
	    JButton[] buttons = new JButton[buttonTxt.length];
	    for (int i = 0; i < buttonTxt.length; i++)
	    {
	        buttons[i] = new JButton(buttonTxt[i]);
	        panel.add(buttons[i]);
	    }
	    optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
	    optionPane.add(panel);
	    
	    dialog = optionPane.createDialog(null, "Menu Contextuel");
	}

	public JButton getBtnMenuContextuel() {
		return btnMenuContextuel;
	}

	public void setBtnMenuContextuel(JButton btnMenuContextuel) {
		this.btnMenuContextuel = btnMenuContextuel;
	}

}
