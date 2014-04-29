package et3.jeu;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import deploiment.DeploimentContaminee;
import deploiment.DeploimentSurvolee;
import main.Principale;
import et3.grille.cases.CaseEnum;
import et3.grille.cases.CaseModel;
import et3.reserve.pions.PionManager;
import et3.reserve.pions.PionModel;
import et3.sauvegarde.PropertyAcces;

public class JeuListener {

	/**
	 * 
	 */

	private JeuModel jeuModel;
	private JeuView jeuView;
	private int cliqueX;
	private int cliqueY;

	public void setJeu(JeuModel jeu) {
		this.jeuModel = jeu;
	}

	public JeuModel getJeu() {
		return this.jeuModel;
	}

	public JeuView getJeuView() {
		return jeuView;
	}

	public void setJeuView(JeuView jeuview) {
		this.jeuView = jeuview;
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

	public JeuListener(JeuModel jeu, JeuView jeuView) {
		this.jeuModel = jeu;
		this.jeuView = jeuView;
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
			for (PionModel pion : jeuModel.getReserve().getPions()) {
				if (pion.contains(getCliqueX(), getCliqueY())) {
					for (ArrayList<CaseModel> alCase : jeuModel.getGrille()
							.getListCases()) {
						for (CaseModel caseJeu : alCase) {

							caseJeu.setEtatActuel(caseJeu.getEtatInitial());

						}
					}

					jeuModel.getPionsEnJeu().remove(pion);
					jeuModel.setPionSelectionne(pion);
					DeploimentContaminee dc = new DeploimentContaminee(
							jeuModel.getGrille(), jeuModel.getPionsEnJeu());
					dc.deploimentListPion();

					jeuView.repaint();
					break;
				}
			}
		}

		public void mouseReleased(MouseEvent evt) {

			if (jeuModel.getPionSelectionne() != null) {

				// Si pas de case select, le pion retourne dans la reserve

				if (!(jeuModel.getGrille().getListCases()
						.get(jeuModel.getIndiceCaseH())
						.get(jeuModel.getIndiceCaseV()).intersect(jeuModel
						.getPionSelectionne()))
						|| jeuModel.getGrille().getListCases()
								.get(jeuModel.getIndiceCaseH())
								.get(jeuModel.getIndiceCaseV()).getEtatActuel()
								.toString().equals(CaseEnum.OCCUPEE.toString())) {

					jeuModel.getPionSelectionne().setX(
							jeuModel.getPionSelectionne().getxInitial());
					jeuModel.getPionSelectionne().setY(
							jeuModel.getPionSelectionne().getyInitial());
					jeuModel.setPionSelectionne(null);

				}

				else {

					// sinon on le positionne sur la case TODO ANIMATION
					jeuModel.getPionSelectionne().setX(
							jeuModel.getGrille().getListCases()
									.get(jeuModel.getIndiceCaseH())
									.get(jeuModel.getIndiceCaseV()).getX() + 3);
					jeuModel.getPionSelectionne().setY(
							jeuModel.getGrille().getListCases()
									.get(jeuModel.getIndiceCaseH())
									.get(jeuModel.getIndiceCaseV()).getY() + 4);
					jeuModel.getGrille().getListCases()
							.get(jeuModel.getIndiceCaseH())
							.get(jeuModel.getIndiceCaseV())
							.setEtatActuel(CaseEnum.OCCUPEE);
					jeuModel.getPionSelectionne().setIndiceCaseH(jeuModel.getIndiceCaseH());
					jeuModel.getPionSelectionne().setIndiceCaseV(jeuModel.getIndiceCaseV());
					/* On met a jour la liste des pions en jeu */
					List<PionModel> tmp = new ArrayList<PionModel>(
							jeuModel.getPionsEnJeu());
					tmp.add(jeuModel.getPionSelectionne());
					jeuModel.setPionsEnJeu(tmp);

					DeploimentContaminee dc = new DeploimentContaminee(
							jeuModel.getGrille(), jeuModel.getPionsEnJeu());
					dc.deploimentListPion();

					/* APRES LA CONTAMINATION ON TESTE SI LE JEU EST FINI */
					if (jeuModel.isFinish()) {
						jeuView.repaint();

						int retour = JOptionPane.showConfirmDialog(null,
								"Voulez vous passer au niveau suivant ?",
								"EXCELLENT", JOptionPane.OK_CANCEL_OPTION);

						/* Sauvegarde */
						PropertyAcces.saveProperties(jeuModel.getIdJeu());
						System.out.println(retour);
						if (retour == 2) {
							// OK
							System.exit(0);

							/* Transition vers nouvelle fenêtre */
							Principale p1 = new Principale("Sporos : niveau "
									+ jeuModel.getIdJeu(), jeuModel.getIdJeu(),
									300, 500);

						} else if (retour == 0) {
							// CANCEL

						} else if (retour == -1) {
							// QUIT
							System.out.println("bite3");

						}

					}
					jeuModel.setPionSelectionne(null);

				}
			}
			jeuView.repaint();

		}
	};

	private MouseMotionAdapter selectionnerPionsMotion = new MouseMotionAdapter() {
		/* Déplacement a la souris */
		public void mouseDragged(MouseEvent evt) {
			if (jeuModel.getPionSelectionne() != null) {

				/* Le pion suit la souris */
				int translateX = evt.getX() - getCliqueX();
				int translateY = evt.getY() - getCliqueY();
				jeuModel.getPionSelectionne().setX(
						jeuModel.getPionSelectionne().getX() + translateX);
				jeuModel.getPionSelectionne().setY(
						jeuModel.getPionSelectionne().getY() + translateY);
				setCliqueX(evt.getX());
				setCliqueY(evt.getY());

				/* La case survolée est jaune entouré de rouge */
				boolean survol = false;
				int j = 0, k;
				for (ArrayList<CaseModel> alCase : jeuModel.getGrille()
						.getListCases()) {
					k = 0;
					for (CaseModel caseJeu : alCase) {

						if (caseJeu.intersect(jeuModel.getPionSelectionne())
								&& (!caseJeu.getEtatActuel().toString()
										.equals(CaseEnum.DESACTIVEE.toString()))) {

							if (!(caseJeu.getEtatActuel().toString()
									.equals(CaseEnum.OCCUPEE.toString()))
									&& !(caseJeu.getEtatActuel().toString()
											.equals(CaseEnum.CONTAMINEE
													.toString()))) {

								caseJeu.setEtatActuel(CaseEnum.POTENTIELLESURVOLEE);
								jeuModel.setIndiceCaseH(j);
								jeuModel.setIndiceCaseV(k);
								jeuModel.getPionSelectionne().setIndiceCaseH(jeuModel.getIndiceCaseH());
								jeuModel.getPionSelectionne().setIndiceCaseV(jeuModel.getIndiceCaseV());
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

				/* Deploiment potentiel */
				if (survol) {

					DeploimentSurvolee ds = new DeploimentSurvolee(
							jeuModel.getGrille());
					ds.deploimentPion(jeuModel.getPionSelectionne());

				}
				jeuView.repaint();

			}
		}
	};

}
