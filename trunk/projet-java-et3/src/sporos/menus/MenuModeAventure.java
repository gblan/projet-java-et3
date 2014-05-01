package sporos.menus;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import sporos.main.Principale;
import sporos.utils.PropertyAcces;

public class MenuModeAventure extends JFrame {
	private static final long serialVersionUID = 1L;

	public MenuModeAventure() {
		super("Mode Aventure");

		JLabel labelNiveauCourant = new JLabel();
		labelNiveauCourant.setText("Votre niveau actuel : "+PropertyAcces.getCurrentLevel());
		
		JButton btnContinuerPartie = new JButton("Continuer Partie");
		BufferedImage imgbtnContinuerPartie = null;
		try {
			imgbtnContinuerPartie = ImageIO.read(new File(
					"resources/continuer-partie.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnContinuerPartie = new JButton(new ImageIcon(imgbtnContinuerPartie));
		btnContinuerPartie.setBorder(BorderFactory.createEmptyBorder());
		btnContinuerPartie.setContentAreaFilled(false);
		btnContinuerPartie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Principale av = new Principale(PropertyAcces.getCurrentLevel(),
						300, 500);
			}
		});
		
		JButton btnNouvellePartie = new JButton("Nouvelle Partie");
		BufferedImage imgbtnNouvellePartie = null;
		try {
			imgbtnNouvellePartie = ImageIO.read(new File(
					"resources/nouvelle-partie.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnNouvellePartie = new JButton(new ImageIcon(imgbtnNouvellePartie));
		btnNouvellePartie.setBorder(BorderFactory.createEmptyBorder());
		btnNouvellePartie.setContentAreaFilled(false);
		btnNouvellePartie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

//				
				int effacement = JOptionPane.showConfirmDialog(null,
						"Ceci va effacer votre progression, êtes vous sur ?",
						"Attention",JOptionPane.WARNING_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
			    
				if (effacement == 0) {
					// OK
					PropertyAcces.saveProperties(1);
					setVisible(false);
					Principale av = new Principale(PropertyAcces.getCurrentLevel(),
							300, 500);
				}
				
			}
		});

		JButton btnListeNiveaux = new JButton("Liste des Niveaux");
		BufferedImage imgbtnListeNiveaux = null;
		try {
			imgbtnListeNiveaux = ImageIO.read(new File(
					"resources/liste-niveaux.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnListeNiveaux = new JButton(new ImageIcon(imgbtnListeNiveaux));
		btnListeNiveaux.setBorder(BorderFactory.createEmptyBorder());
		btnListeNiveaux.setContentAreaFilled(false);
		btnListeNiveaux.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				MenuNiveaux av = new MenuNiveaux("Liste des niveaux");
			}
		});

		JButton btnRetourMenu = new JButton("Menu Principal");
		BufferedImage imgbtnRetourMenu = null;
		try {
			imgbtnRetourMenu = ImageIO.read(new File(
					"resources/menu-principal.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnRetourMenu = new JButton(new ImageIcon(imgbtnRetourMenu));
		btnRetourMenu.setBorder(BorderFactory.createEmptyBorder());
		btnRetourMenu.setContentAreaFilled(false);
		btnRetourMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				MenuPrincipal av = new MenuPrincipal();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addGap(115)
						.addGroup(
								groupLayout
										.createParallelGroup(Alignment.LEADING)
										.addComponent(labelNiveauCourant)
										.addComponent(btnContinuerPartie)
										.addComponent(btnNouvellePartie)
										.addComponent(btnListeNiveaux)
										.addComponent(btnRetourMenu,
												GroupLayout.PREFERRED_SIZE, 69,
												GroupLayout.PREFERRED_SIZE))
						.addContainerGap(115, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.TRAILING).addGroup(
				groupLayout.createSequentialGroup()
						.addContainerGap(180, Short.MAX_VALUE)
						.addComponent(labelNiveauCourant).addGap(10)
						.addComponent(btnContinuerPartie).addGap(25)
						.addComponent(btnNouvellePartie).addGap(25)
						.addComponent(btnListeNiveaux).addGap(25)
						.addComponent(btnRetourMenu).addGap(61)));
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {
				btnNouvellePartie, btnContinuerPartie, btnListeNiveaux,
				btnRetourMenu });
		getContentPane().setLayout(groupLayout);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
