package sporos.animation;

import sporos.jeu.JeuView;

public class Animation {
	
	public static void Deplacement(JeuView jeu,int pointX, int pointY, int arriveeX, int arriveeY, float temps){
		int departX =pointX;
		int departY =pointY;
		double distanceX = Math.abs(arriveeX-pointX);
		double distanceY = Math.abs(arriveeY-pointY);
		double distanceInitial =  Math.sqrt(Math.pow(distanceX, 2)+Math.pow(distanceY, 2));
		double distanceCourante;
		long start = System.currentTimeMillis();
		double coefAcceleration = 0.0005;
		long deltaTemps = (long) 1;
		double vitesseInitialX = (2*distanceX)/temps;
		double vitesseInitialY = (2*distanceY)/temps;
		double vitesseActuelX = vitesseInitialX;
		double vitesseActuelY = vitesseInitialY;
		double tempsActuel = (System.currentTimeMillis() - start);
		
		while (tempsActuel < temps){
			distanceCourante =  Math.sqrt(Math.pow(Math.abs(arriveeX-pointX), 2)+Math.pow(Math.abs(arriveeY-pointY), 2));
			
			if (distanceCourante>=distanceInitial/2){
				// Acceleration
				// Deplacement en X
				
				vitesseActuelX = vitesseActuelX + coefAcceleration * tempsActuel;
				int deplacementX = (int) (vitesseActuelX*deltaTemps);
				if (arriveeX > departX) {
					pointX = pointX + deplacementX;
				}
				else if (arriveeX < departX) {
					pointX = pointX - deplacementX;
				}
				
				// Deplacement en Y
				
				vitesseActuelY = vitesseActuelY + coefAcceleration * tempsActuel;
				int deplacementY = (int) (vitesseActuelY*deltaTemps);
				if (arriveeY > departY) {
					pointY = pointY + deplacementY;
				}
				else if (arriveeX < departY) {
					pointY = pointY - deplacementY;
				}
				
			}
			else {
				// Decceleration
				// Deplacement en X
				
				vitesseActuelX = vitesseActuelX - coefAcceleration * (tempsActuel - temps/2);
				int deplacementX = (int) (vitesseActuelX*deltaTemps);
				if (arriveeX > pointX) {
					pointX = pointX + deplacementX;
				}
				else if (arriveeX < departX) {
					pointX = pointX - deplacementX;
				}
				// Deplacement en Y
			
				vitesseActuelY = vitesseActuelY - coefAcceleration * (tempsActuel - temps/2);
				int deplacementY = (int) (vitesseActuelY*deltaTemps);
				if (arriveeY > pointY) {
					pointY = pointY + deplacementY;
				}
				else if (arriveeX < departY) {
					pointY = pointY - deplacementY;
				}
			}
			tempsActuel = (System.currentTimeMillis() - start);
			jeu.repaint();
			try {  
		         Thread.sleep(deltaTemps*1000);  
		     }  
		      catch (InterruptedException e) {  
		     }  
		}
		
	}
}
