package sporos.creations;

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

public class MesCreationsView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] listNiveau;

	/**
	 * @return Constructeur de niveaux
	 */
	public MesCreationsView(String windowsName) {
		super(windowsName);
		setBounds(100, 100, 315, 454);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
					Principale p1 = new Principale(index, 300, 500,
							GrilleEnum.MOYEN, true, false);

				}
			}
		});

		JScrollPane listScroller = new JScrollPane(list);
		getContentPane().add(listScroller);
	}

	private void buildLevels() {
		String[] tmp = new String[PropertyAcces.getNumLevelToSave()];
		for (int i = 1; i < PropertyAcces.getNumLevelToSave(); i++) {
			String str = "level" + i + ".properties";
			File properties = new File("levels/myLevels/" + str);
			if (properties.isFile()) {
				JeuModel jeu = new JeuModel(i, Grille.buildGrid(
						"levels/myLevels/" + str, GrilleEnum.MOYEN),
						Reserve.buildReserve("levels/myLevels/" + str,
								GrilleEnum.MOYEN));
				tmp[i] = str;
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
