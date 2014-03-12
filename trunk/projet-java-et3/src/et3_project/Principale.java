package et3_project;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JFrame;



public class Principale extends JFrame {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Jeu jeu;
	private int clique_x;
	private int clique_y;
	
	
	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}

	private MouseAdapter selectionnerPions = new MouseAdapter() {
		public void mousePressed(MouseEvent evt) {
			setClique_x(evt.getX());
			setClique_y(evt.getY());
			for (int i=0; i<jeu.getReserve().size();i++){
				if (jeu.getReserve().get(i).contains(clique_x,clique_y)){
					jeu.setPionSelectionne(jeu.getReserve().get(i));
					jeu.repaint() ;
				}
			}
		}
		public void mouseReleased(MouseEvent evt) {
			jeu.setPionSelectionne(null); 
			jeu.repaint() ;
		}
	};
	
	private MouseMotionAdapter selectionnerPionsMotion = new MouseMotionAdapter(){	
	    public void mouseDragged(MouseEvent evt) {
	    	if (jeu.getPionSelectionne() !=null){
	    	  int translate_x=evt.getX()- getClique_x() ;
			  int translate_y=evt.getY()- getClique_y() ;
			  jeu.getPionSelectionne().setCenter_x( jeu.getPionSelectionne().getCenter_x()+translate_x);
			  jeu.getPionSelectionne().setCenter_y( jeu.getPionSelectionne().getCenter_y()+translate_y);
			  setClique_x(evt.getX());
			  setClique_y(evt.getY());
			  jeu.repaint() ;
	    	}
	    }
	};
	
	public Principale(String title, int width, int height) {
		super(title) ;
		setBounds(width, height, 0, 0);
		setSize(width-100, height-100);
		Container pane = getContentPane() ;
		pane.setLayout(new FlowLayout());
		// DEBUT TEST
		ArrayList<ArrayList<Case>> grille = new ArrayList<ArrayList<Case>>();
		ArrayList<Case> ligne1 = new ArrayList<Case>();
		ArrayList<Case> ligne2 = new ArrayList<Case>();
		ArrayList<Case> ligne3 = new ArrayList<Case>();
		Case c1 = new Case(CaseEnum.DISPONIBLE,80,150);
		Case c2 = new Case(CaseEnum.DISPONIBLE,120,150);
		ligne1.add(c1);
		ligne1.add(c2);
		grille.add(ligne1);
		Case c3 = new Case(CaseEnum.DISPONIBLE,59,185);
		Case c4 = new Case(CaseEnum.DISPONIBLE,120,150);
		Case c5 = new Case(CaseEnum.DISPONIBLE,80,150);
		Case c6 = new Case(CaseEnum.DISPONIBLE,120,150);
		ligne2.add(c3);
		ligne2.add(c4);
		ligne2.add(c5);
		ligne2.add(c6);
		grille.add(ligne2);
		Case c7 = new Case(CaseEnum.DISPONIBLE,80,150);
		Case c8 = new Case(CaseEnum.DISPONIBLE,120,150);
		ligne3.add(c7);
		ligne3.add(c8);
		grille.add(ligne3);
		
		ArrayList<Pions> reserve = new ArrayList<Pions>();
		Pions p1 = new Pions(PionsEnum.TYPE6, 50, 10);
		reserve.add(p1);
		
		//listeCase.add(c2);
		// FIN TEST
		
		setJeu(new Jeu(grille,reserve));
		jeu.setBackground(Color.WHITE) ;
		jeu.setPreferredSize(new Dimension(width,height));
		jeu.addMouseListener(selectionnerPions);
		jeu.addMouseMotionListener(selectionnerPionsMotion);
		pane.add(jeu);
		pack();
		setVisible(true);

	}

	public int getClique_x() {
		return clique_x;
	}

	public void setClique_x(int clique_x) {
		this.clique_x = clique_x;
	}

	public int getClique_y() {
		return clique_y;
	}

	public void setClique_y(int clique_y) {
		this.clique_y = clique_y;
	}

	public static void main(String[] arg) {
		Principale p1 = new Principale("Sporos",300,500);
		p1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		/*Sauvegarde s1 = new Sauvegarde();
		s1.saveProperties("cheval", "blanc");*/
	}

}