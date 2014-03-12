package et3_project;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Principale extends JFrame {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		//listeCase.add(c2);
		// FIN TEST
		Jeu j = new Jeu(grille);
		j.setBackground(Color.WHITE) ;
		j.setPreferredSize(new Dimension(width,height));
		pane.add(j);
		pack();
		setVisible(true);

	}

	public static void main(String[] arg) {
		Principale p1 = new Principale("Sporos",300,500);
		p1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		/*Sauvegarde s1 = new Sauvegarde();
		s1.saveProperties("cheval", "blanc");*/
	}

}