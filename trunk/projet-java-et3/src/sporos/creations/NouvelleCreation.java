package sporos.creations;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;

import sporos.grille.Grille;
import sporos.jeu.JeuModel;
import sporos.jeu.JeuView;
import sporos.reserve.Reserve;

public class NouvelleCreation extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NouvelleCreation(){
		super("Creation de niveau");
		setBounds(300, 100, 0, 0);
		setSize(300	, 500);
		Container pane = getContentPane();
		pane.setLayout(new FlowLayout());


		// DEBUT TEST
		Grille grille = Grille.buildEmptyGrid();
		Reserve reserve = Reserve.buildEmptyReserve();

		// FIN TEST

		JeuModel jeu = new JeuModel(0, grille, reserve);
		JeuView jeuView = new JeuView(jeu);
		GrilleCreationListener creationlistener = new GrilleCreationListener();

		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
