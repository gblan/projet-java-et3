package et3.jeu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import et3.grille.Grille;
import et3.grille.cases.Case;
import et3.grille.cases.CaseEnum;
import et3.reserve.Reserve;
import et3.reserve.pions.Pion;
import et3.reserve.pions.PionManager;

public class JeuListener extends Jeu{

	private Jeu jeu;
	private int clique_x;
	private int clique_y;

	public void setJeu(Jeu jeu){
		this.jeu = jeu;
	}
	
	public Jeu getJeu(){
		return this.jeu;
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
	
	public JeuListener(Grille grille, Reserve reserve) {
		super(grille, reserve);
		// TODO Auto-generated constructor stub
	}

	/* Selection du pion a la souris */
	public MouseAdapter selectionnerPions = new MouseAdapter() {
		public void mousePressed(MouseEvent evt) {
			setClique_x(evt.getX());
			setClique_y(evt.getY());
			for (Pion pion : jeu.getReserve().getPions()) {
				if (pion.contains(clique_x, clique_y)) {
					for (ArrayList<Case> alCase : jeu.getGrille()
							.getListCases()) {
						for (Case caseJeu : alCase) {
							if (caseJeu.contains(clique_x, clique_y)) {
								caseJeu.setEtatActuel(CaseEnum.DISPONIBLE);
							}
						}
					}
					jeu.setPionSelectionne(pion);
					jeu.repaint();
				}
			}
		}

		public void mouseReleased(MouseEvent evt) {

			if (jeu.getPionSelectionne() != null) {
				// Si il n'y a pas de case selectionne le pion retourne dans la
				// reserve

				// if Magic. Do not touch.
				if (jeu.getGrille().getListCases().get(jeu.getIndiceCaseH())
						.get(jeu.getIndiceCaseV()) == null
						|| !(jeu.getGrille().getListCases()
								.get(jeu.getIndiceCaseH())
								.get(jeu.getIndiceCaseV()).intersect(jeu
								.getPionSelectionne()))
						|| jeu.getGrille().getListCases()
								.get(jeu.getIndiceCaseH())
								.get(jeu.getIndiceCaseV()).getEtatActuel()
								.toString().equals(CaseEnum.OCCUPEE.toString())) {
					/* A FAIRE deplacement vers point initial */
					jeu.getPionSelectionne().setCenter_x(
							jeu.getPionSelectionne().getxInitial());
					jeu.getPionSelectionne().setCenter_y(
							jeu.getPionSelectionne().getyInitial());
					jeu.setPionSelectionne(null);

				}

				else {
					// sinon on le positionne sur la case
					jeu.getPionSelectionne().setCenter_x(
							jeu.getGrille().getListCases()
									.get(jeu.getIndiceCaseH())
									.get(jeu.getIndiceCaseV()).getX() + 3);
					jeu.getPionSelectionne().setCenter_y(
							jeu.getGrille().getListCases()
									.get(jeu.getIndiceCaseH())
									.get(jeu.getIndiceCaseV()).getY() + 4);
					jeu.getGrille().getListCases().get(jeu.getIndiceCaseH())
							.get(jeu.getIndiceCaseV())
							.setEtatActuel(CaseEnum.OCCUPEE);

					jeu.getPionsEnJeu().add(jeu.getPionSelectionne());

					PionManager pm = new PionManager(jeu.getGrille(),
							jeu.getPionsEnJeu(), jeu.getIndiceCaseH(),
							jeu.getIndiceCaseV(), false);
					jeu.setPionSelectionne(null);

				}
			}
			jeu.repaint();

		}
	};

	public MouseMotionAdapter selectionnerPionsMotion = new MouseMotionAdapter() {
		/* Déplacement a la souris */
		public void mouseDragged(MouseEvent evt) {
			if (jeu.getPionSelectionne() != null) {
				int translate_x = evt.getX() - getClique_x();
				int translate_y = evt.getY() - getClique_y();
				jeu.getPionSelectionne().setCenter_x(
						jeu.getPionSelectionne().getCenter_x() + translate_x);
				jeu.getPionSelectionne().setCenter_y(
						jeu.getPionSelectionne().getCenter_y() + translate_y);
				setClique_x(evt.getX());
				setClique_y(evt.getY());

				boolean potentielle = false;
				int j = 0, k;
				for (ArrayList<Case> alCase : jeu.getGrille().getListCases()) {
					k = 0;
					for (Case caseJeu : alCase) {

						if (caseJeu.intersect(jeu.getPionSelectionne())
								&& (!caseJeu.getEtatActuel().toString()
										.equals(CaseEnum.DESACTIVEE.toString()))) {

							if (!(caseJeu.getEtatActuel().toString()
									.equals(CaseEnum.OCCUPEE.toString()))
									&& !(caseJeu.getEtatActuel().toString()
											.equals(CaseEnum.CONTAMINEE
													.toString()))) {

								caseJeu.setEtatActuel(CaseEnum.POTENTIELLESURVOLEE);
								jeu.setIndiceCaseH(j);
								jeu.setIndiceCaseV(k);
								potentielle = true;

							}
						} else if (!(caseJeu.getEtatActuel().toString()
								.equals(CaseEnum.OCCUPEE.toString()))
								&& !(caseJeu.getEtatActuel().toString()
										.equals(CaseEnum.CONTAMINEE.toString()))) {

							caseJeu.setEtatActuel(caseJeu.getEtatInitial());

						}
						k++;
					}
					j++;
				}

				if (potentielle == true) {
					PionManager pm = new PionManager(jeu.getGrille(),
							jeu.getPionsEnJeu(), jeu.getIndiceCaseH(),
							jeu.getIndiceCaseV(), true);
				}
				jeu.repaint();

			}
		}
	};
	
}
