package et3_project;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JFrame;

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
	 * @param x position dans les properties
	 * @param y position dans les properties
	 * @param posX position exacte de la cellule
	 * @param posY position exacte de la cellule
	 */
	public void getPositions(int x, int y, int posX, int posY) {

		if (y % 2 == 0) {
			// Y PAIR
			posX = 20+40*x;
			posY = 80+35*y;
		} else {
			// Y IMPAIR
			posX = 40*x;
			posY = 80+35*y;
		}

	}

	public Principale(String title, int width, int height) {
		super(title);
		setBounds(300, 100, 0, 0);
		setSize(width - 100, height - 100);
		Container pane = getContentPane();
		pane.setLayout(new FlowLayout());

		// DEBUT TEST
		ArrayList<ArrayList<Case>> grille = new ArrayList<ArrayList<Case>>();
		ArrayList<Case> ligne1 = new ArrayList<Case>();
		ArrayList<Case> ligne2 = new ArrayList<Case>();
		ArrayList<Case> ligne3 = new ArrayList<Case>();
		Case c1 = new Case(CaseEnum.DISPONIBLE, 80, 150);
		Case c2 = new Case(CaseEnum.DISPONIBLE, 120, 150);
		Case c12 = new Case(CaseEnum.DISPONIBLE, 40, 150);
		Case c22 = new Case(CaseEnum.DISPONIBLE, 0, 150);
		Case c111 = new Case(CaseEnum.DISPONIBLE, 160, 150);
		Case c222 = new Case(CaseEnum.DISPONIBLE, 200, 150);
		Case c122 = new Case(CaseEnum.DISPONIBLE, 240, 150);
		ligne1.add(c1);
		ligne1.add(c2);
		ligne1.add(c12);
		ligne1.add(c22);
		ligne1.add(c111);
		ligne1.add(c222);
		ligne1.add(c122);
		grille.add(ligne1);
		Case c31 = new Case(CaseEnum.DISPONIBLE, 20, 185);
		Case c3 = new Case(CaseEnum.DISPONIBLE, 60, 185);
		Case c4 = new Case(CaseEnum.DISPONIBLE, 100, 185);
		Case c5 = new Case(CaseEnum.DISPONIBLE, 140, 185);
		Case c55 = new Case(CaseEnum.DISPONIBLE, 220, 185);
		Case c6 = new Case(CaseEnum.DISPONIBLE, 180, 185);
		Case c66 = new Case(CaseEnum.DISPONIBLE, 260, 185);
		ligne2.add(c3);
		ligne2.add(c4);
		ligne2.add(c5);
		ligne2.add(c6);
		ligne2.add(c31);
		ligne2.add(c55);
		ligne2.add(c66);
		grille.add(ligne2);
		Case c7 = new Case(CaseEnum.DISPONIBLE, 60, 395);
		Case c8 = new Case(CaseEnum.DISPONIBLE, 100, 395);
		ligne3.add(c7);
		ligne3.add(c8);
		grille.add(ligne3);

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