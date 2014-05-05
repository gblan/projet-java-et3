package sporos.creations;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import sporos.grille.GrilleEnum;
import sporos.grille.cases.CaseEnum;
import sporos.grille.cases.CaseModel;
import sporos.jeu.JeuView;
import sporos.reserve.pions.PionEnum;
import sporos.reserve.pions.PionModel;

public class CreationListener {

	private JeuView jeuView;
	private int cliqueX;
	private int cliqueY;

	public CreationListener(JeuView jeuView) {

		this.setJeuView(jeuView);
	}

	public JeuView getJeuView() {
		return jeuView;
	}

	public void setJeuView(JeuView jeuView) {
		this.jeuView = jeuView;
	}

	public MouseListener getGridCreationListener() {
		return gridCreationListener;
	}

	public int getCliqueX() {
		return cliqueX;
	}

	public void setCliqueX(int cliqueX) {
		this.cliqueX = cliqueX;
	}

	public int getCliqueY() {
		return cliqueY;
	}

	public void setCliqueY(int cliqueY) {
		this.cliqueY = cliqueY;
	}

	public KeyListener getKey() {
		return key;
	}

	private MouseAdapter gridCreationListener = new MouseAdapter() {
		public void mousePressed(MouseEvent evt) {
			setCliqueX(evt.getX());
			setCliqueY(evt.getY());
			PionModel pion = new PionModel(PionEnum.TYPE1, getCliqueX() - 20,
					getCliqueY() - 20, 0, 0, GrilleEnum.GRAND);
			for (ArrayList<CaseModel> alCase : jeuView.getJeu().getGrille()
					.getListCases()) {

				for (CaseModel caseJeu : alCase) {
					if (caseJeu.intersect(pion)) {
						if (caseJeu.getEtatActuel().equals(CaseEnum.DISPONIBLE)) {
							caseJeu.setEtatActuel(CaseEnum.DESACTIVEE);
						} else {
							caseJeu.setEtatActuel(CaseEnum.DISPONIBLE);
						}
					}
				}
			}
			jeuView.repaint();
		}

	};

	private KeyListener key = new KeyListener() {

		@Override
		public void keyPressed(KeyEvent arg0) {

			if ((arg0.getKeyCode() == KeyEvent.VK_ENTER)) {
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

	};

	public ActionListener getValidateButtonListener() {
		return validateButtonListener;
	}

	private ActionListener validateButtonListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JDialog dialog = new JDialog();
			JOptionPane optionPane = new JOptionPane();
			optionPane.setMessage("");
			optionPane.setMessageType(JOptionPane.PLAIN_MESSAGE);

			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(7, 2));
			String[] nb = { "0", "1", "2", "3" };

			ArrayList<JComboBox> tab = new ArrayList<JComboBox>();
			for (int i = 0; i < 7; i++) {
				tab.add(new JComboBox(nb));
			}

			for (int i = 1; i < 8; i++) {
				try {
					panel.add(new JLabel(new ImageIcon(ImageIO.read(new File(
							"resources/Pion_" + i + ".png")))));
				} catch (IOException e) {
					e.printStackTrace();
				}
				panel.add(tab.get(i - 1));
			}
			optionPane.add(panel);
			optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);

			dialog = optionPane.createDialog(null, "Menu Contextuel");
			dialog.setVisible(true);
		}
	};

}
