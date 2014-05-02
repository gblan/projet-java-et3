package sporos.animation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.Timer;

import sporos.jeu.JeuModel;
import sporos.jeu.JeuView;
import sporos.reserve.pions.PionModel;

public class Animation {

	public static void DeplacementPion(final JeuModel jeuModel,
			final JeuView jeuView, final Hashtable<PionModel, Timer> timers,
			final int surCase) {
		// Animation deplacement pion
		final PionModel pionRelache = jeuModel.getPionSelectionne();
		jeuModel.getPionRelache().add(pionRelache);

		final int arriveeX;
		final int arriveeY;
		switch (surCase) {
		case 0:
			arriveeX = jeuModel.getGrille().getListCases()
					.get(jeuModel.getIndiceCaseH())
					.get(jeuModel.getIndiceCaseV()).getX() + 3;
			arriveeY = jeuModel.getGrille().getListCases()
					.get(jeuModel.getIndiceCaseH())
					.get(jeuModel.getIndiceCaseV()).getY() + 4;
			System.out.println(arriveeX);
			break;
		case 1:
			arriveeX = pionRelache.getxInitial();
			arriveeY = pionRelache.getyInitial();
			break;
		default:
			arriveeX = 0;
			arriveeY = 0;
		}

		double distanceX = Math.abs(arriveeX - pionRelache.getX());
		double distanceY = Math.abs(arriveeY - pionRelache.getY());
		final int distance = (int) Math.sqrt(Math.pow(distanceX, 2)
				+ Math.pow(distanceY, 2));

		Timer timer = new Timer(1000 / distance, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int step;
				if (distance > 300) {
					step = 6;
				} else if (distance > 100) {
					step = 4;
				} else if (distance > 10) {
					step = 2;
				} else {
					step = 1;
				}

				if (pionRelache.getX() < arriveeX) {

					pionRelache.setX(pionRelache.getX() + step);
				} else if (pionRelache.getX() > arriveeX) {
					pionRelache.setX(pionRelache.getX() - step);
				}

				if (pionRelache.getY() < arriveeY) {
					pionRelache.setY(pionRelache.getY() + step);
				} else if (pionRelache.getY() > arriveeY) {
					pionRelache.setY(pionRelache.getY() - step);
				}
				jeuView.repaint();

				if ((pionRelache.getY() > arriveeY - step && pionRelache.getY() < arriveeY
						+ step)
						&& (pionRelache.getX() > arriveeX - step && pionRelache
								.getX() < arriveeX + step)) {
					pionRelache.setX(arriveeX);
					pionRelache.setY(arriveeY);
					jeuModel.getPionRelache().remove(pionRelache);
					timers.get(pionRelache).stop();
					timers.remove(pionRelache);

				}

			}
		});
		timers.put(pionRelache, timer);
		timer.start();

	}
	// int departX = pion.getX();
	// int departY = pion.getY();
	// double distanceX = Math.abs(arriveeX-pion.getX());
	// double distanceY = Math.abs(arriveeY-pion.getY());
	// double distanceInitial = Math.sqrt(Math.pow(distanceX,
	// 2)+Math.pow(distanceY, 2));
	// double distanceCourante;
	// double coefAcceleration = 0.0005;
	// long deltaTemps = (long) 1;
	// double vitesseInitialX = (2*distanceX)/temps;
	// double vitesseInitialY = (2*distanceY)/temps;
	// double vitesseActuelX = vitesseInitialX;
	// double vitesseActuelY = vitesseInitialY;
	// double tempsActuel = 0;

	// while (tempsActuel < temps){
	// if (distanceCourante>=distanceInitial/2){
	// // Acceleration
	// // Deplacement en X
	//
	// vitesseActuelX = vitesseActuelX + coefAcceleration * tempsActuel;
	// int deplacementX = (int) (vitesseActuelX*deltaTemps);
	// if (arriveeX > departX) {
	// pion.setX(pion.getX() + deplacementX);
	// }
	// else if (arriveeX < departX) {
	// pion.setX(pion.getX() - deplacementX);
	// }
	//
	// // Deplacement en Y
	//
	// vitesseActuelY = vitesseActuelY + coefAcceleration * tempsActuel;
	// int deplacementY = (int) (vitesseActuelY*deltaTemps);
	// if (arriveeY > departY) {
	// pion.setY(pion.getY() + deplacementY);
	// }
	// else if (arriveeX < departY) {
	// pion.setY(pion.getY() - deplacementY);
	// }
	//
	// }
	// else {
	// // Decceleration
	// // Deplacement en X
	//
	// vitesseActuelX = vitesseActuelX - coefAcceleration * (tempsActuel -
	// temps/2);
	// int deplacementX = (int) (vitesseActuelX*deltaTemps);
	// if (arriveeX > departX) {
	// pion.setX(pion.getX() + deplacementX);
	// }
	// else if (arriveeX < departX) {
	// pion.setX(pion.getX() - deplacementX);
	// }
	// // Deplacement en Y
	//
	// vitesseActuelY = vitesseActuelY - coefAcceleration * (tempsActuel -
	// temps/2);
	// int deplacementY = (int) (vitesseActuelY*deltaTemps);
	// if (arriveeY > departY) {
	// pion.setY(pion.getY() + deplacementY);
	// }
	// else if (arriveeX < departY) {
	// pion.setY(pion.getY() - deplacementY);
	// }
	// }

}
