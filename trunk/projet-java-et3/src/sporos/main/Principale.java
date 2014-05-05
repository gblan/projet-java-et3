package sporos.main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import sporos.grille.Grille;
import sporos.grille.GrilleEnum;
import sporos.jeu.JeuListener;
import sporos.jeu.JeuModel;
import sporos.jeu.JeuView;
import sporos.menus.ImageImplement;
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
	public Principale(int numLevel, int width, int height,GrilleEnum taille, boolean myLevels) {
		super("Sporos, niveau : " + numLevel);
//		setBounds(300, 500, 10, 100);
		setSize(width, height);
		
		Container pane = getContentPane();
		pane.setBackground(Color.black);
		pane.setLayout(new FlowLayout());


		String level = "level" + numLevel + ".properties";

		Grille grille = null;
		Reserve reserve = null;
		// DEBUT TEST
		if(myLevels){
			grille = Grille.buildGrid("levels/myLevels/" + level, taille);
			reserve = Reserve.buildReserve("levels/myLevels/" + level, taille);
		}else{
			grille = Grille.buildGrid("levels/" + level, taille);
			reserve = Reserve.buildReserve("levels/" + level, taille);
		}
		// FIN TEST

		JeuModel jeu = new JeuModel(numLevel, grille, reserve);
		JeuView jeuView = new JeuView(jeu);
		JeuListener jeuListener = new JeuListener(jeu, jeuView,this);

		jeuListener.getJeuView().setPreferredSize(new Dimension(width, height));
		jeuListener.getJeuView().addMouseListener(
				jeuListener.getSelectionnerPions());
		jeuListener.getJeuView().addMouseMotionListener(
				jeuListener.getSelectionnerPionsMotion());
		jeuListener.getJeuView().getBtnMenuContextuel().addMouseListener(jeuListener.getSelectionnerMenuContextuel());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pane.add(jeuListener.getJeuView());
//		System.out.println("is corect grid : "+jeu.isCorrectGrid(15));
//		pack();
		setLocationRelativeTo(null);
		setVisible(true);
        setResizable(false);

	}
	
	public void kill(){
		this.dispose();
	}

}