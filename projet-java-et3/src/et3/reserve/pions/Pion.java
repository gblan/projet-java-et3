package et3.reserve.pions;

import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pion {

	private PionEnum typePion;
	private int x;
	private int y;
	private int xInitial;
	private int yInitial;
	private final int rayon = 16;

	public Pion(PionEnum typePion, int center_x, int center_y) {
		super();
		this.typePion = typePion;
		this.x = center_x;
		this.y = center_y;
		this.xInitial = center_x;
		this.yInitial = center_y;
	}

	public Pion() {
	}

	/**
	 * 
	 * @return
	 */
	public PionEnum getTypePion() {
		return typePion;
	}
	
	

	public int getxInitial() {
		return xInitial;
	}

	public void setxInitial(int xInitial) {
		this.xInitial = xInitial;
	}

	public int getyInitial() {
		return yInitial;
	}

	public void setyInitial(int yInitial) {
		this.yInitial = yInitial;
	}

	/**
	 * 
	 * @param typePion
	 */
	public void setTypePion(PionEnum typePion) {
		this.typePion = typePion;
	}

	public int getCenter_x() {
		return x;
	}

	public void setCenter_x(int center_x) {
		this.x = center_x;
	}

	public int getCenter_y() {
		return y;
	}

	public void setCenter_y(int center_y) {
		this.y = center_y;
	}

	public int getRayon() {
		return rayon;
	}

	/**
	 * 
	 * @param point_x
	 * @param point_y
	 * @return true or false si le point selectionné contient le Pion
	 */
	public boolean contains(int point_x, int point_y) {
		Ellipse2D.Float cercle = new Ellipse2D.Float(x, y, 2 * rayon, 2 * rayon);
		return (cercle.contains(new Point2D.Float(point_x, point_y)));

	}

	/**
	 * 
	 * @param graphics qui permet l'affichage d'un pion
	 */
	public void paint(Graphics graphics) {

		// Puis le cercle interieur en fonction de l'etat de la case
		for (int i = 1; i < 8; i++) {
			if (this.typePion.toString().equals(
					"TYPE".concat(String.valueOf(i)))) {
				File file = new File("resources/Pion_" + i + ".png");
				try {
					BufferedImage img = ImageIO.read(file);
					graphics.drawImage(img, x, y, null);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	@Override
	public String toString(){
		String res = "";
		
		res+="Pion : x : "+this.x+", y : "+this.y+"\n";
		res+="\t typePion : "+this.typePion.toString()+"\n";
		return res;
	}

}
