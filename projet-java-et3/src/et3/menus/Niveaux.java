package et3.menus;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.binding.ListBinding;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import main.Principale;

import et3.grille.Grille;
import et3.jeu.Jeu;
import et3.reserve.Reserve;

public class Niveaux extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Jeu> listeNiveau;
	private String[] listNiveau;

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

		JList list = new JList(listNiveau);
		
		list.setLayoutOrientation(JList.VERTICAL);
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            int index = list.locationToIndex(evt.getPoint());
		            System.out.println(index);
					Principale p1 = new Principale("Sporos : niveau "+index,index, 300, 500);

		        }
		    }
		});
		
		JScrollPane listScroller = new JScrollPane(list);
		
		getContentPane().add(listScroller);
	}

	private void buildLevels() {
		String[] tmp = new String[100];
		for (int i = 1; i < 100; i++) {
			String str = "level" + i + ".properties";
			File properties = new File(str);
			if (properties.isFile()) {
				Jeu jeu = new Jeu(Grille.buildGrid(str),
						Reserve.buildReserve(str));
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

		for (Jeu j : this.listeNiveau) {
			res += "\t Grille num " + i + ", Reserve : "
					+ j.getReserve().getPions().size() + "\n";
			i++;
		}
		return res;
	}
}
