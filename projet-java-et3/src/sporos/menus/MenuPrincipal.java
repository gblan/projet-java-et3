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

public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	public MenuPrincipal() {
		super("Menu Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btnPartieRapide = new JButton("Partie Rapide");
		btnPartieRapide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Principale p1 = new Principale("sporos", 1, 300, 500);

			}
		});

		JButton btnModeAventure = new JButton("Mode Aventure");
		btnModeAventure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ModeAventure av = new ModeAventure();
			}
		});

		JButton btnModeCreateur = new JButton("Mode Createur");
		btnModeCreateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ModeCreation av = new ModeCreation();
			}
		});

		JButton btnOption = new JButton("Options");
		btnOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JButton btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
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
						.addComponent(btnModeCreateur).addGap(25)
						.addComponent(btnModeAventure).addGap(25)
						.addComponent(btnOption).addGap(25)
						.addComponent(btnQuit).addGap(61)));
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {
				btnPartieRapide, btnModeAventure, btnModeCreateur, btnOption,
				btnQuit });
		getContentPane().setLayout(groupLayout);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}

}
