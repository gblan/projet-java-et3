package sporos.jeu;

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import sporos.grille.GrilleEnum;
import sporos.grille.cases.CaseModel;
import sporos.grille.cases.CaseView;
import sporos.main.Principale;
import sporos.menus.ImageImplement;
import sporos.menus.MenuContextuel;
import sporos.menus.MenuNiveaux;
import sporos.menus.MenuPrincipal;
import sporos.reserve.pions.PionModel;
import sporos.reserve.pions.PionView;
import sporos.utils.PropertyAcces;

public class JeuView extends Component {

	private JeuModel jeu;
	private JButton btnMenuContextuel;

	public JeuView(JeuModel jeu) {
		this.jeu = jeu;
		this.btnMenuContextuel = MenuContextuel.createButtonContextuel();
	}

	public JeuModel getJeu() {
		return jeu;
	}

	public void setJeu(JeuModel jeu) {
		this.jeu = jeu;
	}
	
	/**
	 * @param graphics
	 *            on affiche le jeu en faisant appel à l'affichage de chaque
	 *            case et de chaque pion
	 */
	public void paint(Graphics graphics) {

		Graphics2D g = (Graphics2D) graphics;
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.drawImage(new ImageIcon("resources/Background2.png").getImage(),0,0, null);
		for (ArrayList<CaseModel> alCase : jeu.getGrille().getListCases()) {
			for (CaseModel cases : alCase) {
				CaseView caseView = new CaseView(cases);
				caseView.paint(graphics);
			}
		}
		for (PionModel pion : jeu.getReserve().getPions()) {
			PionView pionView = new PionView(pion);
			pionView.paint(graphics);
		}
		jeu.getReserve().paint(g);

		getBtnMenuContextuel().paint(g);

		g.setStroke(new BasicStroke(2f));
		if (jeu.getPionSelectionne() != null) {
			PionView pionView = new PionView(jeu.getPionSelectionne());
			pionView.paint(graphics);
		}
		if (jeu.getPionRelache() != null) {
			for (PionModel pion : jeu.getPionRelache()) {

				PionView pionView = new PionView(pion);
				pionView.paint(graphics);
			}
		}
	}

	public static void buildMenuContextuel(final Principale p) {
		
		
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
				Principale av = new Principale(PropertyAcces.getCurrentLevel(),
						300, 500, GrilleEnum.MOYEN,false);
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
				MenuNiveaux av = new MenuNiveaux("Liste des niveaux");
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

	public JButton getBtnMenuContextuel() {
		return btnMenuContextuel;
	}

	public void setBtnMenuContextuel(JButton btnMenuContextuel) {
		this.btnMenuContextuel = btnMenuContextuel;
	}

}
