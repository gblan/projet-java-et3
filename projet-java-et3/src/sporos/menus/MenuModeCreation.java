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
import javax.swing.JFrame;
import javax.swing.SwingConstants;

import sporos.creations.MesCreationsView;
import sporos.creations.NouvelleCreationView;

public class MenuModeCreation extends JFrame {
	private static final long serialVersionUID = 1L;

	public MenuModeCreation() {
		super("Mode Creation");
		ImageImplement background = new ImageImplement(new ImageIcon(
				"resources/Background1.png").getImage(), 0, 0);
		JButton btnMesCreations = new JButton("Mes Creations");
		BufferedImage imgbtnMesCreations = null;
		try {
			imgbtnMesCreations = ImageIO.read(new File(
					"resources/MesCreation.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnMesCreations = new JButton(new ImageIcon(imgbtnMesCreations));
		btnMesCreations.setBorder(BorderFactory.createEmptyBorder());
		btnMesCreations.setContentAreaFilled(false);
		btnMesCreations.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				MesCreationsView av = new MesCreationsView("Mes Creations");
			}
		});

		JButton btnNouvelleCreation = new JButton("Nouvelle Creation");
		BufferedImage imgbtnNouvelleCreation = null;
		try {
			imgbtnNouvelleCreation = ImageIO.read(new File(
					"resources/NouvelleCreation.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnNouvelleCreation = new JButton(new ImageIcon(imgbtnNouvelleCreation));
		btnNouvelleCreation.setBorder(BorderFactory.createEmptyBorder());
		btnNouvelleCreation.setContentAreaFilled(false);
		btnNouvelleCreation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				NouvelleCreationView test = new NouvelleCreationView();

			}
		});

		JButton btnRetourMenu = new JButton("Menu Principal");
		BufferedImage imgbtnRetourMenu = null;
		try {
			imgbtnRetourMenu = ImageIO.read(new File(
					"resources/MenuPrincipal.png"));
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
										.addComponent(btnMesCreations)
										.addComponent(btnNouvelleCreation)
										.addComponent(btnRetourMenu,
												GroupLayout.PREFERRED_SIZE, 69,
												GroupLayout.PREFERRED_SIZE))
						.addContainerGap(115, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.TRAILING).addGroup(
				groupLayout.createSequentialGroup()
						.addContainerGap(180, Short.MAX_VALUE)
						.addComponent(btnMesCreations).addGap(25)
						.addComponent(btnNouvelleCreation).addGap(25)
						.addComponent(btnRetourMenu).addGap(61)));
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {
				btnMesCreations, btnNouvelleCreation, btnRetourMenu });
		getContentPane().add(background);
		getContentPane().setLayout(groupLayout);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

}
