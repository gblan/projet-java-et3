package sporos.deploiment;

import java.util.List;

import sporos.grille.Grille;
import sporos.grille.cases.CaseEnum;
import sporos.reserve.pions.PionModel;

public class DeploimentContaminee extends Deploiment {

	private List<PionModel> listPions;

	public DeploimentContaminee(Grille grille, List<PionModel> listPions) {
		super(grille);
		this.listPions = listPions;
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

	@Override
	protected void deploimentGauche(PionModel p) {
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

	@Override
	protected void deploimentDroite(PionModel p) {

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

	@Override
	protected void deploimentBasDroit(PionModel p) {
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

	@Override
	protected void deploimentHautGauche(PionModel p) {
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

	@Override
	protected void deploimentHautDroite(PionModel p) {
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

	@Override
	protected void deploimentBasGauche(PionModel p) {
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

}
