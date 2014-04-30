package sporos.main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.FileNotFoundException;

import javax.swing.JFrame;

import sporos.grille.Grille;
import sporos.jeu.JeuListener;
import sporos.jeu.JeuModel;
import sporos.jeu.JeuView;
import sporos.menus.Menu;
import sporos.reserve.Reserve;


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
	public Principale(String title, int numLevel, int width, int height) {
		super(title);
		setBounds(300, 100, 0, 0);
		setSize(width - 100, height - 100);
		Container pane = getContentPane();
		pane.setLayout(new FlowLayout());

		String level = "level" + numLevel + ".properties";

		// DEBUT TEST
		Grille grille = Grille.buildGrid("levels/" + level);
		Reserve reserve = Reserve.buildReserve("levels/" + level);

		// FIN TEST

		JeuModel jeu = new JeuModel(numLevel, grille, reserve);
		JeuView jeuView = new JeuView(jeu);
		JeuListener jeuListener = new JeuListener(jeu, jeuView);

		jeuListener.getJeuView().setBackground(Color.WHITE);
		jeuListener.getJeuView().setPreferredSize(new Dimension(width, height));
		jeuListener.getJeuView().addMouseListener(
				jeuListener.getSelectionnerPions());
		jeuListener.getJeuView().addMouseMotionListener(
				jeuListener.getSelectionnerPionsMotion());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane.add(jeuListener.getJeuView());
		pack();
		setVisible(true);

	}

	public static void main(String[] arg) {
		Menu m = new Menu("Sporos");
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}