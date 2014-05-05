package sporos.menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
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
		btnMenuContextuel.setBorder(BorderFactory.createEmptyBorder());
		btnMenuContextuel.setContentAreaFilled(false);
		btnMenuContextuel.setBounds(20, 0, 520, 50);
		
		btnMenuContextuel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JeuView.buildMenuContextuel() ;
				System.out.println("lol");
			}
		});
		
		return btnMenuContextuel;
	}

}
