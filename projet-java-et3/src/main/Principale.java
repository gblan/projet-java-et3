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
import et3.grille.cases.Case;
import et3.grille.cases.CaseEnum;
import et3.jeu.Jeu;
import et3.jeu.JeuListener;
import et3.menus.Menu;
import et3.reserve.Reserve;
import et3.reserve.pions.Pion;
import et3.reserve.pions.PionManager;

public class Principale extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

		String level = "level1.properties";

		// DEBUT TEST
		Grille grille = Grille.buildGrid(level);
		Reserve reserve = Reserve.buildReserve(level);

		// FIN TEST

		JeuListener jeuListener = new JeuListener(grille, reserve);
		jeuListener.setJeu(new Jeu(grille, reserve));
		jeuListener.getJeu().setBackground(Color.WHITE);
		jeuListener.getJeu().setPreferredSize(new Dimension(width, height));
		jeuListener.getJeu().addMouseListener(jeuListener.selectionnerPions);
		jeuListener.getJeu().addMouseMotionListener(
				jeuListener.selectionnerPionsMotion);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane.add(jeuListener.getJeu());
		pack();
		setVisible(true);

	}

	public static void main(String[] arg) {
		// Principale p1 = new Principale("Sporos", 300, 500);
		Menu m = new Menu("Sporos");
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}