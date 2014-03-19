package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.JFrame;

import et3.grille.Grille;
import et3.grille.cases.CaseEnum;
import et3.jeu.Jeu;
import et3.reserve.Reserve;
import et3.reserve.pions.Pion;
import et3.reserve.pions.PionEnum;

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
			for (int i = 0; i < jeu.getReserve().getPions().size(); i++) {
				if (jeu.getReserve().getPions().get(i)
						.contains(clique_x, clique_y)) {
					jeu.setPionSelectionne(jeu.getReserve().getPions().get(i));
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
				for (int i = 0; i < jeu.getReserve().getPions().size(); i++) {
					caseSurvoleeListener(jeu.getReserve().getPions().get(i));
				}
				jeu.repaint();

			}
		}
	};

	public void caseSurvoleeListener(Pion p) {
		int PionSurvoleX = 0;
		int PionSurvoleY = 0;
		for (int j = 0; j < jeu.getGrille().getListCases().size(); j++) {
			for (int k = 0; k < jeu.getGrille().getListCases().get(j).size(); k++) {

				if ((jeu.getGrille().getListCases().get(j).get(k).intersect(p))
						&& (!jeu.getGrille().getListCases().get(j).get(k)
								.getEtatActuel().toString()
								.equals(CaseEnum.DESACTIVEE.toString()))) {

					/*
					 * Calcul de la distance entre la case et le pion En x
					 */
					int caseX = jeu.getGrille().getListCases().get(j).get(k)
							.getX();
					int centreCaseX = caseX
							+ (jeu.getGrille().getListCases().get(j).get(k)
									.getHeight() / 10);
					int centrePionX = p.getCenter_x();
					int distance_x = Math.abs(centrePionX - centreCaseX);

					/* En y */
					int caseY = jeu.getGrille().getListCases().get(j).get(k)
							.getY();
					int centreCaseY = caseY
							+ (jeu.getGrille().getListCases().get(j).get(k)
									.getHeight() / 10)
							+ (jeu.getGrille().getListCases().get(j).get(k)
									.getHeight() / 20);
					int centrePionY = p.getCenter_y();
					int distance_y = Math.abs(centrePionY - centreCaseY);

					if (distance_x == Math.max(distance_x, PionSurvoleX)
							&& distance_y == Math.max(distance_y, PionSurvoleY)) {
						jeu.getGrille().getListCases().get(j).get(k)
								.setEtatActuel(CaseEnum.POTENTIELLESURVOLEE);
						PionSurvoleX = distance_x;
						PionSurvoleY = distance_y;
						
					}
					return;
					/* Propagation de chaque pion à apeller ici */
					// jeu.paint(getGraphics());
				} else if (!jeu.getGrille().getListCases().get(j).get(k)
						.intersect(p)
						&& (jeu.getGrille().getListCases().get(j).get(k)
								.getEtatActuel().toString()
								.equals(CaseEnum.POTENTIELLESURVOLEE.toString()))) {
					jeu.getGrille()
							.getListCases()
							.get(j)
							.get(k)
							.setEtatActuel(
									jeu.getGrille().getListCases().get(j)
											.get(k).getEtatInitial());
					
				}

			}
		}
	}

	/**
	 * 
	 * @param title
	 * @param width
	 * @param height
	 * @throws FileNotFoundException
	 */
	public Principale(String title, int width, int height) {
		super(title);
		setBounds(300, 100, 0, 0);
		setSize(width - 100, height - 100);
		Container pane = getContentPane();
		pane.setLayout(new FlowLayout());

		// DEBUT TEST
		Grille grille = Grille.buildGrid("level1.properties");

		Reserve reserve = Reserve.buildReserve("level1.properties");

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

	}

}