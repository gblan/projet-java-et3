package et3.menus;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import et3.grille.Grille;
import et3.jeu.Jeu;
import et3.reserve.Reserve;

public class Niveaux extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Jeu> listeNiveau;

	/**
	 * @return Constructeur de niveaux
	 */
	public Niveaux(String windowsName) {
		super(windowsName);
		this.listeNiveau = new ArrayList<Jeu>();
		setBounds(100, 100, 315, 454);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		buildLevels();
		initialize();
		setVisible(true);
	}

	private void initialize() {
		int i = 1;

		setBounds(100, 100, 315, 454);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JList<Jeu> list = new JList<Jeu>();
		JScrollPane listScroller = new JScrollPane(list);

		for (Jeu j : this.listeNiveau) {
			list.add("level : " + i, j);
			i++;
		}
		getContentPane().add(listScroller);
	}

	private void buildLevels() {
		for (int i = 1; i < 100; i++) {
			String str = "level" + i + ".properties";
			File properties = new File(str);
			if (properties.isFile()) {
				Jeu jeu = new Jeu(Grille.buildGrid(str),
						Reserve.buildReserve(str));
				this.listeNiveau.add(jeu);

			}
		}
	}

	@Override
	public String toString() {
		String res = "";
		int i = 1;
		res += "Niveaux : \n";

		for (Jeu j : this.listeNiveau) {
			res += "\t Grille num " + i + ", Reserve : "
					+ j.getReserve().getPions().size() + "\n";
			i++;
		}
		return res;
	}
}
