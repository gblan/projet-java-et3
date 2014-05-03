package sporos.creations;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import sporos.grille.Grille;
import sporos.jeu.JeuModel;
import sporos.jeu.JeuView;
import sporos.reserve.Reserve;

public class NouvelleCreation extends JFrame{

	private static final long serialVersionUID = 1L;

	public NouvelleCreation(){
		super("Creation de niveau");
		setBounds(300, 100, 0, 0);
		Container pane = getContentPane();
		pane.setLayout(new CardLayout());

		// DEBUT TEST
		Grille grille = Grille.buildEmptyGrid();
		Reserve reserve = Reserve.buildEmptyReserve();
		
		// FIN TEST

		JeuModel jeu = new JeuModel(0, grille, reserve);
		JeuView jeuView = new JeuView(jeu);
		CreationListener creationListener = new CreationListener(jeuView);

		creationListener.getJeuView().setBackground(Color.WHITE);
		creationListener.getJeuView().setPreferredSize(new Dimension(300, 500));
		creationListener.getJeuView().addMouseListener(creationListener.getGridCreationListener());
		creationListener.getJeuView().addKeyListener(creationListener.getKey());

		pane.add(creationListener.getJeuView());
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
