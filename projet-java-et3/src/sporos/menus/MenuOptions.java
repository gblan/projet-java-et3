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
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import sporos.creations.NouvelleCreationView;
import sporos.utils.Bruitages;
import sporos.utils.PropertyAcces;

public class MenuOptions extends JFrame {

	public MenuOptions() {
		super("Mode Option");
		ImageImplement background = new ImageImplement(new ImageIcon(
				"resources/Background1.png").getImage(), 0, 0);

		final JLabel labelNiveauCourant = new JLabel();
		String tmp = "";
		if (Bruitages.getInstance().isActive()) {
			tmp = "Activé";
		} else {
			tmp = "Desactivé";
		}
		
		labelNiveauCourant.setText("Le son est actuellement : "+tmp);

		JButton btnSon = new JButton("Son");
		BufferedImage imgBtnSon = null;
		try {
			imgBtnSon = ImageIO.read(new File(
					"resources/Son.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		btnSon = new JButton(new ImageIcon(imgBtnSon));
		btnSon.setBorder(BorderFactory.createEmptyBorder());
		btnSon.setContentAreaFilled(false);
		btnSon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				setVisible(false);
				if (Bruitages.getInstance().isActive()) {
					Bruitages.getInstance().setActive(false);
				}else{
					Bruitages.getInstance().setActive(true);
				}
				String tmp = "";
				if (Bruitages.getInstance().isActive()) {
					tmp = "Activé";
				} else {
					tmp = "Desactivé";
				}
				labelNiveauCourant.setText("Le son est actuellement : "+tmp);

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
										.addComponent(labelNiveauCourant)
										.addComponent(btnSon)
										.addComponent(btnRetourMenu,
												GroupLayout.PREFERRED_SIZE, 69,
												GroupLayout.PREFERRED_SIZE))
						.addContainerGap(115, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.TRAILING).addGroup(
				groupLayout.createSequentialGroup()
						.addContainerGap(180, Short.MAX_VALUE)
						.addComponent(labelNiveauCourant).addGap(25)
						.addComponent(btnSon).addGap(25)
						.addComponent(btnRetourMenu).addGap(61)));
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {
				btnSon, btnRetourMenu });
		getContentPane().add(background);
		getContentPane().setLayout(groupLayout);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
