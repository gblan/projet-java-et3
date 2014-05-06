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
						if (caseJeu.getEtatActuel().equals(CaseEnum.VIDE)) {
							caseJeu.setEtatActuel(CaseEnum.DISPONIBLE);
						} else {
							caseJeu.setEtatActuel(CaseEnum.VIDE);
						}
					}
				}
			}
			jeuView.repaint();
		}

	};

	public ActionListener getValidateButtonListener() {
		return validateButtonListener;
	}

	private ActionListener validateButtonListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			DialogChoixBoutons jdialog = new DialogChoixBoutons(new JDialog(), jeuView);
			jdialog.setVisible(true);
		}

	};

}
