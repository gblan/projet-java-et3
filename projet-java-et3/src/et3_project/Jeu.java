package et3_project;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;



public class Jeu extends Component{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/* En attendant les .properties pour Tester l'affichage
	* A MODIFIER
	*/
	private ArrayList<ArrayList<Case>> grille;
	/*
	 * FIN A MODIFIER
	 */
	
	public Jeu(ArrayList<ArrayList<Case>> grille) {
		super();
		this.grille = grille;
	}
	
	public void paint(Graphics graphics) {
    	
		   Graphics2D g = (Graphics2D)graphics ;
		   g.setColor(getBackground()) ;
		   g.fillRect(0, 0, getWidth(), getHeight()) ;
		   for (int i = 0; i<this.grille.size();i++ ){
			   for(int j=0; j< grille.get(i).size();j++){
				   grille.get(i).get(j).paint(graphics);
			   }
		   }
	}

}
