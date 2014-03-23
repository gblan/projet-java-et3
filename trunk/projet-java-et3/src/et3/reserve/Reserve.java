package et3.reserve;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import et3.grille.Grille;
import et3.reserve.pions.Pion;
import et3.reserve.pions.PionEnum;
import et3.sauvegarde.PropertyAcces;

public class Reserve {

	private ArrayList<Pion> pions;

	public Reserve() {
		this.pions = new ArrayList<Pion>();
	}

	public ArrayList<Pion> getPions() {
		return pions;
	}

	public void setPions(ArrayList<Pion> pions) {
		this.pions = pions;
	}

	/**
	 * 
	 * @param pionName
	 * @param typePion
	 * @return position absolue de chaque pion
	 */
	public static Pion getPosition(String pionName, PionEnum typePion) {
		String num = "";
		int numInt = 0;

		num = pionName.substring(4);
		numInt = Integer.parseInt(num);

		int x = 0, y = 10;
		x = 50 + (40 * (numInt - 1));
		Pion pion = new Pion(typePion, x, y, x, y);

		return pion;
	}
	
	/**
	 *@param graphics  on affiche la reserve
	 */
	public void paint(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		graphics.setStroke(new BasicStroke(2f));
		g.setColor(Color.black);
		graphics.drawRoundRect(40, 6, 
			     160, 45, 40, 40);
	}

	/**
	 * 
	 * @param filename.properties
	 * @return reserve construite a partir du fichier
	 */
	public static Reserve buildReserve(String filename) {

		ArrayList<Pion> alPions = new ArrayList<Pion>();
		Reserve reserve = new Reserve();
		Pion p = new Pion();
		String typePion, pionName;
		PionEnum pionEnum = PionEnum.TYPE1;
		

		for (int i = 1; i < 8; i++) {
			pionName = "PION".concat(String.valueOf(i));
			typePion = PropertyAcces.retrieveProperties(filename, pionName);

			for (PionEnum b : PionEnum.values()) {
				if (typePion.equalsIgnoreCase(b.name())) {
					pionEnum = PionEnum.valueOf(b.name());
				}
			}

			if (!typePion.equals("vide")) {
				p = getPosition(pionName, pionEnum);
				alPions.add(p);
				reserve.setPions(alPions);
			}
		}

		return reserve;
	}
}
