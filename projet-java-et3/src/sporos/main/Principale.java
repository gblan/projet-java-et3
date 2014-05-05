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
import sporos.utils.PropertyAcces;

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
	public Principale(int numLevel, int width, int height,GrilleEnum taille) {
		super("Sporos, niveau : " + numLevel);
		setBounds(300, 100, 0, 0);
		setSize(width - 100, height - 100);
		Container pane = getContentPane();
		pane.setLayout(new FlowLayout());

		String level = "level" + numLevel + ".properties";

		// DEBUT TEST
		Grille grille = Grille.buildGrid("levels/" + level, taille);
		Reserve reserve = Reserve.buildReserve("levels/" + level, taille);
		
		// FIN TEST

		JeuModel jeu = new JeuModel(numLevel, grille, reserve);
		JeuView jeuView = new JeuView(jeu);
		JeuListener jeuListener = new JeuListener(jeu, jeuView, this);

		jeuListener.getJeuView().setBackground(Color.WHITE);
		jeuListener.getJeuView().setPreferredSize(new Dimension(width, height));
		jeuListener.getJeuView().addMouseListener(
				jeuListener.getSelectionnerPions());
		jeuListener.getJeuView().addMouseMotionListener(
				jeuListener.getSelectionnerPionsMotion());
		jeuListener.getJeuView().getBtnMenuContextuel().addMouseListener(jeuListener.getSelectionnerMenuContextuel());

		PropertyAcces.saveCreatedGrid(grille, reserve);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane.add(jeuListener.getJeuView());
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
        setResizable(false);
        setOpacity(0.7f);

	}
	
	public void kill(){
		this.dispose();
	}

}