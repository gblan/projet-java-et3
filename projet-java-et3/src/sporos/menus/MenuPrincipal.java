package sporos.menus;

import java.awt.Component;
import java.awt.Image;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import sporos.grille.GrilleEnum;
import sporos.main.Principale;

public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] arg) {
		MenuPrincipal m = new MenuPrincipal();
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public MenuPrincipal() {
		super("Menu Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageImplement img = new ImageImplement(new ImageIcon("resources/Background1.png").getImage());
		
		JButton btnPartieRapide = new JButton("Partie Rapide");
		BufferedImage imgbtnPartieRapide = null;
		try {
			imgbtnPartieRapide = ImageIO.read(new File(
					"resources/PartieRapide.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnPartieRapide = new JButton(new ImageIcon(imgbtnPartieRapide));
		btnPartieRapide.setBorder(BorderFactory.createEmptyBorder());
		btnPartieRapide.setContentAreaFilled(false);
		btnPartieRapide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Principale p1 = new Principale(1, 300, 500,GrilleEnum.MOYEN);

			}
		});

		JButton btnModeAventure = new JButton("Mode Aventure");
		BufferedImage imgbtnModeAventure = null;
		try {
			imgbtnModeAventure = ImageIO.read(new File(
					"resources/ModeAventure.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnModeAventure = new JButton(new ImageIcon(imgbtnModeAventure));
		btnModeAventure.setBorder(BorderFactory.createEmptyBorder());
		btnModeAventure.setContentAreaFilled(false);
		btnModeAventure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				MenuModeAventure av = new MenuModeAventure();
			}
		});

		JButton btnModeCreateur = new JButton("Mode Createur");
		BufferedImage imgbtnModeCreateur = null;
		try {
			imgbtnModeCreateur = ImageIO.read(new File(
					"resources/ModeCreation.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnModeCreateur = new JButton(new ImageIcon(imgbtnModeCreateur));
		btnModeCreateur.setBorder(BorderFactory.createEmptyBorder());
		btnModeCreateur.setContentAreaFilled(false);
		btnModeCreateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				MenuModeCreation av = new MenuModeCreation();
			}
		});

		JButton btnOption = new JButton("Options");
		BufferedImage imgbtnOption = null;
		try {
			imgbtnOption = ImageIO.read(new File("resources/boutonOption.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnOption = new JButton(new ImageIcon(imgbtnOption));
		btnOption.setBorder(BorderFactory.createEmptyBorder());
		btnOption.setContentAreaFilled(false);
		btnOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JButton btnQuit = new JButton("Quit");
		BufferedImage imgbtnQuit = null;
		try {
			imgbtnQuit = ImageIO.read(new File("resources/Quitter.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnQuit = new JButton(new ImageIcon(imgbtnQuit));
		btnQuit.setBorder(BorderFactory.createEmptyBorder());
		btnQuit.setContentAreaFilled(false);
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	
			
		getContentPane().add(img);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addGap(115)
						.addGroup(
								groupLayout
										.createParallelGroup(Alignment.LEADING)
										.addComponent(btnModeCreateur)
										.addComponent(btnModeAventure)
										.addComponent(btnOption)
										.addComponent(btnQuit,
												GroupLayout.PREFERRED_SIZE, 69,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnPartieRapide))
						.addContainerGap(115, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.TRAILING).addGroup(
				groupLayout.createSequentialGroup()
						.addContainerGap(180, Short.MAX_VALUE)
						.addComponent(btnPartieRapide).addGap(25)
						.addComponent(btnModeAventure).addGap(25)
						.addComponent(btnModeCreateur).addGap(25)
						
						.addComponent(btnOption).addGap(25)
						.addComponent(btnQuit).addGap(61)));
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {
				btnPartieRapide, btnModeAventure, btnModeCreateur, btnOption,
				btnQuit });
		getContentPane().add(img);
		getContentPane().setLayout(groupLayout);
       
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}

}
