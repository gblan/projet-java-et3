package sporos.main;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
//		jeuListener.getJeuView().getBtnMenuContextuel().addMouseListener( new MouseAdapter() {
//		public void mousePressed(MouseEvent evt) {
//		System.out.println("lol");
////		jeuView.buildMenuContextuel();
//	}
//});
//		JButton btnMenuContextuel = new JButton();
//		BufferedImage imgMenuContextuel = null;
//		try {
//			imgMenuContextuel = ImageIO.read(new File(
//					"resources/icone_menu.png"));
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		btnMenuContextuel = new JButton(new ImageIcon(imgMenuContextuel));
//		btnMenuContextuel.setBorder(BorderFactory.createEmptyBorder());
//		btnMenuContextuel.setContentAreaFilled(false);
//		btnMenuContextuel.setBounds(20, 0, 520, 50);
//		
//		btnMenuContextuel.addMouseListener( new MouseAdapter() {
//			public void mousePressed(MouseEvent evt) {
//				System.out.println("lol");
////				jeuView.buildMenuContextuel();
//			}
//		});
		

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		pane.add(btnMenuContextuel);
		pane.add(jeuListener.getJeuView());
		
//		pack();
		setLocationRelativeTo(null);
		setVisible(true);
        setResizable(false);

	}
	
	public void kill(){
		this.dispose();
	}

}