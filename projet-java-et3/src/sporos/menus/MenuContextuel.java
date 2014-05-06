package sporos.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import sporos.jeu.JeuView;

public class MenuContextuel {

	public static JButton createButtonContextuel() {
		JButton btnMenuContextuel = new JButton();

		BufferedImage imgMenuContextuel = null;
		try {
			imgMenuContextuel = ImageIO.read(new File(
					"resources/icone_menu.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		btnMenuContextuel = new JButton(new ImageIcon(imgMenuContextuel));

		btnMenuContextuel.setBounds(300, 0, 50, 50);
		btnMenuContextuel.setBorder(BorderFactory.createEmptyBorder());
		btnMenuContextuel.setContentAreaFilled(false);
//		btnMenuContextuel.addMouseListener( new MouseAdapter() {
//			public void mousePressed(MouseEvent evt) {
//				System.out.println("lol");
////				jeuView.buildMenuContextuel();
//			}
//		});
//		
		return btnMenuContextuel;
	}

}
