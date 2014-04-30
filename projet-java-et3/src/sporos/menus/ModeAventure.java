package sporos.menus;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

import sporos.main.Principale;
import sporos.utils.PropertyAcces;

public class ModeAventure extends JFrame {
	private static final long serialVersionUID = 1L;

	public ModeAventure() {
		super("Mode Aventure");

		JButton btnNouvellePartie = new JButton("Nouvelle Partie");
		btnNouvellePartie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PropertyAcces.saveProperties(1);
				setVisible(false);
				Principale av = new Principale("sporos", PropertyAcces
						.getCurrentLevel(), 300, 500);
			}
		});

		JButton btnContinuerPartie = new JButton("Continuer Partie");
		btnContinuerPartie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Principale av = new Principale("sporos, niveau : "+PropertyAcces
						.getCurrentLevel(), PropertyAcces
						.getCurrentLevel(), 300, 500);
			}
		});

		JButton btnListeNiveaux = new JButton("Liste des Niveaux");
		btnListeNiveaux.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Niveaux av = new Niveaux("Liste des niveaux");
			}
		});

		JButton btnRetourMenu = new JButton("Menu Principal");
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
