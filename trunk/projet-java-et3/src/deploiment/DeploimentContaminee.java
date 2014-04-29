package deploiment;

import java.util.List;

import et3.grille.Grille;
import et3.grille.cases.CaseEnum;
import et3.reserve.pions.PionModel;

public class DeploimentContaminee extends Deploiment {
	

	private List<PionModel> listPions;

	public DeploimentContaminee(Grille grille, List<PionModel> listPions,
			int indiceCaseH, int indiceCaseV) {
		super(grille, indiceCaseH, indiceCaseV);
		this.listPions=listPions;
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
	protected void deploimentGauche() {
		/* Vers la gauche */
		for (int i = super.getIndiceCaseV() - 1; i >= 0; i--) {
			/*
			 * On arrete le deploiment lorsque on rencontre une case desactivee
			 * ou occupee
			 */
			if ((super.getGrille().getListCases().get(super.getIndiceCaseH())
					.get(i).getEtatActuel().equals(CaseEnum.DESACTIVEE))
					|| (super.getGrille().getListCases()
							.get(super.getIndiceCaseH()).get(i).getEtatActuel()
							.equals(CaseEnum.OCCUPEE))) {
				break;
			}

			/*
			 * On continue le deploiment lorsque on rencontre une case
			 * contaminee ou disponible
			 */
			else if (super.getGrille().getListCases()
					.get(super.getIndiceCaseH()).get(i).getEtatActuel()
					.equals(CaseEnum.DISPONIBLE)) {
				/* Transformation de la case en case potentielle */
				super.getGrille().getListCases().get(super.getIndiceCaseH())
						.get(i).setEtatActuel(CaseEnum.CONTAMINEE);

			}
		}
	}

	@Override
	protected void deploimentDroite() {
		
		/* Vers la droite */
		for (int j = super.getIndiceCaseV() + 1; j < 7; j++) {
			if ((super.getGrille().getListCases().get(super.getIndiceCaseH()).get(j)
					.getEtatActuel().equals(CaseEnum.DESACTIVEE))
					|| (super.getGrille().getListCases().get(super.getIndiceCaseH()).get(j)
							.getEtatActuel().equals(CaseEnum.OCCUPEE))) {
				break;
			} else if (super.getGrille().getListCases().get(super.getIndiceCaseH()).get(j)
					.getEtatActuel().equals(CaseEnum.DISPONIBLE)) {

				/* SURVOL */
				super.getGrille().getListCases().get(super.getIndiceCaseH()).get(j)
						.setEtatActuel(CaseEnum.CONTAMINEE);

			}

		}

	}

	@Override
	protected void deploimentBasDroit() {
		int ajout = 0;

		/* vers le bas droit */
		for (int j = super.getIndiceCaseH() + 1; j < 10; j++) {
			try {
				if (j % 2 == 1) {
					ajout++;
				}
				if ((super.getGrille().getListCases().get(j)
						.get(super.getIndiceCaseV() + ajout).getEtatActuel()
						.equals(CaseEnum.DESACTIVEE))
						|| (super.getGrille().getListCases().get(j)
								.get(super.getIndiceCaseV() + ajout).getEtatActuel()
								.equals(CaseEnum.OCCUPEE))) {
					break;
				} else if (super.getGrille().getListCases().get(j)
						.get(super.getIndiceCaseV() + ajout).getEtatActuel()
						.equals(CaseEnum.DISPONIBLE)) {
					
						super.getGrille().getListCases().get(j)
								.get(super.getIndiceCaseV() + ajout)
								.setEtatActuel(CaseEnum.CONTAMINEE);
					
				}
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}

	}

	@Override
	protected void deploimentHautGauche() {
		int ajout = 0;
		/* vers le haut gauche */
		for (int j = super.getIndiceCaseH() - 1; j >= 0; j--) {
			try {
				if (j % 2 == 0) {
					ajout++;
				}
				if ((super.getGrille().getListCases().get(j)
						.get(super.getIndiceCaseV() - ajout).getEtatActuel()
						.equals(CaseEnum.DESACTIVEE))
						|| (super.getGrille().getListCases().get(j)
								.get(super.getIndiceCaseV() - ajout).getEtatActuel()
								.equals(CaseEnum.OCCUPEE))) {
					break;
				} else if (super.getGrille().getListCases().get(j)
						.get(super.getIndiceCaseV() - ajout).getEtatActuel()
						.equals(CaseEnum.DISPONIBLE)) {

					super.getGrille().getListCases().get(j)
							.get(super.getIndiceCaseV() - ajout)
							.setEtatActuel(CaseEnum.CONTAMINEE);
					
				}
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}
	}

	@Override
	protected void deploimentHautDroite() {
		int ajout = 0;

		/* vers le haut droit */
		for (int j = super.getIndiceCaseH() - 1; j >= 0; j--) {
			try {
				if (j % 2 == 1) {
					ajout--;
				}
				if ((super.getGrille().getListCases().get(j)
						.get(super.getIndiceCaseV() - ajout).getEtatActuel()
						.equals(CaseEnum.DESACTIVEE))
						|| (super.getGrille().getListCases().get(j)
								.get(super.getIndiceCaseV() - ajout).getEtatActuel()
								.equals(CaseEnum.OCCUPEE))) {
					break;
				} else if (super.getGrille().getListCases().get(j)
						.get(super.getIndiceCaseV() - ajout).getEtatActuel()
						.equals(CaseEnum.DISPONIBLE)) {

				
						super.getGrille().getListCases().get(j)
								.get(super.getIndiceCaseV() - ajout)
								.setEtatActuel(CaseEnum.CONTAMINEE
										);
					
				}
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}
	}

	@Override
	protected void deploimentBasGauche() {
		int ajout = 0;
		/* bas gauche */
		for (int j = super.getIndiceCaseH() + 1; j < 10; j++) {
			try {
				if (j % 2 == 0) {
					ajout++;
				}
				if ((super.getGrille().getListCases().get(j)
						.get(super.getIndiceCaseV() - ajout).getEtatActuel()
						.equals(CaseEnum.DESACTIVEE))
						|| (super.getGrille().getListCases().get(j)
								.get(super.getIndiceCaseV() - ajout).getEtatActuel()
								.equals(CaseEnum.OCCUPEE))) {
					break;
				} else if (super.getGrille().getListCases().get(j)
						.get(super.getIndiceCaseV() - ajout).getEtatActuel()
						.equals(CaseEnum.DISPONIBLE)) {

						super.getGrille().getListCases().get(j)
								.get(super.getIndiceCaseV() - ajout)
								.setEtatActuel(CaseEnum.CONTAMINEE);
					
				}
			} catch (IndexOutOfBoundsException e) {
				break;
			}
		}
	}

	
}



