package sporos.creations;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import sporos.grille.cases.CaseEnum;
import sporos.grille.cases.CaseModel;
import sporos.jeu.JeuView;
import sporos.main.Principale;
import sporos.reserve.pions.PionEnum;
import sporos.reserve.pions.PionModel;

public class CreationListener {

	private JeuView jeuView;
	private int cliqueX;
	private int cliqueY;

	public CreationListener(JeuView jeuView) {

		this.setJeuView(jeuView);
	}

	public JeuView getJeuView() {
		return jeuView;
	}

	public void setJeuView(JeuView jeuView) {
		this.jeuView = jeuView;
	}

	public MouseListener getGridCreationListener() {
		return gridCreationListener;
	}

	public int getCliqueX() {
		return cliqueX;
	}

	public void setCliqueX(int cliqueX) {
		this.cliqueX = cliqueX;
	}

	public int getCliqueY() {
		return cliqueY;
	}

	public void setCliqueY(int cliqueY) {
		this.cliqueY = cliqueY;
	}

	public KeyListener getKey() {
		return key;
	}


	private MouseAdapter gridCreationListener = new MouseAdapter() {
		public void mousePressed(MouseEvent evt) {
			setCliqueX(evt.getX());
			setCliqueY(evt.getY());
			PionModel pion = new PionModel(PionEnum.TYPE1, getCliqueX()-20, getCliqueY()-20, 0, 0);
			for (ArrayList<CaseModel> alCase : jeuView.getJeu().getGrille()
					.getListCases()) {

				for (CaseModel caseJeu : alCase) {
					if(caseJeu.intersect(pion)){
						System.out.println(caseJeu);
						if(caseJeu.getEtatActuel().equals(CaseEnum.DISPONIBLE)){
							caseJeu.setEtatActuel(CaseEnum.DESACTIVEE);
						}else{
							caseJeu.setEtatActuel(CaseEnum.DISPONIBLE);
						}
					}
				}
			}
			jeuView.repaint();
		}

	};
	
	private KeyListener key = new KeyListener(){

		@Override
		public void keyPressed(KeyEvent arg0) {

			if((arg0.getKeyCode()==KeyEvent.VK_ENTER)){
				System.out.println("Teub");
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	};

}
