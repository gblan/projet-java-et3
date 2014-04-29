package et3.menus;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import main.Principale;
import et3.grille.Grille;
import et3.jeu.JeuModel;
import et3.reserve.Reserve;
import et3.sauvegarde.PropertyAcces;

public class Niveaux extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<JeuModel> listeNiveau;
	private String[] listNiveau;
	private int currentLevel = 0;

	/**
	 * @return Constructeur de niveaux
	 */
	public Niveaux(String windowsName) {
		super(windowsName);
		this.listeNiveau = new ArrayList<JeuModel>();
		setBounds(100, 100, 315, 454);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		currentLevel = PropertyAcces.getCurrentLevel();

		buildLevels();
		initialize();
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
					Principale p1 = new Principale("Sporos : niveau " + index,
							index, 300, 500);

				}
			}
		});

		JScrollPane listScroller = new JScrollPane(list);
		getContentPane().add(listScroller);
	}

	private void buildLevels() {
		String[] tmp = new String[currentLevel + 2];
		for (int i = 1; i < currentLevel + 2; i++) {
			String str = "level" + i + ".properties";
			File properties = new File("levels/" + str);
			if (properties.isFile()) {
				JeuModel jeu = new JeuModel(i,
						Grille.buildGrid("levels/" + str),
						Reserve.buildReserve("levels/" + str));
				this.listeNiveau.add(jeu);
				tmp[i] = str;
			}
		}
		listNiveau = tmp;

	}

	@Override
	public String toString() {
		String res = "";
		int i = 1;
		res += "Niveaux : \n";

		for (JeuModel j : this.listeNiveau) {
			res += "\t Grille num " + i + ", Reserve : "
					+ j.getReserve().getPions().size() + "\n";
			i++;
		}
		return res;
	}
}
