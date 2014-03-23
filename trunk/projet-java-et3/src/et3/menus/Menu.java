package et3.menus;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import main.Principale;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;

	
	public Menu(String windowName) {
		super(windowName);
		setBounds(100, 100, 315, 454);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btnNewButton = new JButton("Play");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				
				/* TODO Aller a liste niveau par la suite*/
				Principale p1 = new Principale("Sporos", 300, 500);
				
			}
		});

		JButton btnOptions = new JButton("Options");
		btnOptions.addActionListener(new ActionListener() {
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
										.addComponent(btnOptions)
										.addComponent(btnQuit,
												GroupLayout.PREFERRED_SIZE, 69,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnNewButton))
						.addContainerGap(115, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.TRAILING).addGroup(
				groupLayout.createSequentialGroup()
						.addContainerGap(201, Short.MAX_VALUE)
						.addComponent(btnNewButton).addGap(42)
						.addComponent(btnOptions).addGap(42)
						.addComponent(btnQuit).addGap(61)));
		groupLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {
				btnNewButton, btnOptions, btnQuit });
		getContentPane().setLayout(groupLayout);
		setVisible(true);

	}

}
