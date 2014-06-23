package sporos.main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.FileNotFoundException;

import javax.swing.JFrame;

import sporos.grille.Grille;
import sporos.grille.GrilleEnum;
import sporos.jeu.JeuListener;
import sporos.jeu.JeuModel;
import sporos.jeu.JeuView;
import sporos.reserve.Reserve;

public class Principale extends JFrame {

	private boolean myLevels;
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

	private void setMyLevels(boolean myLevels2) {
		this.myLevels = myLevels2;
	}

	public boolean isMyLevels() {
		return myLevels;
	}

	public Principale(int numLevel, int width, int height, GrilleEnum taille,
			boolean myLevels, boolean partieRapide) {

		super("Sporos, niveau : " + numLevel);
		// setBounds(300, 500, 10, 100);
		setMyLevels(myLevels);
		setSize(width, height);

		Container pane = getContentPane();
		pane.setBackground(Color.black);
		pane.setLayout(new FlowLayout());

		String level = "level" + numLevel + ".properties";

		Grille grille = null;
		Reserve reserve = null;
		// DEBUT TEST
		if (myLevels) {
			grille = Grille.buildGrid("levels/myLevels/" + level, taille);
			reserve = Reserve.buildReserve("levels/myLevels/" + level, taille);
		} else {
			grille = Grille.buildGrid("levels/" + level, taille);
			reserve = Reserve.buildReserve("levels/" + level, taille);
		}
		// FIN TEST

		JeuModel jeu = new JeuModel(numLevel, grille, reserve);
		JeuView jeuView = new JeuView(jeu);
		JeuListener jeuListener = new JeuListener(jeu, jeuView, this,
				partieRapide);

		jeuListener.getJeuView().setPreferredSize(new Dimension(width, height));
		jeuListener.getJeuView().addMouseListener(
				jeuListener.getSelectionnerPions());
		jeuListener.getJeuView().addMouseMotionListener(
				jeuListener.getSelectionnerPionsMotion());

		// System.out.println(jeu.isCorrectGrid(2));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane.add(jeuListener.getJeuView());

		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);

	}

	public void kill() {
		this.dispose();
	}

}