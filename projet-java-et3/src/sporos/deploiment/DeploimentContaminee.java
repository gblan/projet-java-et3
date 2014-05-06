package sporos.deploiment;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.List;

import javax.swing.Timer;

import sporos.grille.Grille;
import sporos.grille.cases.CaseEnum;
import sporos.jeu.JeuView;
import sporos.reserve.pions.PionModel;

public class DeploimentContaminee extends Deploiment {

	private List<PionModel> listPions;
	private Hashtable<String, Timer> timersDeploiment = new Hashtable<String, Timer>();
	private JeuView jeuView;
	private int vitesse;
	private Timer endTimer;

	public DeploimentContaminee(Grille grille, List<PionModel> listPions,
			JeuView jeuView, int vitesse) {
		super(grille);
		this.listPions = listPions;
		this.jeuView = jeuView;
		this.vitesse = vitesse;

	}

	public List<PionModel> getListPions() {
		return listPions;
	}

	public void setListPions(List<PionModel> listPions) {
		this.listPions = listPions;
	}

	public void deploimentListPion() {
		for (PionModel p : getListPions()) {
			super.deploimentPion(p);
		}

	}

	public void deploimentInstantaneListPion() {
		for (PionModel p : getListPions()) {
			this.deploimentInstantanePion(p);
		}

	}

	public void deploimentInstantanePion(PionModel p) {
		switch (p.getTypePion()) {
		case TYPE1:
			deploimentInstantaneHorizontal(p);
			break;
		case TYPE2:
			deploimentInstantaneMontante(p);
			break;
		case TYPE3:
			deploimentInstantaneDescendante(p);
			break;
		case TYPE4:
			deploimentInstantaneDescendante(p);
			deploimentInstantaneHorizontal(p);
			break;
		case TYPE5:
			deploimentInstantaneMontante(p);
			deploimentInstantaneHorizontal(p);
			break;
		case TYPE6:
			deploimentInstantaneMontante(p);
			deploimentInstantaneDescendante(p);
			break;
		case TYPE7:
			deploimentInstantaneHorizontal(p);
			deploimentInstantaneMontante(p);
			deploimentInstantaneDescendante(p);
			break;
		default:
			break;
		}
	}

	/**
	 * @return propagation du pion vers la gauche puis vers la droite
	 */
	private void deploimentInstantaneHorizontal(PionModel p) {
		deploimentInstantaneGauche(p);
		deploimentInstantaneDroite(p);
	}

	private void deploimentInstantaneMontante(PionModel p) {
		deploimentInstantaneBasGauche(p);
		deploimentInstantaneHautDroite(p);
	}

	private void deploimentInstantaneDescendante(PionModel p) {
		deploimentInstantaneHautGauche(p);
		deploimentInstantaneBasDroit(p);
	}

	protected void deploimentInstantaneGauche(PionModel p) {
		/* Vers la gauche */

		for (int i = p.getIndiceCaseV() - 1; i >= 0; i--) {
			/*
			 * On arrete le deploiment lorsque on rencontre une case desactivee
			 * ou occupee
			 */

			if ((super.getGrille().getListCases().get(p.getIndiceCaseH())
					.get(i).getEtatActuel().equals(CaseEnum.DESACTIVEE))
					|| (super.getGrille().getListCases()
							.get(p.getIndiceCaseH()).get(i).getEtatActuel()
							.equals(CaseEnum.OCCUPEE))) {

				break;

			}

			/*
			 * On continue le deploiment lorsque on rencontre une case
			 * contaminee ou disponible
			 */
			else if (super.getGrille().getListCases().get(p.getIndiceCaseH())
					.get(i).getEtatActuel().equals(CaseEnum.POTENTIELLE)) {
				/* Transformation de la case en case potentielle */
				super.getGrille().getListCases().get(p.getIndiceCaseH()).get(i)
						.setEtatActuel(CaseEnum.CONTAMINEE);

			}
		}
	}

	protected void deploimentInstantaneDroite(PionModel p) {

		/* Vers la droite */
		for (int j = p.getIndiceCaseV() + 1; j < 7; j++) {
			if ((super.getGrille().getListCases().get(p.getIndiceCaseH())
					.get(j).getEtatActuel().equals(CaseEnum.DESACTIVEE))
					|| (super.getGrille().getListCases()
							.get(p.getIndiceCaseH()).get(j).getEtatActuel()
							.equals(CaseEnum.OCCUPEE))) {
				break;
			} else if (super.getGrille().getListCases().get(p.getIndiceCaseH())
					.get(j).getEtatActuel().equals(CaseEnum.POTENTIELLE)) {

				/* SURVOL */
				super.getGrille().getListCases().get(p.getIndiceCaseH()).get(j)
						.setEtatActuel(CaseEnum.CONTAMINEE);

			}

		}

	}

	protected void deploimentInstantaneBasDroit(PionModel p) {
		int ajout = 0;

		/* vers le bas droit */
		for (int j = p.getIndiceCaseH() + 1; j < 10; j++) {
			try {
				if (j % 2 == 1) {
					ajout++;
				}
				if ((super.getGrille().getListCases().get(j)
						.get(p.getIndiceCaseV() + ajout).getEtatActuel()
						.equals(CaseEnum.DESACTIVEE))
						|| (super.getGrille().getListCases().get(j)
								.get(p.getIndiceCaseV() + ajout)
								.getEtatActuel().equals(CaseEnum.OCCUPEE))) {
					break;
				} else if (super.getGrille().getListCases().get(j)
						.get(p.getIndiceCaseV() + ajout).getEtatActuel()
						.equals(CaseEnum.POTENTIELLE)) {

					super.getGrille().getListCases().get(j)
							.get(p.getIndiceCaseV() + ajout)
							.setEtatActuel(CaseEnum.CONTAMINEE);

				}
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}

	}

	protected void deploimentInstantaneHautGauche(PionModel p) {
		int ajout = 0;
		/* vers le haut gauche */
		for (int j = p.getIndiceCaseH() - 1; j >= 0; j--) {
			try {
				if (j % 2 == 0) {
					ajout++;
				}
				if ((super.getGrille().getListCases().get(j)
						.get(p.getIndiceCaseV() - ajout).getEtatActuel()
						.equals(CaseEnum.DESACTIVEE))
						|| (super.getGrille().getListCases().get(j)
								.get(p.getIndiceCaseV() - ajout)
								.getEtatActuel().equals(CaseEnum.OCCUPEE))) {
					break;
				} else if (super.getGrille().getListCases().get(j)
						.get(p.getIndiceCaseV() - ajout).getEtatActuel()
						.equals(CaseEnum.POTENTIELLE)) {

					super.getGrille().getListCases().get(j)
							.get(p.getIndiceCaseV() - ajout)
							.setEtatActuel(CaseEnum.CONTAMINEE);

				}
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}
	}

	protected void deploimentInstantaneHautDroite(PionModel p) {
		int ajout = 0;

		/* vers le haut droit */
		for (int j = p.getIndiceCaseH() - 1; j >= 0; j--) {
			try {
				if (j % 2 == 1) {
					ajout--;
				}
				if ((super.getGrille().getListCases().get(j)
						.get(p.getIndiceCaseV() - ajout).getEtatActuel()
						.equals(CaseEnum.DESACTIVEE))
						|| (super.getGrille().getListCases().get(j)
								.get(p.getIndiceCaseV() - ajout)
								.getEtatActuel().equals(CaseEnum.OCCUPEE))) {
					break;
				} else if (super.getGrille().getListCases().get(j)
						.get(p.getIndiceCaseV() - ajout).getEtatActuel()
						.equals(CaseEnum.POTENTIELLE)) {

					super.getGrille().getListCases().get(j)
							.get(p.getIndiceCaseV() - ajout)
							.setEtatActuel(CaseEnum.CONTAMINEE);

				}
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}
	}

	protected void deploimentInstantaneBasGauche(PionModel p) {
		int ajout = 0;
		/* bas gauche */
		for (int j = p.getIndiceCaseH() + 1; j < 10; j++) {
			try {
				if (j % 2 == 0) {
					ajout++;
				}
				if ((super.getGrille().getListCases().get(j)
						.get(p.getIndiceCaseV() - ajout).getEtatActuel()
						.equals(CaseEnum.DESACTIVEE))
						|| (super.getGrille().getListCases().get(j)
								.get(p.getIndiceCaseV() - ajout)
								.getEtatActuel().equals(CaseEnum.OCCUPEE))) {
					break;
				} else if (super.getGrille().getListCases().get(j)
						.get(p.getIndiceCaseV() - ajout).getEtatActuel()
						.equals(CaseEnum.POTENTIELLE)) {

					super.getGrille().getListCases().get(j)
							.get(p.getIndiceCaseV() - ajout)
							.setEtatActuel(CaseEnum.CONTAMINEE);

				}
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}
	}

	@Override
	protected void deploimentGauche(final PionModel p) {
		/* Vers la gauche */
		final Grille grille = super.getGrille();
		if (p.getIndiceCaseV() - 1 == -1) {
			p.setCaseCouranteDeplacementGauche(0);
		} else {
			p.setCaseCouranteDeplacementGauche(p.getIndiceCaseV() - 1);
		}
		Timer timer = new Timer(vitesse, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * On arrete le deploiment lorsque on rencontre une case
				 * desactivee ou occupee
				 */

				if ((grille.getListCases().get(p.getIndiceCaseH())
						.get(p.getCaseCouranteDeplacementGauche())
						.getEtatActuel().equals(CaseEnum.DESACTIVEE))
						|| (grille.getListCases().get(p.getIndiceCaseH())
								.get(p.getCaseCouranteDeplacementGauche())
								.getEtatActuel().equals(CaseEnum.OCCUPEE))) {

					timersDeploiment.get("gauche" + p.hashCode()).stop();
					timersDeploiment.remove("gauche" + p.hashCode());
//					System.out.println(timersDeploiment);

				}

				/*
				 * On continue le deploiment lorsque on rencontre une case
				 * contaminee ou disponible
				 */
				else if (grille.getListCases().get(p.getIndiceCaseH())
						.get(p.getCaseCouranteDeplacementGauche())
						.getEtatActuel().equals(CaseEnum.POTENTIELLE)
						|| grille.getListCases().get(p.getIndiceCaseH())
								.get(p.getCaseCouranteDeplacementGauche())
								.getEtatActuel().equals(CaseEnum.CONTAMINEE)) {
					/* Transformation de la case en case potentielle */
					grille.getListCases().get(p.getIndiceCaseH())
							.get(p.getCaseCouranteDeplacementGauche())
							.setEtatActuel(CaseEnum.CONTAMINEE);
					if (p.getCaseCouranteDeplacementGauche() == 0
							|| p.getCaseCouranteDeplacementGauche() == -1) {
						timersDeploiment.get("gauche" + p.hashCode()).stop();
						timersDeploiment.remove("gauche" + p.hashCode());

					} else {
						p.setCaseCouranteDeplacementGauche(p
								.getCaseCouranteDeplacementGauche() - 1);
					}

				}
				jeuView.repaint();
			}

		});
		timer.start();
		timersDeploiment.put("gauche" + p.hashCode(), timer);

	}

	@Override
	protected void deploimentDroite(final PionModel p) {

		final Grille grille = super.getGrille();
		if (p.getIndiceCaseV() + 1 == grille.getListCases()
				.get(p.getIndiceCaseH()).size()) {
			p.setCaseCouranteDeplacementDroite(p.getIndiceCaseV());
		} else {
			p.setCaseCouranteDeplacementDroite(p.getIndiceCaseV() + 1);
		}
		Timer timer = new Timer(vitesse, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * On arrete le deploiment lorsque on rencontre une case
				 * desactivee ou occupee
				 */

				if ((grille.getListCases().get(p.getIndiceCaseH())
						.get(p.getCaseCouranteDeplacementDroite())
						.getEtatActuel().equals(CaseEnum.DESACTIVEE))
						|| (grille.getListCases().get(p.getIndiceCaseH())
								.get(p.getCaseCouranteDeplacementDroite())
								.getEtatActuel().equals(CaseEnum.OCCUPEE))) {

					timersDeploiment.get("droite" + p.hashCode()).stop();
					timersDeploiment.remove("droite" + p.hashCode());

				}

				/*
				 * On continue le deploiment lorsque on rencontre une case
				 * contaminee ou disponible
				 */
				else if (grille.getListCases().get(p.getIndiceCaseH())
						.get(p.getCaseCouranteDeplacementDroite())
						.getEtatActuel().equals(CaseEnum.POTENTIELLE)
						|| grille.getListCases().get(p.getIndiceCaseH())
								.get(p.getCaseCouranteDeplacementDroite())
								.getEtatActuel().equals(CaseEnum.CONTAMINEE)) {
					/* Transformation de la case en case potentielle */
					grille.getListCases().get(p.getIndiceCaseH())
							.get(p.getCaseCouranteDeplacementDroite())
							.setEtatActuel(CaseEnum.CONTAMINEE);
					if (p.getCaseCouranteDeplacementDroite() == grille
							.getListCases().get(p.getIndiceCaseH()).size() - 1
							|| p.getCaseCouranteDeplacementDroite() == grille
									.getListCases().get(p.getIndiceCaseH())
									.size()) {

						timersDeploiment.get("droite" + p.hashCode()).stop();
						timersDeploiment.remove("droite" + p.hashCode());

//						System.out.println(timersDeploiment);
					} else {
						p.setCaseCouranteDeplacementDroite(p
								.getCaseCouranteDeplacementDroite() + 1);
					}

				}
				jeuView.repaint();
			}

		});
		timer.start();
		timersDeploiment.put("droite" + p.hashCode(), timer);

	}

	@Override
	protected void deploimentBasDroit(final PionModel p) {

		p.setAjoutBasDroite(0);
		/* vers le bas droit */
		final Grille grille = super.getGrille();
		p.setCaseCouranteDeplacementBasDroite(p.getIndiceCaseH() + 1);
		Timer timer = new Timer(vitesse, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * On arrete le deploiment lorsque on rencontre une case
				 * desactivee ou occupee
				 */

				try {
					if (p.getCaseCouranteDeplacementBasDroite() % 2 == 1) {
						p.setAjoutBasDroite(p.getAjoutBasDroite() + 1);
					}

					if ((grille.getListCases()
							.get(p.getCaseCouranteDeplacementBasDroite())
							.get(p.getIndiceCaseV() + p.getAjoutBasDroite())
							.getEtatActuel().equals(CaseEnum.DESACTIVEE))
							|| (grille
									.getListCases()
									.get(p.getCaseCouranteDeplacementBasDroite())
									.get(p.getIndiceCaseV()
											+ p.getAjoutBasDroite())
									.getEtatActuel().equals(CaseEnum.OCCUPEE))) {

						timersDeploiment.get("basdroite" + p.hashCode()).stop();
						timersDeploiment.remove("basdroite" + p.hashCode());
						p.setAjoutBasDroite(0);

//						System.out.println(timersDeploiment);

					}

					/*
					 * On continue le deploiment lorsque on rencontre une case
					 * contaminee ou disponible
					 */
					else if (grille.getListCases()
							.get(p.getCaseCouranteDeplacementBasDroite())
							.get(p.getIndiceCaseV() + p.getAjoutBasDroite())
							.getEtatActuel().equals(CaseEnum.POTENTIELLE)
							|| grille
									.getListCases()
									.get(p.getCaseCouranteDeplacementBasDroite())
									.get(p.getIndiceCaseV()
											+ p.getAjoutBasDroite())
									.getEtatActuel()
									.equals(CaseEnum.CONTAMINEE)) {
						/* Transformation de la case en case potentielle */
						grille.getListCases()
								.get(p.getCaseCouranteDeplacementBasDroite())
								.get(p.getIndiceCaseV() + p.getAjoutBasDroite())
								.setEtatActuel(CaseEnum.CONTAMINEE);

						p.setCaseCouranteDeplacementBasDroite(p
								.getCaseCouranteDeplacementBasDroite() + 1);

					}

				} catch (IndexOutOfBoundsException e1) {
					timersDeploiment.get("basdroite" + p.hashCode()).stop();
					timersDeploiment.remove("basdroite" + p.hashCode());
					p.setAjoutBasDroite(0);

				}
				jeuView.repaint();
			}

		});
		timer.start();
		timersDeploiment.put("basdroite" + p.hashCode(), timer);
	}

	@Override
	protected void deploimentHautGauche(final PionModel p) {

		p.setAjoutHautGauche(0);
		/* vers le haut droit */
		final Grille grille = super.getGrille();
		p.setCaseCouranteDeplacementHautGauche(p.getIndiceCaseH() - 1);
		Timer timer = new Timer(vitesse, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * On arrete le deploiment lorsque on rencontre une case
				 * desactivee ou occupee
				 */

				try {
					if (p.getCaseCouranteDeplacementHautGauche() % 2 == 0) {
						p.setAjoutHautGauche(p.getAjoutHautGauche() + 1);
					}

					if ((grille.getListCases()
							.get(p.getCaseCouranteDeplacementHautGauche())
							.get(p.getIndiceCaseV() - p.getAjoutHautGauche())
							.getEtatActuel().equals(CaseEnum.DESACTIVEE))
							|| (grille
									.getListCases()
									.get(p.getCaseCouranteDeplacementHautGauche())
									.get(p.getIndiceCaseV()
											- p.getAjoutHautGauche())
									.getEtatActuel().equals(CaseEnum.OCCUPEE))) {

						timersDeploiment.get("hautgauche" + p.hashCode())
								.stop();
						timersDeploiment.remove("hautgauche" + p.hashCode());
						p.setAjoutHautGauche(0);

					}

					/*
					 * On continue le deploiment lorsque on rencontre une case
					 * contaminee ou disponible
					 */
					else if (grille.getListCases()
							.get(p.getCaseCouranteDeplacementHautGauche())
							.get(p.getIndiceCaseV() - p.getAjoutHautGauche())
							.getEtatActuel().equals(CaseEnum.POTENTIELLE)
							|| grille
									.getListCases()
									.get(p.getCaseCouranteDeplacementHautGauche())
									.get(p.getIndiceCaseV()
											- p.getAjoutHautGauche())
									.getEtatActuel()
									.equals(CaseEnum.CONTAMINEE)) {
						/* Transformation de la case en case potentielle */
						grille.getListCases()
								.get(p.getCaseCouranteDeplacementHautGauche())
								.get(p.getIndiceCaseV()
										- p.getAjoutHautGauche())
								.setEtatActuel(CaseEnum.CONTAMINEE);

						p.setCaseCouranteDeplacementHautGauche(p
								.getCaseCouranteDeplacementHautGauche() - 1);

					}

				} catch (IndexOutOfBoundsException e1) {
					timersDeploiment.get("hautgauche" + p.hashCode()).stop();
					timersDeploiment.remove("hautgauche" + p.hashCode());
					p.setAjoutHautGauche(0);

				}
				jeuView.repaint();
			}

		});
		timer.start();
		timersDeploiment.put("hautgauche" + p.hashCode(), timer);
	}

	@Override
	protected void deploimentHautDroite(final PionModel p) {
		p.setAjoutHautDroite(0);
		/* vers le haut droit */
		final Grille grille = super.getGrille();
		p.setCaseCouranteDeplacementHautDroite(p.getIndiceCaseH() - 1);
		Timer timer = new Timer(vitesse, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * On arrete le deploiment lorsque on rencontre une case
				 * desactivee ou occupee
				 */

				try {
					if (p.getCaseCouranteDeplacementHautDroite() % 2 == 1) {
						p.setAjoutHautDroite(p.getAjoutHautDroite() - 1);
					}

					if ((grille.getListCases()
							.get(p.getCaseCouranteDeplacementHautDroite())
							.get(p.getIndiceCaseV() - p.getAjoutHautDroite())
							.getEtatActuel().equals(CaseEnum.DESACTIVEE))
							|| (grille
									.getListCases()
									.get(p.getCaseCouranteDeplacementHautDroite())
									.get(p.getIndiceCaseV()
											- p.getAjoutHautDroite())
									.getEtatActuel().equals(CaseEnum.OCCUPEE))) {

						timersDeploiment.get("hautdroite" + p.hashCode())
								.stop();
						timersDeploiment.remove("hautdroite" + p.hashCode());
						p.setAjoutHautDroite(0);

					}

					/*
					 * On continue le deploiment lorsque on rencontre une case
					 * contaminee ou disponible
					 */
					else if (grille.getListCases()
							.get(p.getCaseCouranteDeplacementHautDroite())
							.get(p.getIndiceCaseV() - p.getAjoutHautDroite())
							.getEtatActuel().equals(CaseEnum.POTENTIELLE)
							|| (grille
									.getListCases()
									.get(p.getCaseCouranteDeplacementHautDroite())
									.get(p.getIndiceCaseV()
											- p.getAjoutHautDroite())
									.getEtatActuel()
									.equals(CaseEnum.CONTAMINEE))) {
						/* Transformation de la case en case potentielle */
						grille.getListCases()
								.get(p.getCaseCouranteDeplacementHautDroite())
								.get(p.getIndiceCaseV()
										- p.getAjoutHautDroite())
								.setEtatActuel(CaseEnum.CONTAMINEE);

						p.setCaseCouranteDeplacementHautDroite(p
								.getCaseCouranteDeplacementHautDroite() - 1);

					}

				} catch (IndexOutOfBoundsException e1) {
					timersDeploiment.get("hautdroite" + p.hashCode()).stop();
					timersDeploiment.remove("hautdroite" + p.hashCode());
					p.setAjoutHautDroite(0);

				}
				jeuView.repaint();
			}

		});
		timer.start();
		timersDeploiment.put("hautdroite" + p.hashCode(), timer);
	}

	@Override
	protected void deploimentBasGauche(final PionModel p) {
		p.setAjoutBasGauche(0);
		/* vers le haut droit */
		final Grille grille = super.getGrille();
		p.setCaseCouranteDeplacementBasGauche(p.getIndiceCaseH() + 1);
		Timer timer = new Timer(vitesse, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * On arrete le deploiment lorsque on rencontre une case
				 * desactivee ou occupee
				 */

				try {
					if (p.getCaseCouranteDeplacementBasGauche() % 2 == 0) {
						p.setAjoutBasGauche(p.getAjoutBasGauche() + 1);
					}

					if ((grille.getListCases()
							.get(p.getCaseCouranteDeplacementBasGauche())
							.get(p.getIndiceCaseV() - p.getAjoutBasGauche())
							.getEtatActuel().equals(CaseEnum.DESACTIVEE))
							|| (grille
									.getListCases()
									.get(p.getCaseCouranteDeplacementBasGauche())
									.get(p.getIndiceCaseV()
											- p.getAjoutBasGauche())
									.getEtatActuel().equals(CaseEnum.OCCUPEE))) {

						timersDeploiment.get("basgauche" + p.hashCode()).stop();
						timersDeploiment.remove("basgauche" + p.hashCode());
						p.setAjoutBasGauche(0);

					}

					/*
					 * On continue le deploiment lorsque on rencontre une case
					 * contaminee ou disponible
					 */
					else if (grille.getListCases()
							.get(p.getCaseCouranteDeplacementBasGauche())
							.get(p.getIndiceCaseV() - p.getAjoutBasGauche())
							.getEtatActuel().equals(CaseEnum.POTENTIELLE)
							|| (grille
									.getListCases()
									.get(p.getCaseCouranteDeplacementBasGauche())
									.get(p.getIndiceCaseV()
											- p.getAjoutBasGauche())
									.getEtatActuel()
									.equals(CaseEnum.CONTAMINEE))) {
						/* Transformation de la case en case potentielle */
						grille.getListCases()
								.get(p.getCaseCouranteDeplacementBasGauche())
								.get(p.getIndiceCaseV() - p.getAjoutBasGauche())
								.setEtatActuel(CaseEnum.CONTAMINEE);

						p.setCaseCouranteDeplacementBasGauche(p
								.getCaseCouranteDeplacementBasGauche() + 1);

					}

				} catch (IndexOutOfBoundsException e1) {
					timersDeploiment.get("basgauche" + p.hashCode()).stop();
					timersDeploiment.remove("basgauche" + p.hashCode());
					p.setAjoutBasGauche(0);

				}
				jeuView.repaint();
			}

		});
		timer.start();
		timersDeploiment.put("basgauche" + p.hashCode(), timer);
	}

}
