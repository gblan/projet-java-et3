package sporos.menus;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import sporos.grille.Grille;
import sporos.grille.GrilleEnum;
import sporos.jeu.JeuModel;
import sporos.main.Principale;
import sporos.reserve.Reserve;
import sporos.utils.PropertyAcces;

public class MenuNiveaux extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] listNiveau;
	private int currentLevel = 0;

	/**
	 * @return Constructeur de niveaux
	 */
	public MenuNiveaux(String windowsName) {
		super(windowsName);
		setBounds(100, 100, 315, 454);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		currentLevel = PropertyAcces.getCurrentLevel();

		buildLevels();
		initialize();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void initialize() {
		setBounds(100, 100, 315, 454);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JList list = new JList(listNiveau);

		// list.setEnabled(false);
		list.setLayoutOrientation(JList.VERTICAL);
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {
					int index = list.locationToIndex(evt.getPoint());
					setVisible(false);
					GrilleEnum tailleGrille;
					if (index < 6) {
						tailleGrille = GrilleEnum.PETIT;
					} else if (index < 21) {
						tailleGrille = GrilleEnum.MOYEN;
					} else {
						tailleGrille = GrilleEnum.GRAND;
					}
					Principale p1 = new Principale(index, 300, 500,
							tailleGrille, false, false);

				}
			}
		});

		JScrollPane listScroller = new JScrollPane(list);
		getContentPane().add(listScroller);
	}

	private void buildLevels() {
		String[] tmp = new String[currentLevel + 1];
		for (int i = 1; i < currentLevel + 1; i++) {
			String str = "level" + i + ".properties";
			File properties = new File("levels/" + str);
			if (properties.isFile()) {
				JeuModel jeu = new JeuModel(i, Grille.buildGrid(
						"levels/" + str, GrilleEnum.GRAND),
						Reserve.buildReserve("levels/" + str, GrilleEnum.GRAND));
				String[] tmp2 = str.split("\\.");
				tmp[i] = tmp2[0];
			}
		}
		listNiveau = tmp;

	}

	@Override
	public String toString() {
		String res = "";

		for (String j : this.listNiveau) {
			res += "Nom niveau : " + j + "\n";
		}
		return res;
	}
}
