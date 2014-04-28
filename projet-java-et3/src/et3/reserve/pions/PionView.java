package et3.reserve.pions;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PionView {
	
	private Pion pionModel;

	public PionView(Pion pionModel) {
		super();
		this.pionModel = pionModel;
	}

	public Pion getPionModel() {
		return pionModel;
	}

	public void setPionModel(Pion pionModel) {
		this.pionModel = pionModel;
	}

	/**
	 * 
	 * @param graphics
	 *            qui permet l'affichage d'un pion
	 */
	public void paint(Graphics graphics) {
		// Puis le cercle interieur en fonction de l'etat de la case
		for (int i = 1; i < 8; i++) {
			if (getPionModel().getTypePion().toString().equals(
					"TYPE".concat(String.valueOf(i)))) {
				File file = new File("resources/Pion_" + i + ".png");
				try {
					BufferedImage img = ImageIO.read(file);
					graphics.drawImage(img, getPionModel().getX(), getPionModel().getY(), null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
}
