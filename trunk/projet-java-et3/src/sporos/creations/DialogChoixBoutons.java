package sporos.creations;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import sporos.grille.GrilleEnum;
import sporos.jeu.JeuView;
import sporos.reserve.Reserve;
import sporos.reserve.pions.PionEnum;
import sporos.reserve.pions.PionModel;
import sporos.utils.PropertyAcces;

public class DialogChoixBoutons extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton confirmBtn = new JButton("Valider");
	private String[] nb = { "0", "1", "2", "3" };
	private JComboBox type1 = new JComboBox(nb);
	private JComboBox type2 = new JComboBox(nb);
	private JComboBox type3 = new JComboBox(nb);
	private JComboBox type4 = new JComboBox(nb);
	private JComboBox type5 = new JComboBox(nb);
	private JComboBox type6 = new JComboBox(nb);
	private JComboBox type7 = new JComboBox(nb);
	private ArrayList<JComboBox> al = new ArrayList<JComboBox>();
	private int quantitetype1 = 0;
	private int quantitetype2 = 0;
	private int quantitetype3 = 0;
	private int quantitetype4 = 0;
	private int quantitetype5 = 0;
	private int quantitetype6 = 0;
	private int quantitetype7 = 0;
	private int sumPions = 0;
	private JeuView jeuView;

	public int getQuantitetype1() {
		return quantitetype1;
	}

	public void setQuantitetype1(int quantitetype1) {
		this.quantitetype1 = quantitetype1;
	}

	public int getQuantitetype2() {
		return quantitetype2;
	}

	public void setQuantitetype2(int quantitetype2) {
		this.quantitetype2 = quantitetype2;
	}

	public int getQuantitetype3() {
		return quantitetype3;
	}

	public void setQuantitetype3(int quantitetype3) {
		this.quantitetype3 = quantitetype3;
	}

	public int getQuantitetype4() {
		return quantitetype4;
	}

	public void setQuantitetype4(int quantitetype4) {
		this.quantitetype4 = quantitetype4;
	}

	public int getQuantitetype5() {
		return quantitetype5;
	}

	public void setQuantitetype5(int quantitetype5) {
		this.quantitetype5 = quantitetype5;
	}

	public int getQuantitetype6() {
		return quantitetype6;
	}

	public void setQuantitetype6(int quantitetype6) {
		this.quantitetype6 = quantitetype6;
	}

	public int getQuantitetype7() {
		return quantitetype7;
	}

	public void setQuantitetype7(int quantitetype7) {
		this.quantitetype7 = quantitetype7;
	}

	public DialogChoixBoutons(JDialog dialog, JeuView jeuView) {
		super(dialog, "Choix des Pions", false);
		this.jeuView = jeuView;
		remplirAl();
		JPanel panComboBox = new JPanel();
		panComboBox.setLayout(new GridLayout(8, 2));

		JOptionPane optionPane = new JOptionPane();
		optionPane.setMessage("");
		optionPane.setMessageType(JOptionPane.PLAIN_MESSAGE);

		for (int i = 1; i < 8; i++) {
			try {
				panComboBox.add(new JLabel(new ImageIcon(ImageIO.read(new File(
						"resources/Pion_" + i + ".png")))));
			} catch (IOException e) {
				e.printStackTrace();
			}
			panComboBox.add(al.get(i - 1));
		}
		optionPane.add(panComboBox);
		optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);

		dialog = optionPane.createDialog(null, "");

		JPanel panelConfirmation = new JPanel();
		panelConfirmation.setLayout(new FlowLayout());
		panelConfirmation.add(getConfirmBtn());
		panComboBox.add(panelConfirmation);

		type1.addItemListener(listener1);
		type2.addItemListener(listener2);
		type3.addItemListener(listener3);
		type4.addItemListener(listener4);
		type5.addItemListener(listener5);
		type6.addItemListener(listener6);
		type7.addItemListener(listener7);
		confirmBtn.addActionListener(getConfirmationListener());

		add(panComboBox);
		// add(panelConfirmation);
		pack();
		setLocationRelativeTo(null);

	}

	private ActionListener getConfirmationListener() {
		return confirmationListener;
	}

	public void kill(){
		this.dispose();
	}
	
	private ActionListener confirmationListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (getSumPions() == 0 || getSumPions() > 3) {

				JOptionPane.showMessageDialog(null,
						"Veuillez Choisir entre 1 et 3 pions",
						"Mauvais nombre de pions", JOptionPane.ERROR_MESSAGE);
			} else {
				saveAllCreation();
				kill();
				JOptionPane
						.showMessageDialog(
								null,
								"Le niveau voulu a été créé (niveau : "
										+ (PropertyAcces.getNumLevelToSave()-1)
										+ "), veuillez aller dans Mes Niveaux pour le consulter",
								"Niveau creé", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	};

	private void saveAllCreation() {
		Reserve reserve = new Reserve(GrilleEnum.MOYEN);
		List<PionModel> pions = getAlPions();
		reserve.setPions(pions);
		PropertyAcces.saveCreatedGrid(jeuView.getJeu().getGrille(), reserve);
	}

	private ArrayList<PionModel> getAlPions() {
		ArrayList<PionModel> tmp = new ArrayList<PionModel>();
		int x = 0, y = 0;
		for (int j = 0; j < getQuantitetype1(); j++) {
			tmp.add(new PionModel(PionEnum.TYPE1, x, y, x, y, GrilleEnum.MOYEN));
		}
		for (int j = 0; j < getQuantitetype2(); j++) {
			tmp.add(new PionModel(PionEnum.TYPE2, x, y, x, y, GrilleEnum.MOYEN));
		}
		for (int j = 0; j < getQuantitetype3(); j++) {
			tmp.add(new PionModel(PionEnum.TYPE3, x, y, x, y, GrilleEnum.MOYEN));
		}
		for (int j = 0; j < getQuantitetype4(); j++) {
			tmp.add(new PionModel(PionEnum.TYPE4, x, y, x, y, GrilleEnum.MOYEN));
		}
		for (int j = 0; j < getQuantitetype5(); j++) {
			tmp.add(new PionModel(PionEnum.TYPE5, x, y, x, y, GrilleEnum.MOYEN));
		}
		for (int j = 0; j < getQuantitetype6(); j++) {
			tmp.add(new PionModel(PionEnum.TYPE6, x, y, x, y, GrilleEnum.MOYEN));
		}
		for (int j = 0; j < getQuantitetype7(); j++) {
			tmp.add(new PionModel(PionEnum.TYPE7, x, y, x, y, GrilleEnum.MOYEN));
		}
		return tmp;
	}

	private void remplirAl() {
		al.add(type1);
		al.add(type2);
		al.add(type3);
		al.add(type4);
		al.add(type5);
		al.add(type6);
		al.add(type7);
	}

	public JButton getConfirmBtn() {
		return confirmBtn;
	}

	public int getSumPions() {
		setSumPions(quantitetype1 + quantitetype2 + quantitetype3
				+ quantitetype4 + quantitetype5 + quantitetype6 + quantitetype7);
		return sumPions;
	}

	public void setSumPions(int sumPions) {
		this.sumPions = sumPions;
	}

	private ItemListener listener1 = new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			setQuantitetype1(Integer.parseInt(arg0.getItem().toString()));
		}
	};

	private ItemListener listener2 = new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			setQuantitetype2(Integer.parseInt(arg0.getItem().toString()));
		}
	};

	private ItemListener listener3 = new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			setQuantitetype3(Integer.parseInt(arg0.getItem().toString()));
		}
	};

	private ItemListener listener4 = new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			setQuantitetype4(Integer.parseInt(arg0.getItem().toString()));
		}
	};

	private ItemListener listener5 = new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			setQuantitetype5(Integer.parseInt(arg0.getItem().toString()));
		}
	};

	private ItemListener listener6 = new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			setQuantitetype6(Integer.parseInt(arg0.getItem().toString()));
		}
	};

	private ItemListener listener7 = new ItemListener() {

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			setQuantitetype7(Integer.parseInt(arg0.getItem().toString()));
		}
	};

}