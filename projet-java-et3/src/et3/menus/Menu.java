package et3.menus;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;

public class Menu extends JFrame {

	public Menu() {
		super();
		setBounds(300, 100, 0, 0);
		setSize(500, 500);
		Container pane = getContentPane();
		pane.setLayout(new FlowLayout());
		// pack();
		setVisible(true);
	}

}
