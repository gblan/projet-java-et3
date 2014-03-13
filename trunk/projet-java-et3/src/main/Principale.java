package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import et3.cases.Case;
import et3.cases.CaseEnum;
import et3.jeu.Jeu;
import et3.pions.Pions;
import et3.pions.PionsEnum;
import et3.sauvegarde.Sauvegarde;

public class Principale extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Jeu jeu;
	private int clique_x;
	private int clique_y;

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}

	/* Selection du pion a la souris */
	private MouseAdapter selectionnerPions = new MouseAdapter() {
		public void mousePressed(MouseEvent evt) {
			setClique_x(evt.getX());
			setClique_y(evt.getY());
			for (int i = 0; i < jeu.getReserve().size(); i++) {
				if (jeu.getReserve().get(i).contains(clique_x, clique_y)) {
					jeu.setPionSelectionne(jeu.getReserve().get(i));
					jeu.repaint();
				}
			}
		}

		public void mouseReleased(MouseEvent evt) {
			jeu.setPionSelectionne(null);
			jeu.repaint();
		}
	};

	private MouseMotionAdapter selectionnerPionsMotion = new MouseMotionAdapter() {
		/* Déplacement a la souris */
		public void mouseDragged(MouseEvent evt) {
			if (jeu.getPionSelectionne() != null) {
				int translate_x = evt.getX() - getClique_x();
				int translate_y = evt.getY() - getClique_y();
				jeu.getPionSelectionne().setCenter_x(
						jeu.getPionSelectionne().getCenter_x() + translate_x);
				jeu.getPionSelectionne().setCenter_y(
						jeu.getPionSelectionne().getCenter_y() + translate_y);
				setClique_x(evt.getX());
				setClique_y(evt.getY());
				caseSurvoleeListener();
				jeu.repaint();
			}
		}
	};

	public void caseSurvoleeListener() {

		for (int i = 0; i < jeu.getReserve().size(); i++) {
			for (int j = 0; j < jeu.getGrille().size(); j++) {
				for (int k = 0; k < jeu.getGrille().get(j).size(); k++) {
					if (jeu.getGrille().get(j).get(k)
							.intersect(jeu.getReserve().get(i))) {
						jeu.getGrille().get(j).get(k)
								.setEtat(CaseEnum.POTENTIELLESURVOLEE);
					} else {
						jeu.getGrille().get(j).get(k)
								.setEtat(CaseEnum.DISPONIBLE);
					}
				}
			}

		}

	}

	/**
	 * 
	 * @param x
	 *            position dans les properties
	 * @param y
	 *            position dans les properties
	 * @param posX
	 *            position exacte de la cellule
	 * @param posY
	 *            position exacte de la cellule
	 */

	private ArrayList<ArrayList<Case>> buildGrid(String filename) {
		ArrayList<ArrayList<Case>> grille = new ArrayList<ArrayList<Case>>();
		ArrayList<Case> ligne = new ArrayList<Case>();
		Case c = new Case(null, 0, 0);
		String caseGrille = "";
		String typeCase;
		CaseEnum caseEnum = null;

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 7; j++) {
				caseGrille = String.valueOf(j) + "," + String.valueOf(i);
				c = Case.getPositions(j, i);
				typeCase = Sauvegarde.retrieveProperties(filename, caseGrille);

				if (typeCase.equals(CaseEnum.DESACTIVEE.toString())) {
					caseEnum = CaseEnum.DESACTIVEE;
				} else if (typeCase.equals(CaseEnum.DISPONIBLE.toString())) {
					caseEnum = CaseEnum.DISPONIBLE;
				}
				
				c.setEtat(caseEnum);

				ligne.add(c);
			}
			grille.add(ligne);
		}

		return grille;
	}

	/**
	 * 
	 * @param title
	 * @param width
	 * @param height
	 */
	public Principale(String title, int width, int height) {
		super(title);
		setBounds(300, 100, 0, 0);
		setSize(width - 100, height - 100);
		Container pane = getContentPane();
		pane.setLayout(new FlowLayout());

		// DEBUT TEST
		
		ArrayList<ArrayList<Case>> grille = buildGrid("level1.properties");

		ArrayList<Pions> reserve = new ArrayList<Pions>();
		Pions p1 = new Pions(PionsEnum.TYPE6, 50, 10);
		Pions p2 = new Pions(PionsEnum.TYPE1, 90, 10);

		reserve.add(p1);
		reserve.add(p2);

		// listeCase.add(c2);
		// FIN TEST

		setJeu(new Jeu(grille, reserve));
		jeu.setBackground(Color.WHITE);
		jeu.setPreferredSize(new Dimension(width, height));
		jeu.addMouseListener(selectionnerPions);
		jeu.addMouseMotionListener(selectionnerPionsMotion);
		pane.add(jeu);
		pack();
		setVisible(true);

	}

	public int getClique_x() {
		return clique_x;
	}

	public void setClique_x(int clique_x) {
		this.clique_x = clique_x;
	}

	public int getClique_y() {
		return clique_y;
	}

	public void setClique_y(int clique_y) {
		this.clique_y = clique_y;
	}

	public static void main(String[] arg) {
		Principale p1 = new Principale("Sporos", 300, 500);
		p1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p1.setResizable(false);
		/*
		 * Sauvegarde s1 = new Sauvegarde(); s1.saveProperties("cheval",
		 * "blanc");
		 */
	}

}