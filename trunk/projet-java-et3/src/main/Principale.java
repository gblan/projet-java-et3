package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.FileNotFoundException;

import javax.swing.JFrame;

import et3.grille.Grille;
import et3.grille.cases.CaseEnum;
import et3.jeu.Jeu;
import et3.menus.Menu;
import et3.reserve.Reserve;
import et3.reserve.pions.PionManager;

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
					for (int j = 0; j < jeu.getGrille().getListCases().size(); j++) {
					 for (int k = 0; k < jeu.getGrille().getListCases().get(j).size();k++) {
						 if ( jeu.getGrille().getListCases().get(j).get(k).contains(clique_x, clique_y)) {
							 jeu.getGrille().getListCases().get(j).get(k).setEtatActuel(CaseEnum.DISPONIBLE);
						 }
					 }
					}
					jeu.setPionSelectionne(jeu.getReserve().getPions().get(i));
					jeu.repaint();
				}
			}
		}

		public void mouseReleased(MouseEvent evt) {

			if (jeu.getPionSelectionne() != null) {
				// Si il n'y a pas de case selectionne le pion retourne dans la
				// reserve
				if (jeu.getGrille().getListCases().get(jeu.getIndiceCaseH())
						.get(jeu.getIndiceCaseV()) == null
						|| !(jeu.getGrille().getListCases()
								.get(jeu.getIndiceCaseH())
								.get(jeu.getIndiceCaseV()).intersect(jeu
								.getPionSelectionne()))
						|| jeu.getGrille().getListCases()
								.get(jeu.getIndiceCaseH())
								.get(jeu.getIndiceCaseV()).getEtatActuel()
								.toString().equals(CaseEnum.OCCUPEE.toString())) {
					/* A FAIRE deplacement vers point initial */
					jeu.getPionSelectionne().setCenter_x(
							jeu.getPionSelectionne().getxInitial());
					jeu.getPionSelectionne().setCenter_y(
							jeu.getPionSelectionne().getyInitial());
					jeu.setPionSelectionne(null);


				}

				else {
					// sinon on le positionne sur la case
					jeu.getPionSelectionne().setCenter_x(
							jeu.getGrille().getListCases()
									.get(jeu.getIndiceCaseH())
									.get(jeu.getIndiceCaseV()).getX() + 3);
					jeu.getPionSelectionne().setCenter_y(
							jeu.getGrille().getListCases()
									.get(jeu.getIndiceCaseH())
									.get(jeu.getIndiceCaseV()).getY() + 4);
					jeu.getGrille().getListCases().get(jeu.getIndiceCaseH())
							.get(jeu.getIndiceCaseV())
							.setEtatActuel(CaseEnum.OCCUPEE);
					
					PionManager pm = new PionManager(jeu.getGrille(), jeu.getPionSelectionne(), jeu.getIndiceCaseH(),jeu.getIndiceCaseV(),false);
					jeu.setPionSelectionne(null);

				}
			}
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
				
				PionManager pm = new PionManager(jeu.getGrille(), jeu.getPionSelectionne(), jeu.getIndiceCaseH(),jeu.getIndiceCaseV(),true);

				jeu.repaint();

			}
		}
	};

	public void caseSurvoleeListener() {

		for (int j = 0; j < jeu.getGrille().getListCases().size(); j++) {
			for (int k = 0; k < jeu.getGrille().getListCases().get(j).size(); k++) {

				if (jeu.getGrille().getListCases().get(j).get(k)
						.intersect(jeu.getPionSelectionne())
						&& (!jeu.getGrille().getListCases().get(j).get(k)
								.getEtatActuel().toString()
								.equals(CaseEnum.DESACTIVEE.toString()))) {

					if (!jeu.getGrille().getListCases().get(j).get(k)
									.getEtatActuel().toString()
									.equals(CaseEnum.OCCUPEE.toString())) {
						
						jeu.getGrille().getListCases().get(j).get(k)
								.setEtatActuel(CaseEnum.POTENTIELLESURVOLEE);
						jeu.setIndiceCaseH(j);
						jeu.setIndiceCaseV(k);


					}
				} else if (!jeu.getGrille().getListCases().get(j).get(k)
						.getEtatActuel().toString()
						.equals(CaseEnum.OCCUPEE.toString()))   {
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		//Principale p1 = new Principale("Sporos", 300, 500);
		Menu m = new Menu("Sporos");
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}