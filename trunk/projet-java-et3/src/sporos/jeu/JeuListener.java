package sporos.jeu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import sporos.animation.Animation;
import sporos.deploiment.DeploimentContaminee;
import sporos.deploiment.DeploimentSurvolee;
import sporos.grille.GrilleEnum;
import sporos.grille.cases.CaseEnum;
import sporos.grille.cases.CaseModel;
import sporos.main.Principale;
import sporos.menus.MenuPrincipal;
import sporos.reserve.pions.PionModel;
import sporos.utils.Bruitages;
import sporos.utils.PropertyAcces;

public class JeuListener {

	private JeuModel jeuModel;
	private JeuView jeuView;
	private Bruitages bruits;
	private int cliqueX;
	private int cliqueY;
	private Timer endTimer;
	private Hashtable<PionModel, Timer> timers = new Hashtable<PionModel, Timer>();
	private Principale principale;

	public JeuListener(JeuModel jeu, final JeuView jeuView,
			final Principale principale) {
		this.jeuModel = jeu;
		this.jeuView = jeuView;
		this.principale =principale;
		this.bruits = Bruitages.getInstance();
		this.endTimer = new Timer(1, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (jeuView.getJeu().isFinish()) {
					endTimer.stop();
					jeuView.repaint();
					Bruitages bruitage = Bruitages.getInstance();
					bruitage.playSong("resources/sounds/fun.wav");

					int retour = JOptionPane.showConfirmDialog(null,
							"Voulez vous passer au niveau suivant ?",
							"EXCELLENT", JOptionPane.OK_CANCEL_OPTION);

					/* Sauvegarde */
					PropertyAcces
							.saveProperties(jeuView.getJeu().getIdJeu() + 1);
					if (retour == 0) {
						// OK
						principale.kill();
						Principale av = new Principale(PropertyAcces
								.getCurrentLevel(), 300, 500, GrilleEnum.MOYEN, false);

					} else if (retour == 2) {
						// CANCEL
						principale.kill();
						MenuPrincipal av = new MenuPrincipal();

					} else if (retour == -1) {
						// QUIT
						System.exit(0);

					}

				}
			}
		});
		endTimer.start();
	}

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

	public Hashtable<PionModel, Timer> getTimers() {
		return timers;
	}

	public MouseMotionAdapter getSelectionnerPionsMotion() {
		return selectionnerPionsMotion;
	}

	public MouseAdapter getSelectionnerPions() {
		return selectionnerPions;
	}



	private MouseAdapter recommencerPartie = new MouseAdapter() {
		public void mousePressed(MouseEvent evt) {
			Principale p1 = new Principale(jeuModel.getIdJeu(), 300, 500,
					GrilleEnum.MOYEN, false);
		}

	};

	private MouseAdapter quitterNiveau = new MouseAdapter() {
		public void mousePressed(MouseEvent evt) {
			jeuView.setVisible(false);
			MenuPrincipal av = new MenuPrincipal();
		}

	};

	private MouseAdapter quitterPartie = new MouseAdapter() {
		public void mousePressed(MouseEvent evt) {
			System.exit(0);
		}
	};



	/* Selection du pion a la souris */
	private MouseAdapter selectionnerPions = new MouseAdapter() {
		public void mousePressed(MouseEvent evt) {
			setCliqueX(evt.getX());
			setCliqueY(evt.getY());
			if (evt.getX()>0 && evt.getX() <45 && evt.getY()>0 && evt.getY()<45){
				JeuView.buildMenuContextuel(principale);
			}
			for (PionModel pion : jeuModel.getReserve().getPions()) {
				if (pion.contains(getCliqueX(), getCliqueY())
						&& !jeuModel.getPionRelache().contains(pion)) {

					// Preparation au refresh du deploiment en mettant toutes
					// les cases potentielles
					if ((pion.getIndiceCaseH() != -1)
							&& (pion.getIndiceCaseV() != -1)) {

						for (ArrayList<CaseModel> alCase : jeuModel.getGrille()
								.getListCases()) {
							for (CaseModel caseJeu : alCase) {
								if (!caseJeu.getEtatActuel().equals(
										CaseEnum.DESACTIVEE)
										&& !caseJeu.getEtatActuel().equals(
												CaseEnum.OCCUPEE)) {
									caseJeu.setEtatActuel(CaseEnum.POTENTIELLE);

								}
							}
						}

					}

					jeuModel.getPionsEnJeu().remove(pion);
					jeuModel.setPionSelectionne(pion);

					// Case sous le pion devient potentielle
					if (jeuModel.getPionSelectionne().getIndiceCaseV() != -1
							&& jeuModel.getPionSelectionne().getIndiceCaseH() != -1) {
						jeuModel.getGrille()
								.getListCases()
								.get(jeuModel.getPionSelectionne()
										.getIndiceCaseH())
								.get(jeuModel.getPionSelectionne()
										.getIndiceCaseV())
								.setEtatActuel(CaseEnum.POTENTIELLE);
					}
					// Deploiment des contaminees
					DeploimentContaminee dc = new DeploimentContaminee(
							jeuModel.getGrille(), jeuModel.getPionsEnJeu(),
							jeuView, 0);

					dc.deploimentInstantaneListPion();

					// Les potentielles restantes devienne disponible
					if ((pion.getIndiceCaseH() != -1)
							&& (pion.getIndiceCaseV() != -1)) {
						for (ArrayList<CaseModel> alCase : jeuModel.getGrille()
								.getListCases()) {
							for (CaseModel caseJeu : alCase) {
								if (caseJeu.getEtatActuel().equals(
										CaseEnum.POTENTIELLE)) {
									caseJeu.setEtatActuel(CaseEnum.DISPONIBLE);
								}

							}
						}
						if (!jeuModel.getGrille().getListCases()
								.get(pion.getIndiceCaseH())
								.get(pion.getIndiceCaseV()).getEtatActuel()
								.equals(CaseEnum.CONTAMINEE)) {
							DeploimentSurvolee ds = new DeploimentSurvolee(
									jeuModel.getGrille());
							ds.deploimentPion(jeuModel.getPionSelectionne());
						}
					}
				}
			}
			jeuView.repaint();

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

					Animation.DeplacementPion(jeuModel, jeuView, timers, 1,
							jeuModel.getGrille().getTaille());

					jeuModel.getPionSelectionne().setIndiceCaseH(-1);
					jeuModel.getPionSelectionne().setIndiceCaseV(-1);

					DeploimentContaminee dc = new DeploimentContaminee(
							jeuModel.getGrille(), jeuModel.getPionsEnJeu(),
							jeuView, 100);
					dc.deploimentListPion();
					bruits.playSong("resources/sounds/ressort.wav");

					jeuModel.setPionSelectionne(null);

				} else {

			
					Animation.DeplacementPion(jeuModel, jeuView, timers, 0,
							jeuModel.getGrille().getTaille());
					jeuModel.getGrille().getListCases()
							.get(jeuModel.getIndiceCaseH())
							.get(jeuModel.getIndiceCaseV())
							.setEtatActuel(CaseEnum.OCCUPEE);
					jeuModel.getPionSelectionne().setIndiceCaseH(
							jeuModel.getIndiceCaseH());
					jeuModel.getPionSelectionne().setIndiceCaseV(
							jeuModel.getIndiceCaseV());

					/* On met a jour la liste des pions en jeu */
					List<PionModel> tmp = new ArrayList<PionModel>(
							jeuModel.getPionsEnJeu());
					tmp.add(jeuModel.getPionSelectionne());
					jeuModel.setPionsEnJeu(tmp);

					DeploimentContaminee dc = new DeploimentContaminee(
							jeuModel.getGrille(), jeuModel.getPionsEnJeu(),
							jeuView, 100);
					dc.deploimentListPion();

					/* APRES LA CONTAMINATION ON TESTE SI LE JEU EST FINI */

					jeuModel.setPionSelectionne(null);
					bruits.playSong("resources/sounds/ploc.wav");

				}
				jeuView.repaint();
			}
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
								jeuModel.getPionSelectionne().setIndiceCaseH(
										jeuModel.getIndiceCaseH());
								jeuModel.getPionSelectionne().setIndiceCaseV(
										jeuModel.getIndiceCaseV());
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

				/* Deploiment au survol */
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
