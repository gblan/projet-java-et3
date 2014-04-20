package et3.jeu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import et3.grille.Grille;
import et3.grille.cases.Case;
import et3.grille.cases.CaseEnum;
import et3.reserve.Reserve;
import et3.reserve.pions.Pion;
import et3.reserve.pions.PionManager;

public class JeuListener extends Jeu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Jeu jeu;
	private int cliqueX;
	private int cliqueY;

	public void setJeu(Jeu jeu) {
		this.jeu = jeu;
	}

	public Jeu getJeu() {
		return this.jeu;
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

	public JeuListener(Grille grille, Reserve reserve) {
		super(grille, reserve);
	}

	public MouseMotionAdapter getSelectionnerPionsMotion() {
		return selectionnerPionsMotion;
	}


	public MouseAdapter getSelectionnerPions() {
		return selectionnerPions;
	}


	/* Selection du pion a la souris */
	private MouseAdapter selectionnerPions = new MouseAdapter() {
		public void mousePressed(MouseEvent evt) {
			setCliqueX(evt.getX());
			setCliqueY(evt.getY());
			for (Pion pion : jeu.getReserve().getPions()) {
				if (pion.contains(getCliqueX(), getCliqueY())) {
					for (ArrayList<Case> alCase : jeu.getGrille().getListCases()) {
						for (Case caseJeu : alCase) {
							if (caseJeu.contains(getCliqueX(), getCliqueY())) {
								caseJeu.setEtatActuel(caseJeu.getEtatInitial());
								jeu.getPionsEnJeu().remove(pion);
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
				// Si pas de case select, le pion retourne dans la reserve
				
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

					jeu.getPionSelectionne().setX(
							jeu.getPionSelectionne().getxInitial());
					jeu.getPionSelectionne().setY(
							jeu.getPionSelectionne().getyInitial());
					jeu.setPionSelectionne(null);

				}

				else {
					// sinon on le positionne sur la case
					jeu.getPionSelectionne().setX(
							jeu.getGrille().getListCases()
									.get(jeu.getIndiceCaseH())
									.get(jeu.getIndiceCaseV()).getX() + 3);
					jeu.getPionSelectionne().setY(
							jeu.getGrille().getListCases()
									.get(jeu.getIndiceCaseH())
									.get(jeu.getIndiceCaseV()).getY() + 4);
					jeu.getGrille().getListCases().get(jeu.getIndiceCaseH())
							.get(jeu.getIndiceCaseV())
							.setEtatActuel(CaseEnum.OCCUPEE);
					
					List<Pion> tmp = new ArrayList<>(jeu.getPionsEnJeu());
					tmp.add(jeu.getPionSelectionne());
					jeu.setPionsEnJeu(tmp);

					PionManager pm = new PionManager(jeu.getGrille(),
							(ArrayList<Pion>) jeu.getPionsEnJeu(),
							jeu.getIndiceCaseH(), jeu.getIndiceCaseV(), false);
					pm.contaminationListPion();

					jeu.setPionSelectionne(null);

				}
			}
			jeu.repaint();

		}
	};

	private MouseMotionAdapter selectionnerPionsMotion = new MouseMotionAdapter() {
		/* Déplacement a la souris */
		public void mouseDragged(MouseEvent evt) {
			if (jeu.getPionSelectionne() != null) {
				int translateX = evt.getX() - getCliqueX();
				int translateY = evt.getY() - getCliqueY();
				jeu.getPionSelectionne().setX(
						jeu.getPionSelectionne().getX() + translateX);
				jeu.getPionSelectionne().setY(
						jeu.getPionSelectionne().getY() + translateY);
				setCliqueX(evt.getX());
				setCliqueY(evt.getY());

				boolean survol = false;
				int j = 0, k;
				for (ArrayList<Case> alCase : jeu.getGrille().getListCases()) {
					survol = false;
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
								survol = true;

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

				if (survol) {
					PionManager pm = new PionManager(jeu.getGrille(),
							(ArrayList<Pion>) jeu.getPionsEnJeu(),
							jeu.getIndiceCaseH(), jeu.getIndiceCaseV(), true);
					pm.contaminationListPion();
				}
				jeu.repaint();

			}
		}
	};

}
