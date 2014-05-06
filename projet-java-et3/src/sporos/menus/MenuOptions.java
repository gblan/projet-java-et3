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

import sporos.creations.NouvelleCreationView;

public class MenuOptions extends JFrame{

	private boolean sonActif;

	public boolean isSonActif() {
		return sonActif;
	}

	public void setSonActif(boolean sonActif) {
		this.sonActif = sonActif;
	}
	
	public MenuOptions() {
		super("Mode Option");
		ImageImplement background = new ImageImplement(new ImageIcon(
				"resources/Background1.png").getImage(), 0, 0);

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
										.addComponent(btnNouvelleCreation)
										.addComponent(btnRetourMenu,
												GroupLayout.PREFERRED_SIZE, 69,
												GroupLayout.PREFERRED_SIZE))
						.addContainerGap(115, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.TRAILING).addGroup(
				groupLayout.createSequentialGroup()
						.addContainerGap(180, Short.MAX_VALUE)
						.addComponent(btnNouvelleCreation).addGap(25)
						.addComponent(btnRetourMenu).addGap(61)));
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {
				btnNouvelleCreation, btnRetourMenu });
		getContentPane().add(background);
		getContentPane().setLayout(groupLayout);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
