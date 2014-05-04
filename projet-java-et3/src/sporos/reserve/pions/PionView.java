package sporos.reserve.pions;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PionView {

	private PionModel pionModel;

	public PionView(PionModel pionModel) {
		super();
		this.pionModel = pionModel;
	}

	public PionModel getPionModel() {
		return pionModel;
	}

	public void setPionModel(PionModel pionModel) {
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
			if (getPionModel().getTypePion().toString()
					.equals("TYPE".concat(String.valueOf(i)))) {
				File file = new File("resources/Pion_" + i + ".png");
				try {
					BufferedImage img = ImageIO.read(file);
					Image imgScaled;
					switch (this.pionModel.getTaille()) {
					case PETIT :
						imgScaled = img.getScaledInstance(2*this.pionModel.getRayon()-18, 2*this.pionModel.getRayon()-18,  BufferedImage.SCALE_FAST);
						graphics.drawImage(imgScaled, getPionModel().getX(),
								getPionModel().getY(), null);
						break;
					case MOYEN :
						graphics.drawImage(img, getPionModel().getX(),
								getPionModel().getY(), null);
						break;
					case GRAND :
						imgScaled = img.getScaledInstance(2*this.pionModel.getRayon()+1, 2*this.pionModel.getRayon()+1,  BufferedImage.SCALE_FAST);
						graphics.drawImage(imgScaled, getPionModel().getX(),
								getPionModel().getY(), null);
						break;
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	

}
