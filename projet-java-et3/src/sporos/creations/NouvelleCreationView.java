package sporos.creations;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import sporos.grille.Grille;
import sporos.grille.GrilleEnum;
import sporos.jeu.JeuModel;
import sporos.jeu.JeuView;
import sporos.main.Principale;
import sporos.menus.MenuNiveaux;
import sporos.menus.MenuPrincipal;
import sporos.reserve.Reserve;
import sporos.utils.PropertyAcces;

public class NouvelleCreationView extends JFrame {

	private static final long serialVersionUID = 1L;

	public NouvelleCreationView() {
		super("Creation de niveau");
		setBounds(300, 100, 0, 0);
		Container pane = getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

		// DEBUT TEST
		Grille grille = Grille.buildEmptyGrid(GrilleEnum.MOYEN);
		Reserve reserve = Reserve.buildEmptyReserve(GrilleEnum.MOYEN);

		// FIN TEST

		JeuModel jeu = new JeuModel(0, grille, reserve);
		JeuView jeuView = new JeuView(jeu);
		CreationListener creationListener = new CreationListener(jeuView,this);

		creationListener.getJeuView().setBackground(Color.WHITE);
		creationListener.getJeuView().setPreferredSize(new Dimension(300, 500));
		creationListener.getJeuView().addMouseListener(
				creationListener.getGridCreationListener());
		pane.add(creationListener.getJeuView());

		JButton valider = new JButton("Valider");
		valider.addActionListener(creationListener.getValidateButtonListener());
		pane.add(valider);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
public static void buildMenuContextuel(final NouvelleCreationView p) {
		
		
		final JFrame dialog = new JFrame("Menu");
		dialog.setSize(100, 100);
//		JOptionPane optionPane = new JOptionPane();
//		optionPane.setMessage("");
//		optionPane.setMessageType(JOptionPane.PLAIN_MESSAGE);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 1));
		
		JButton btnRevenir = new JButton("Revenir");
		BufferedImage imgbtnRevenir = null;
		try {
			imgbtnRevenir = ImageIO.read(new File("resources/Revenir.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnRevenir = new JButton(new ImageIcon(imgbtnRevenir));
		btnRevenir.setBorder(BorderFactory.createEmptyBorder());
		btnRevenir.setContentAreaFilled(false);
		btnRevenir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}
		});
		JButton btnRecommencer = new JButton("Recommencer");
		BufferedImage imgbtnRecommencer = null;
		try {
			imgbtnRecommencer= ImageIO.read(new File("resources/Recommencer.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnRecommencer = new JButton(new ImageIcon(imgbtnRecommencer));
		btnRecommencer.setBorder(BorderFactory.createEmptyBorder());
		btnRecommencer.setContentAreaFilled(false);
		btnRecommencer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
				p.kill();
				NouvelleCreationView av = new NouvelleCreationView();
				av.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
		
		JButton btnNiveaux = new JButton("Niveaux");
		BufferedImage imgbtnNiveaux = null;
		try {
			imgbtnNiveaux = ImageIO.read(new File("resources/NiveauxRetour.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnNiveaux = new JButton(new ImageIcon(imgbtnNiveaux));
		btnNiveaux.setBorder(BorderFactory.createEmptyBorder());
		btnNiveaux.setContentAreaFilled(false);
		btnNiveaux.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
				p.kill();
				MesCreationsView av = new MesCreationsView("Liste des niveaux");
				av.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
		
		JButton btnMenu = new JButton("Menu");
		BufferedImage imgbtnMenu = null;
		try {
			imgbtnMenu = ImageIO.read(new File("resources/MenuPrincipalRetour.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnMenu = new JButton(new ImageIcon(imgbtnMenu));
		btnMenu.setBorder(BorderFactory.createEmptyBorder());
		btnMenu.setContentAreaFilled(false);
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
				p.kill();
				MenuPrincipal m = new MenuPrincipal();
				m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
		
		panel.add(btnRevenir);
		panel.add(btnRecommencer);
		panel.add(btnNiveaux);
		
		panel.add(btnMenu);
//		dialog.getContentPane().setOptionType(JOptionPane.DEFAULT_OPTION);
		dialog.getContentPane().add(panel);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		dialog.setResizable(false);
	}

public void kill(){
	this.dispose();
}


}
