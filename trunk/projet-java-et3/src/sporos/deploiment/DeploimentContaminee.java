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
	private int caseCouranteDeplacementGauche;
	private int caseCouranteDeplacementDroite;
	private int caseCouranteDeplacementBasDroite;
	private int ajoutBasDroite;
	private int caseCouranteDeplacementBasGauche;
	private int ajoutBasGauche;
	private int caseCouranteDeplacementHautDroite;
	private int ajoutHautDroite;
	private int caseCouranteDeplacementHautGauche;
	private int ajoutHautGauche;

	public DeploimentContaminee(Grille grille, List<PionModel> listPions,
			JeuView jeuView) {
		super(grille);
		this.listPions = listPions;
		this.jeuView = jeuView;
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
	protected void deploimentGauche(final PionModel p) {
		/* Vers la gauche */
		final DeploimentContaminee deploiment = this;
		final Grille grille = super.getGrille();
		caseCouranteDeplacementGauche = p.getIndiceCaseV() - 1;
		Timer timer = new Timer(200, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * On arrete le deploiment lorsque on rencontre une case
				 * desactivee ou occupee
				 */

				if ((grille.getListCases().get(p.getIndiceCaseH())
						.get(caseCouranteDeplacementGauche).getEtatActuel()
						.equals(CaseEnum.DESACTIVEE))
						|| (grille.getListCases().get(p.getIndiceCaseH())
								.get(caseCouranteDeplacementGauche)
								.getEtatActuel().equals(CaseEnum.OCCUPEE))  ) {

					timersDeploiment.get("gauche").stop();
					timersDeploiment.remove("gauche");


				}

				/*
				 * On continue le deploiment lorsque on rencontre une case
				 * contaminee ou disponible
				 */
				else if (grille.getListCases().get(p.getIndiceCaseH())
						.get(caseCouranteDeplacementGauche).getEtatActuel()
						.equals(CaseEnum.POTENTIELLE)) {
					/* Transformation de la case en case potentielle */
					grille.getListCases().get(p.getIndiceCaseH())
					.get(caseCouranteDeplacementGauche)
					.setEtatActuel(CaseEnum.CONTAMINEE);
					if (caseCouranteDeplacementGauche == 0) {
						timersDeploiment.get("gauche").stop();
						timersDeploiment.remove("gauche");
					}
					else {
						caseCouranteDeplacementGauche--;
					}
					
				}
				jeuView.repaint();
			}

		});
		timer.start();
		timersDeploiment.put("gauche", timer);

	}

	@Override
	protected void deploimentDroite(final PionModel p) {

		final DeploimentContaminee deploiment = this;
		final Grille grille = super.getGrille();
		caseCouranteDeplacementDroite = p.getIndiceCaseV() + 1;
		Timer timer = new Timer(200, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * On arrete le deploiment lorsque on rencontre une case
				 * desactivee ou occupee
				 */

				if ((grille.getListCases().get(p.getIndiceCaseH())
						.get(caseCouranteDeplacementDroite).getEtatActuel()
						.equals(CaseEnum.DESACTIVEE))
						|| (grille.getListCases().get(p.getIndiceCaseH())
								.get(caseCouranteDeplacementDroite)
								.getEtatActuel().equals(CaseEnum.OCCUPEE))  ) {

					timersDeploiment.get("droite").stop();
					timersDeploiment.remove("droite");


				}

				/*
				 * On continue le deploiment lorsque on rencontre une case
				 * contaminee ou disponible
				 */
				else if (grille.getListCases().get(p.getIndiceCaseH())
						.get(caseCouranteDeplacementDroite).getEtatActuel()
						.equals(CaseEnum.POTENTIELLE)) {
					/* Transformation de la case en case potentielle */
					grille.getListCases().get(p.getIndiceCaseH())
					.get(caseCouranteDeplacementDroite)
					.setEtatActuel(CaseEnum.CONTAMINEE);
					if (caseCouranteDeplacementDroite == grille.getListCases().get(p.getIndiceCaseH()).size()-1) {
						
						timersDeploiment.get("droite").stop();
						timersDeploiment.remove("droite");
					}
					else {
						caseCouranteDeplacementDroite++;
					}
					
				}
				jeuView.repaint();
			}

		});
		timer.start();
		timersDeploiment.put("droite", timer);

	

	}

	@Override
	protected void deploimentBasDroit(final PionModel p) {
		
		ajoutBasDroite=0;
		/* vers le bas droit */
		final DeploimentContaminee deploiment = this;
		final Grille grille = super.getGrille();
		caseCouranteDeplacementBasDroite = p.getIndiceCaseH() + 1;
		Timer timer = new Timer(200, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * On arrete le deploiment lorsque on rencontre une case
				 * desactivee ou occupee
				 */

				
			  try {
				  if (caseCouranteDeplacementBasDroite % 2 == 1){
						ajoutBasDroite++;
				}
				 
				if ((grille.getListCases().get(caseCouranteDeplacementBasDroite)
						.get(p.getIndiceCaseV()+ajoutBasDroite).getEtatActuel()
						.equals(CaseEnum.DESACTIVEE))
						|| (grille.getListCases().get(caseCouranteDeplacementBasDroite)
								.get(p.getIndiceCaseV()+ajoutBasDroite)
								.getEtatActuel().equals(CaseEnum.OCCUPEE))  ) {
					
					timersDeploiment.get("basdroite").stop();
					timersDeploiment.remove("basdroite");
					ajoutBasDroite=0;

				}

				/*
				 * On continue le deploiment lorsque on rencontre une case
				 * contaminee ou disponible
				 */
				else if (grille.getListCases().get(caseCouranteDeplacementBasDroite)
						.get(p.getIndiceCaseV()+ajoutBasDroite).getEtatActuel()
						.equals(CaseEnum.POTENTIELLE)) {
					/* Transformation de la case en case potentielle */
					grille.getListCases().get(caseCouranteDeplacementBasDroite)
					.get(p.getIndiceCaseV()+ajoutBasDroite)
					.setEtatActuel(CaseEnum.CONTAMINEE);
					
					
						caseCouranteDeplacementBasDroite++;
					
						
				}
				
			}
			  catch(IndexOutOfBoundsException e1){
				  timersDeploiment.get("basdroite").stop();
					timersDeploiment.remove("basdroite");
					ajoutBasDroite=0;
			  }
			  jeuView.repaint();
		  }
		
			
		});
		timer.start();
		timersDeploiment.put("basdroite", timer);

	}

	@Override
	protected void deploimentHautGauche(final PionModel p) {
		
		ajoutHautGauche=0;
		/* vers le haut droit */
		final DeploimentContaminee deploiment = this;
		final Grille grille = super.getGrille();
		caseCouranteDeplacementHautGauche = p.getIndiceCaseH() - 1;
		Timer timer = new Timer(200, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * On arrete le deploiment lorsque on rencontre une case
				 * desactivee ou occupee
				 */

				
			  try {
				  if (caseCouranteDeplacementHautGauche  % 2 == 0){
					  ajoutHautGauche++;
				  }
				 
				if ((grille.getListCases().get(caseCouranteDeplacementHautGauche )
						.get(p.getIndiceCaseV()-ajoutHautGauche).getEtatActuel()
						.equals(CaseEnum.DESACTIVEE))
						|| (grille.getListCases().get(caseCouranteDeplacementHautGauche )
								.get(p.getIndiceCaseV()-ajoutHautGauche)
								.getEtatActuel().equals(CaseEnum.OCCUPEE))  ) {
					
					timersDeploiment.get("hautgauche").stop();
					timersDeploiment.remove("hautgauche");
					ajoutHautGauche=0;

				}

				/*
				 * On continue le deploiment lorsque on rencontre une case
				 * contaminee ou disponible
				 */
				else if (grille.getListCases().get(caseCouranteDeplacementHautGauche)
						.get(p.getIndiceCaseV()-ajoutHautGauche).getEtatActuel()
						.equals(CaseEnum.POTENTIELLE)) {
					/* Transformation de la case en case potentielle */
					grille.getListCases().get(caseCouranteDeplacementHautGauche )
					.get(p.getIndiceCaseV()-ajoutHautGauche)
					.setEtatActuel(CaseEnum.CONTAMINEE);
					
					
					caseCouranteDeplacementHautGauche--;
					
						
				}
				
			}
			  catch(IndexOutOfBoundsException e1){
				  timersDeploiment.get("hautgauche").stop();
					timersDeploiment.remove("hautgauche");
					ajoutHautGauche=0;
			  }
			  jeuView.repaint();
		  }
		
			
		});
		timer.start();
		timersDeploiment.put("hautgauche", timer);
	}

	@Override
	protected void deploimentHautDroite(final PionModel p) {
		ajoutHautDroite=0;
		/* vers le haut droit */
		final DeploimentContaminee deploiment = this;
		final Grille grille = super.getGrille();
		caseCouranteDeplacementHautDroite = p.getIndiceCaseH() - 1;
		Timer timer = new Timer(200, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * On arrete le deploiment lorsque on rencontre une case
				 * desactivee ou occupee
				 */

				
			  try {
				  if (caseCouranteDeplacementHautDroite  % 2 == 1){
					  ajoutHautDroite--;
				  }
				 
				if ((grille.getListCases().get(caseCouranteDeplacementHautDroite)
						.get(p.getIndiceCaseV()-ajoutHautDroite).getEtatActuel()
						.equals(CaseEnum.DESACTIVEE))
						|| (grille.getListCases().get(caseCouranteDeplacementHautDroite)
								.get(p.getIndiceCaseV()-ajoutHautDroite)
								.getEtatActuel().equals(CaseEnum.OCCUPEE))  ) {
					
					timersDeploiment.get("hautdroite").stop();
					timersDeploiment.remove("hautdroite");
					ajoutHautDroite=0;

				}

				/*
				 * On continue le deploiment lorsque on rencontre une case
				 * contaminee ou disponible
				 */
				else if (grille.getListCases().get(caseCouranteDeplacementHautDroite)
						.get(p.getIndiceCaseV()-ajoutHautDroite).getEtatActuel()
						.equals(CaseEnum.POTENTIELLE)) {
					/* Transformation de la case en case potentielle */
					grille.getListCases().get(caseCouranteDeplacementHautDroite)
					.get(p.getIndiceCaseV()-ajoutHautDroite)
					.setEtatActuel(CaseEnum.CONTAMINEE);
					
					
					caseCouranteDeplacementHautDroite--;
					
						
				}
				
			}
			  catch(IndexOutOfBoundsException e1){
				  timersDeploiment.get("hautdroite").stop();
					timersDeploiment.remove("hautdroite");
					ajoutHautDroite=0;
			  }
			  jeuView.repaint();
		  }
		
			
		});
		timer.start();
		timersDeploiment.put("hautdroite", timer);
	}

	@Override
	protected void deploimentBasGauche(final PionModel p) {
		ajoutBasGauche=0;
		/* vers le haut droit */
		final DeploimentContaminee deploiment = this;
		final Grille grille = super.getGrille();
		caseCouranteDeplacementBasGauche = p.getIndiceCaseH() + 1;
		Timer timer = new Timer(200, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * On arrete le deploiment lorsque on rencontre une case
				 * desactivee ou occupee
				 */

				
			  try {
				  if (caseCouranteDeplacementBasGauche  % 2 == 0){
					  ajoutBasGauche++;
				  }
				 
				if ((grille.getListCases().get(caseCouranteDeplacementBasGauche)
						.get(p.getIndiceCaseV()-ajoutBasGauche).getEtatActuel()
						.equals(CaseEnum.DESACTIVEE))
						|| (grille.getListCases().get(caseCouranteDeplacementBasGauche)
								.get(p.getIndiceCaseV()-ajoutBasGauche)
								.getEtatActuel().equals(CaseEnum.OCCUPEE))  ) {
					
					timersDeploiment.get("basgauche").stop();
					timersDeploiment.remove("basgauche");
					ajoutBasGauche=0;

				}

				/*
				 * On continue le deploiment lorsque on rencontre une case
				 * contaminee ou disponible
				 */
				else if (grille.getListCases().get(caseCouranteDeplacementBasGauche)
						.get(p.getIndiceCaseV()-ajoutBasGauche).getEtatActuel()
						.equals(CaseEnum.POTENTIELLE)) {
					/* Transformation de la case en case potentielle */
					grille.getListCases().get(caseCouranteDeplacementBasGauche)
					.get(p.getIndiceCaseV()-ajoutBasGauche)
					.setEtatActuel(CaseEnum.CONTAMINEE);
					
					
					caseCouranteDeplacementBasGauche++;
					
						
				}
				
			}
			  catch(IndexOutOfBoundsException e1){
				  timersDeploiment.get("basgauche").stop();
					timersDeploiment.remove("basgauche");
					ajoutBasGauche=0;
			  }
			  jeuView.repaint();
		  }
		
			
		});
		timer.start();
		timersDeploiment.put("basgauche", timer);
	}
}
