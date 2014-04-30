package sporos.reserve;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import sporos.reserve.pions.PionEnum;
import sporos.reserve.pions.PionModel;
import sporos.utils.PropertyAcces;


public class Reserve {

	private List<PionModel> pions;

	public Reserve() {
		this.pions = new ArrayList<PionModel>();
	}

	public List<PionModel> getPions() {
		return pions;
	}

	public void setPions(List<PionModel> pions) {
		this.pions = pions;
	}

	/**
	 * 
	 * @param pionName
	 * @param typePion
	 * @return position absolue de chaque pion
	 */
	public static PionModel getPosition(String pionName, PionEnum typePion) {
		String num = "";
		int numInt = 0;

		num = pionName.substring(4);
		numInt = Integer.parseInt(num);

		int x = 0, y = 10;
		x = 50 + (40 * (numInt - 1));
		PionModel pion = new PionModel(typePion, x, y, x, y);

		return pion;
	}

	/**
	 * @param graphics
	 *            on affiche la reserve
	 */
	public void paint(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		graphics.setStroke(new BasicStroke(2f));
		g.setColor(Color.black);
		graphics.drawRoundRect(40, 6, 160, 45, 40, 40);
	}

	/**
	 * 
	 * @param filename
	 *            .properties
	 * @return reserve construite a partir du fichier
	 */
	public static Reserve buildReserve(String filename) {

		ArrayList<PionModel> alPions = new ArrayList<PionModel>();
		Reserve reserve = new Reserve();
		PionModel p = new PionModel();
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
