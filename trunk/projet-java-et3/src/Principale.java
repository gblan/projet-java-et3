import javax.swing.JDialog;
import javax.swing.JFrame;

public class Principale extends JDialog {

	public Principale() {
		super((JFrame) null, "", true /* modal */);
		setBounds(500, 300, 0, 0);
		setSize(400, 200);
		pack();
		setVisible(true);

	}

	public static void main(String[] arg) {
		/*Principale p1 = new Principale();*/
		Sauvegarde s1 = new Sauvegarde();
		s1.saveProperties("cheval", "blanc");
	}

}